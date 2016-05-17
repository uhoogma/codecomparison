package com.googlecode.ounit.codecomparison.controller;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.googlecode.ounit.codecomparison.dao.AttemptDao;
import com.googlecode.ounit.codecomparison.dao.RoundDao;
import com.googlecode.ounit.codecomparison.dao.StudentDao;
import com.googlecode.ounit.codecomparison.dao.TaskDao;
import com.googlecode.ounit.codecomparison.dao.VersionDao;
import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Login;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Student;
import com.googlecode.ounit.codecomparison.model.Task;
import com.googlecode.ounit.codecomparison.util.CharsetUtil;
import com.googlecode.ounit.codecomparison.view.FileForm;
import com.googlecode.ounit.codecomparison.view.TaskForm;
import com.googlecode.ounit.moodlescraper.MoodleScraper2;
import com.googlecode.ounit.moodlescraper.MoodleScraperRunner;

@Controller
public class TaskController {

	@Resource
	private TaskDao taskDao = new TaskDao();
	@Resource
	private RoundDao roundDao = new RoundDao();
	@Resource
	private VersionDao versionDao = new VersionDao();
	@Resource
	private AttemptDao attemptDao = new AttemptDao();
	@Resource
	private StudentDao studentDao = new StudentDao();

	@ModelAttribute("taskForm")
	public TaskForm getTaskForm() {
		return new TaskForm();
	}

	@ModelAttribute("fileForm")
	public FileForm getFileForm() {
		return new FileForm();
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String newRound(Model model) {
		List<Round> rounds = roundDao.findAllRoundsNotInAnyTask();
		TaskForm form = new TaskForm();
		form.setRoundsNotInTask(rounds);
		model.addAttribute("taskForm", form);
		return "edittask";
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public String saveRound(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result, Model model) {
		return editingResponse(form, result, null, model);
	}

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String edittask(@ModelAttribute("taskForm") TaskForm taskForm, @ModelAttribute("fileForm") FileForm fileForm,
			@PathVariable("id") String id) {
		Task task = taskDao.findTaskForId(Long.parseLong(id));
		if (task != null) {
			setRounds(taskForm, task);
			setAttemptFile(fileForm, task);
			taskDao.store(task);
			return "edittask";
		} else {
			return "404";
		}
	}

	private void setAttemptFile(FileForm fileForm, Task task) {
		fileForm.setTaskId(task.getId());

		Attempt attempt = attemptDao.getTaskBoilerPlateAttempt(task.getId());
		if (attempt != null) {
			String filename = attempt.getFileName();
			fileForm.setFileName(filename);
		}
	}

	private void setRounds(TaskForm form, Task task) {
		List<Round> roundsIn = roundDao.findAllRoundsInTask(task.getId());
		form.setRoundsInTask(roundsIn);
		List<Round> roundsOut = roundDao.findAllRoundsNotInAnyTask();
		form.setRoundsNotInTask(roundsOut);
		form.setTask(task);
	}

	@RequestMapping(value = "/edittask/{taskId}/addRound/{roundId}", method = RequestMethod.POST)
	public String addRoundToTask(@PathVariable("taskId") String taskId, @PathVariable("roundId") String roundId) {
		Round round = roundDao.findRoundForId(Long.parseLong(roundId));
		Task task = taskDao.findTaskForId(Long.parseLong(taskId));
		if (round != null && task != null) {
			taskDao.addRound(task, round);
			return "edittask";
		} else {
			return "404";
		}
	}

	@RequestMapping(value = "/edittask/{taskId}/removeRound/{roundId}", method = RequestMethod.POST)
	public String removeRoundFromTask(@PathVariable("taskId") String taskId, @PathVariable("roundId") String roundId) {
		Round round = roundDao.findRoundForId(Long.parseLong(roundId));
		Task task = taskDao.findTaskForId(Long.parseLong(taskId));
		if (round != null && task != null) {
			taskDao.removeRound(task, round);
			return "edittask";
		} else {
			return "404";
		}
	}

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.POST)
	public String editRound(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result,
			@PathVariable("id") String id, Model model) {
		return editingResponse(form, result, Long.parseLong(id), model);
	}

	private String editingResponse(TaskForm form, BindingResult result, Long id, Model model) {
		Task t = taskDao.findTaskForId(id);
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getObjectName() + " - " + error.getDefaultMessage());
			}
			form.setTask(t);
			model.addAttribute("errors", errors);
			return "edittask";
		} else {
			if (id == null) {
				t = form.getTask();
				t.setActive(true);
				t.setK(versionDao.getDefaultK());
				t.setT(versionDao.getDefaultT());
			}
			taskDao.store(t);
			return "redirect:/index";
		}
	}

	@RequestMapping(value = "/edittask/{taskId}/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file, @PathVariable("taskId") String taskId) {
		if (!file.isEmpty()) {
			try {
				InputStream is = file.getInputStream();
				CharsetUtil cu = new CharsetUtil();
				Charset charset = cu.getCharSetInUse(file);

				if (charset.name().equals("UTF-8")) {
					StringWriter writer = new StringWriter();
					IOUtils.copy(is, writer, "UTF-8");
					String code = writer.toString();

					Attempt attempt = attemptDao.getTaskBoilerPlateAttempt(Long.parseLong(taskId));

					if (attempt != null) {
						updateBoilerplateCode(file, code, attempt);
					} else {
						createBoilerplateCode(file, taskId, code);
					}
				}
				return "redirect:/edittask/" + taskId;
			} catch (Exception e) {
				return "404";
			}
		} else {
			return "404";
		}
	}

	private void createBoilerplateCode(MultipartFile file, String taskId, String code) {
		Attempt newAttempt = new Attempt();
		newAttempt.setTask_id(Long.parseLong(taskId));
		newAttempt.setCode(code);
		newAttempt.setCodeAcquired(true);
		newAttempt.setBoilerPlate(true);
		newAttempt.setFileName(file.getOriginalFilename());
		attemptDao.store(newAttempt);
	}

	private void updateBoilerplateCode(MultipartFile file, String code, Attempt attempt) {
		attempt.setCode(code);
		attempt.setFileName(file.getOriginalFilename());
		attemptDao.store(attempt);
	}

	@RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
	public String showTask(@PathVariable("taskId") String taskId, Model model) {
		Task task = taskDao.findTaskForId(Long.parseLong(taskId));
		TaskForm form = new TaskForm();
		form.setTask(task);
		model.addAttribute("taskForm", form);
		return "task";
	}

	@RequestMapping(value = "/synchronizetask/{taskId}", method = RequestMethod.POST)
	public String synchronizeTask(@PathVariable("taskId") String taskId, @RequestBody Login login) {
		System.out.println(login.toString());
		MoodleScraperRunner msr = new MoodleScraperRunner();
		List<Round> rounds = roundDao.findAllRoundsInTask(Long.parseLong(taskId));

		// saveNewStudents(rounds, taskId, login, msr);
		// saveNewAttempts(rounds, taskId, login, msr);
		saveAttempts(rounds, taskId, login, msr);
		return "redirect:/task/" + taskId;
	}

	private void saveNewStudents(List<Round> rounds, String taskId, Login login, MoodleScraperRunner msr) {
		List<Long> uniqueIdsFromDB = studentDao.getAllMoodleIds();
		for (Round round : rounds) {
			List<Student> studs = msr.getNewStudents(round, Integer.parseInt(taskId), login.getUser(), login.getPass(),
					uniqueIdsFromDB);
			System.out.println(studs);
			for (Student stu : studs) {
				studentDao.store(stu);
			}
		}
	}

	private void saveNewAttempts(List<Round> rounds, String taskId, Login login, MoodleScraperRunner msr) {
		List<Long> uniqueIdsFromDB = attemptDao.getAttemptIds();
		for (Round round : rounds) {
			List<Attempt> studs = msr.getNewAttempts(round, Integer.parseInt(taskId), login.getUser(), login.getPass(),
					uniqueIdsFromDB);
			System.out.println(studs);
			for (Attempt stu : studs) {
				attemptDao.store(stu);
			}
		}
	}
	
	private void saveAttempts(List<Round> rounds, String taskId, Login login, MoodleScraperRunner msr) {

		for (Round round : rounds) {
			List<Attempt> attempts = attemptDao.findAttemptsNotFetched(round.getId());

			List<Attempt> studs = msr.downloadAttempts(round, attempts, login.getUser(), login.getPass());
			System.out.println(studs);
			for (Attempt stu : studs) {
				stu.setCodeAcquired(true);
				attemptDao.store(stu);
				break;
			}
		}
	}
}
