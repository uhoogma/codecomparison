package com.googlecode.ounit.codecomparison.controller;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.ounit.codecomparison.dao.AbstractedCodeDao;
import com.googlecode.ounit.codecomparison.dao.AttemptDao;
import com.googlecode.ounit.codecomparison.dao.RoundDao;
import com.googlecode.ounit.codecomparison.dao.SavedComparisonDao;
import com.googlecode.ounit.codecomparison.dao.StudentDao;
import com.googlecode.ounit.codecomparison.dao.TaskDao;
import com.googlecode.ounit.codecomparison.dao.VersionDao;
import com.googlecode.ounit.codecomparison.model.AbstractedCode;
import com.googlecode.ounit.codecomparison.model.Attempt;
import com.googlecode.ounit.codecomparison.model.Login;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.SavedComparison;
import com.googlecode.ounit.codecomparison.model.Student;
import com.googlecode.ounit.codecomparison.model.Task;
import com.googlecode.ounit.codecomparison.util.CharsetUtil;
import com.googlecode.ounit.codecomparison.util.Message;
import com.googlecode.ounit.codecomparison.util.Paging;
import com.googlecode.ounit.codecomparison.util.PrepareChartData;
import com.googlecode.ounit.codecomparison.view.FileForm;
import com.googlecode.ounit.codecomparison.view.TaskForm;
import com.googlecode.ounit.codesimilarity.Pair;
import com.googlecode.ounit.codesimilarity.SimilarityRunnerAdvanced;
import com.googlecode.ounit.codesimplifier.processing.Java2SimpleJava;
import com.googlecode.ounit.moodlescraper.MoodleScraper;
import com.googlecode.ounit.moodlescraper.MoodleScraperRunner;

@Controller
@SessionAttributes("messages")
public class TaskController {

	private static final int UTF8_TEXT_MAX_LENGTH = 32766;
	private static final int MAX_COMPARISONS_PER_TASK = 100;
	private static final Integer RESULT_COUNT = 8;

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
	@Resource
	private AbstractedCodeDao abstractedCodeDao = new AbstractedCodeDao();
	@Resource
	private SavedComparisonDao savedComparisonDao = new SavedComparisonDao();

	private Message message = new Message();

	@ModelAttribute("fileForm")
	public FileForm getFileForm() {
		return new FileForm();
	}

	@ModelAttribute("taskForm")
	public TaskForm getTaskForm() {
		return new TaskForm();
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String newTask(Model model) {
		List<Round> rounds = roundDao.findAllRoundsNotInAnyTask();
		TaskForm form = new TaskForm();
		form.setRoundsNotInTask(rounds);
		model.addAttribute("taskForm", form);
		return "edittask";
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public String saveTask(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result, Model model) {
		return editingResponse(form, result, null, model);
	}

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String editTask(@ModelAttribute("taskForm") TaskForm taskForm, @ModelAttribute("fileForm") FileForm fileForm,
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

	private void setRounds(TaskForm form, Task task) {
		List<Round> roundsIn = roundDao.findAllRoundsInTask(task.getId());
		form.setRoundsInTask(roundsIn);
		List<Round> roundsOut = roundDao.findAllRoundsNotInAnyTask();
		form.setRoundsNotInTask(roundsOut);
		form.setTask(task);
	}

	private void setAttemptFile(FileForm fileForm, Task task) {
		fileForm.setTaskId(task.getId());
		Attempt attempt = attemptDao.getTaskBoilerPlateAttempt(task.getId());
		if (attempt != null) {
			String filename = attempt.getFileName();
			fileForm.setFileName(filename);
		}
	}

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.POST)
	public String editTask(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result,
			@PathVariable("id") String id, Model model) {
		return editingResponse(form, result, Long.parseLong(id), model);
	}

	private String editingResponse(TaskForm form, BindingResult result, Long id, Model model) {
		Task t = taskDao.findTaskForId(id);
		List<String> customErrors = form.validate(form.getTask());
		if (result.hasErrors() || !customErrors.isEmpty()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getObjectName() + " - " + error.getDefaultMessage());
			}
			errors.addAll(customErrors);
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
			t.setTaskName(form.getTask().getTaskName());
			t.setActive(form.getTask().isActive());
			taskDao.store(t);
			return "redirect:/index";
		}
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
					String dbCode = cropLongCode(code);
					if (attempt != null) {
						updateBoilerplateCode(file, dbCode, attempt);
					} else {
						createBoilerplateCode(file, taskId, dbCode);
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

	private String cropLongCode(String code) {
		return code.length() > UTF8_TEXT_MAX_LENGTH ? code.substring(0, UTF8_TEXT_MAX_LENGTH) : code;
	}

	private void updateBoilerplateCode(MultipartFile file, String code, Attempt attempt) {
		attempt.setCode(code);
		attempt.setFileName(file.getOriginalFilename());
		attemptDao.store(attempt);
	}

	private void createBoilerplateCode(MultipartFile file, String taskId, String code) {
		Long taskIdLong = Long.parseLong(taskId);
		attemptDao.store(new Attempt(taskIdLong, file.getOriginalFilename(), code, true, true));
	}

	@RequestMapping(value = "/task/{taskId}/{startId}", method = RequestMethod.GET)
	public ModelAndView showTask(@PathVariable("taskId") String taskId, @PathVariable("startId") String startId,
			Model model) {
		if (!model.containsAttribute("messages")) {
			model.addAttribute("messages", new CircularBuffer(20));
		}

		Long taskIdLong = Long.parseLong(taskId);
		Long currentVersionId = versionDao.getCurrentVersion();
		Integer startFromId = new Integer(startId);

		TaskForm form = new TaskForm();

		setTask(taskIdLong, form);
		setComparisonsTable(taskIdLong, startFromId, RESULT_COUNT, form);
		setPagination(taskIdLong, currentVersionId, form, startFromId);
		setChart(taskIdLong, currentVersionId, form);

		form.setResultCount(RESULT_COUNT);
		form.setSequentialNumber(startFromId);
		form.setStudentCount(attemptDao.getTasksStudentCount(taskIdLong));
		form.setAttemptCount(attemptDao.getTasksAttemptCount(taskIdLong));
		form.setUnfetchedAttemptCount(attemptDao.getUnfetchedTasksAttemptCount(taskIdLong));
		model.addAttribute("taskForm", form);
		return new ModelAndView("task", "message", getMessages(model));
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public @ResponseBody String getMessages(Model model) {
		Map<String, Object> modelMap = model.asMap();
		CircularBuffer buffer = (CircularBuffer) modelMap.get("messages");
		if (buffer != null) {
			String response = buffer.read();
			if (response == null || response.isEmpty()) {
				return "";
			} else {
				return response+" => ";
			}
		} else {
			return "";
		}
	}

	private void setTask(Long taskIdLong, TaskForm form) {
		Task task = taskDao.findTaskForId(taskIdLong);
		form.setTask(task);
	}

	private void setComparisonsTable(Long taskIdLong, Integer startFromId, Integer resultCount, TaskForm form) {
		List<SavedComparison> list = savedComparisonDao.fillTable(taskIdLong, startFromId, resultCount);
		form.setComparisons(list);
	}

	private void setPagination(Long taskIdLong, Long currentVersionId, TaskForm form, Integer startFromId) {
		Integer totalResults = savedComparisonDao.getResultCount(taskIdLong, currentVersionId);
		form.setPages((new Paging()).paginate(totalResults, RESULT_COUNT, startFromId));
	}

	private void setChart(Long taskIdLong, Long currentVersionId, TaskForm form) {
		List<Double> chartData = savedComparisonDao.getChartData(taskIdLong, currentVersionId);
		PrepareChartData pcd = new PrepareChartData();
		Map<String, Double> processedChartData = pcd.groupAndCalculateMedium(chartData);
		String labels = "";
		String values = "";
		for (Map.Entry<String, Double> item : processedChartData.entrySet()) {
			labels = labels + "\"" + item.getKey() + "\" ,";
			values = values + item.getValue() + " ,";
		}
		String beginning = "$(window).load(function() {if (document.getElementById('canvas') != null) {var ctx = document.getElementById('canvas').getContext('2d');var data = {labels: [";
		String middle = "],datasets: [{label: \"Chart dataset\",fillColor: \"#99ff99\",strokeColor: \"#33cc33\",pointColor: \"#0066ff\",pointStrokeColor: \"#fff\",pointHighlightFill: \"#fff\",pointHighlightStroke: \"rgba(151,187,205,1)\",data: [";
		String end = "]}]};var myLineChart  = new Chart(ctx).Line(data, null);}});";
		String result = beginning + labels.substring(0, labels.length() - 2) + middle
				+ values.substring(0, values.length() - 2) + end;
		form.setChartScript(result);
	}

	@RequestMapping(value = "/deletetask/{taskId}")
	public String deleteTask(@PathVariable("taskId") String taskId) {
		Long taskIdLong = Long.parseLong(taskId);
		Task task = taskDao.findTaskForId(taskIdLong);
		if (task != null) {
			for(Round round : task.getRounds()){
				roundDao.delete(round.getId());
			}
			taskDao.delete(taskIdLong);
			return "redirect:/index";
		} else {
			return "404";
		}
	}

	@RequestMapping(value = "/synchronizetask/{taskId}/{dummy}", method = RequestMethod.POST)
	public String synchronizeTask(@PathVariable("taskId") String taskId, @RequestBody Login login, Model model) {
		MoodleScraperRunner msr = new MoodleScraperRunner();
		Long taskIdLong = Long.parseLong(taskId);
		List<Round> rounds = roundDao.findAllRoundsInTask(taskIdLong);

		MoodleScraper ms = login(login, model, msr, rounds);
		saveNewStudents(rounds, taskId, ms);
		saveNewAttempts(rounds, taskId, ms);
		saveAttempts(rounds, taskId, ms);
		renewConstants(model, taskIdLong);
		logout(model, ms);
		if (model.containsAttribute("messages")) {
			model.asMap().remove("messages");
		}
		return "redirect:/redirectto/" + taskId;
	}

	@RequestMapping(value = "/redirectto/{taskId}")
	public String redirectTask(@PathVariable("taskId") String taskId) {
		return "redirect:/task/" + taskId + "/0";
	}

	private MoodleScraper login(Login login, Model model, MoodleScraperRunner msr, List<Round> rounds) {
		message.storeMessage(model, "Alustame logimist");
		MoodleScraper ms = msr.login(rounds.get(0), login, model, message);
		return ms;
	}

	private void saveNewStudents(List<Round> rounds, String taskId, MoodleScraper ms) {
		List<Integer> studentIds = studentDao.getAllMoodleIds();
		int newStudents = 0;
		for (Round round : rounds) {
			List<Student> students = ms.downloadStudents(round, "TreeNode.java", studentIds);
			for (Student student : students) {
				studentDao.store(student);
				newStudents++;
			}
		}
		message.storeMessage(ms.getModel(), "On salvestatud " + newStudents + " uut tudengit");
	}

	private void saveNewAttempts(List<Round> rounds, String taskId, MoodleScraper ms) {
		Long taskIdLong = Long.parseLong(taskId);
		List<Long> attemptIds = attemptDao.getAttemptMoodleIds(taskIdLong);
		int newAttempts = 0;
		for (Round round : rounds) {
			List<Attempt> attempts = ms.downloadAttempts(round, "TreeNode.java", attemptIds);
			for (Attempt attempt : attempts) {
				attemptDao.store(attempt);
				newAttempts++;
			}
		}
		message.storeMessage(ms.getModel(),
				"On registreeritud " + newAttempts + " uut esitust eelmisest korrast saati.");
	}

	private void saveAttempts(List<Round> rounds, String taskId, MoodleScraper ms) {
		int attempts = 0;
		int attemptsNotFetchedCount = 0;
		for (Round round : rounds) {
			int attemptsNotFetchedInThisRound = 0;
			List<Attempt> attemptsNotFetched = attemptDao.findAttemptsNotFetched(round.getId());
			attemptsNotFetchedInThisRound = attemptsNotFetched.size();
			for (Attempt attempt : attemptsNotFetched) {
				String code = ms.download(attempt.getMoodleId(), attempt.getStudentId());
				if (!code.isEmpty()) {
					attempt.setCode(code);
					attempt.setCodeAcquired(true);
					attemptDao.store(attempt);
					attempts++;
					attemptsNotFetchedInThisRound--;
					message.storeMessage(ms.getModel(), "On salvestatud tudengi id-ga: " + attempt.getStudentId()
							+ " esitus id-ga: " + attempt.getMoodleId());
				}
			}
			attemptsNotFetchedCount += attemptsNotFetchedInThisRound;
		}
		message.storeMessage(ms.getModel(), "On salvestatud " + attempts + " uut tudengi koodi.");
		if (attemptsNotFetchedCount > 0) {
			message.storeMessage(ms.getModel(), attemptsNotFetchedCount + " koodi on salvestamata. Proovige uuesti.");
		}
	}

	private void renewConstants(Model model, Long taskIdLong) {
		Task task = taskDao.findTaskForId(taskIdLong);
		java.util.Date date = new java.util.Date();
		task.setLastSyncTime(new Timestamp(date.getTime()));
		taskDao.store(task);
		message.storeMessage(model, "Konstandid uuendatud");
	}

	private void logout(Model model, MoodleScraper ms) {
		ms.logout();
		message.storeMessage(model, "Oleme välja loginud");
	}

	@RequestMapping(value = "/analyzetask/{taskId}/{dummy}/{noise}/{match}", method = RequestMethod.POST)
	public String analyzeTask(@PathVariable("taskId") String taskId, @PathVariable("noise") String noise,
			@PathVariable("match") String match) {
		int noiseInt = new Integer(noise);
		int matchInt = new Integer(match);
		if (matchInt > noiseInt && noiseInt > 0) {
			Long taskIdLong = Long.parseLong(taskId);
			Long currentVersionId = versionDao.getCurrentVersion();
			Java2SimpleJava j2sj = new Java2SimpleJava();

			abstractBoilerPlateCode(j2sj, taskId, currentVersionId);
			abstractStudentCode(j2sj, taskId, currentVersionId);

			List<SavedComparison> csvResult = analyze(taskIdLong, currentVersionId, noiseInt, matchInt);

			deletePreviousAnalysis(taskIdLong, currentVersionId);
			saveNewComparisons(taskIdLong, currentVersionId, csvResult);
			updateConstants(noiseInt, matchInt, taskIdLong);
		}
		return "redirect:/redirectto/" + taskId;
	}

	private void abstractBoilerPlateCode(Java2SimpleJava j2sj, String taskId, Long versionId) {
		Long taskIdLong = Long.parseLong(taskId);
		Attempt boilerplate = attemptDao.getTaskBoilerPlateAttempt(taskIdLong);
		if (boilerplate != null) {
			Long boilerplateId = boilerplate.getId();
			AbstractedCode abstractedBoiler = abstractedCodeDao.getCodeForAttempt(boilerplateId, versionId);
			if (abstractedBoiler != null) {
				String abstracted = j2sj.processString(boilerplate.getCode());
				abstractedBoiler.setAbstractedCode(abstracted);
				abstractedCodeDao.store(abstractedBoiler);
			} else {
				String abstracted = j2sj.processString(boilerplate.getCode());
				abstractedCodeDao.store(new AbstractedCode(taskIdLong, boilerplateId, versionId, abstracted));
			}
		}
	}

	private void abstractStudentCode(Java2SimpleJava j2sj, String taskId, Long currentVersionId) {
		Long taskIdLong = Long.parseLong(taskId);
		List<Attempt> attempts = attemptDao.getAttemptsForTask(taskIdLong);
		List<AbstractedCode> abstractedAttempts = abstractedCodeDao.getAbstractedAttemptsForTask(taskIdLong,
				currentVersionId);
		List<Long> abstractedAttemptIds = abstractedAttempts.stream().map(a -> a.getAttempt_id())
				.collect(Collectors.toList());
		for (Attempt attempt : attempts) {
			if (!attempt.getCode().isEmpty() && !abstractedAttemptIds.contains(attempt.getId())) {
				String abstracted = j2sj.processString(attempt.getCode());
				abstractedCodeDao.store(new AbstractedCode(taskIdLong, attempt.getId(), currentVersionId, abstracted));
			}
		}
	}

	private List<SavedComparison> analyze(Long taskId, Long versionId, int noise, int match) {
		int ngramsize = noise;
		int windowsize = match - noise + 1;
		double similarityThreshold = 0;
		SimilarityRunnerAdvanced sim = new SimilarityRunnerAdvanced(ngramsize, windowsize, similarityThreshold);
		String boilerPlate = abstractedCodeDao.getTaskBoilerPlateAbstracted(taskId, versionId);
		Map<Pair, String> studentSubmissions = new HashMap<Pair, String>();
		List<Attempt> attempts = attemptDao.getAttemptsForTask(taskId);
		for (Attempt attempt : attempts) {
			List<AbstractedCode> list = attempt.getAbstractedCodes().stream()
					.filter(a -> a.getVersion_id() == versionId).collect(Collectors.toList());
			if (!list.isEmpty()) {
				String code = list.get(0).getAbstractedCode();
				studentSubmissions.put(new Pair(attempt.getStudentId(), attempt.getMoodleId().intValue()), code); // null
			}
		}
		return sim.run(boilerPlate, studentSubmissions);
	}

	private void deletePreviousAnalysis(Long taskIdLong, Long currentVersionId) {
		savedComparisonDao.deletePreviousAnalysis(taskIdLong, currentVersionId);
	}

	private void saveNewComparisons(Long taskIdLong, Long currentVersionId, List<SavedComparison> csvResult) {
		int count = 0;
		for (SavedComparison sc : csvResult) {
			sc.setTask_id(taskIdLong);
			sc.setVersion_id(currentVersionId);
			savedComparisonDao.store(sc);
			count++;
			if (count == MAX_COMPARISONS_PER_TASK) {
				break;
			}
		}
	}

	private void updateConstants(int noiseInt, int matchInt, Long taskIdLong) {
		Task currentTask = taskDao.findTaskForId(taskIdLong);
		currentTask.setK(noiseInt);
		currentTask.setT(matchInt);
		taskDao.store(currentTask);
	}
}
