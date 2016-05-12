package com.googlecode.ounit.codecomparison.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.ounit.codecomparison.dao.RoundDao;
import com.googlecode.ounit.codecomparison.dao.TaskDao;
import com.googlecode.ounit.codecomparison.dao.VersionDao;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;
import com.googlecode.ounit.codecomparison.view.TaskForm;

@Controller
public class TaskController {

	@Resource
	private TaskDao taskDao = new TaskDao();
	@Resource
	private RoundDao roundDao = new RoundDao();
	@Resource
	private VersionDao versionDao = new VersionDao();

	@ModelAttribute("taskForm")
	public TaskForm getUserObject3() {
		return new TaskForm();
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String newRound(Model model) {
		List<Round> rounds = roundDao.findAllRounds();
		TaskForm form = new TaskForm();
		form.setRoundsNotInTask(rounds);
		model.addAttribute("taskForm", form);
		return "edittask";
	}

	@RequestMapping(value = "/edittask", method = RequestMethod.POST)
	public String saveRound(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result, Model model) {
		return editingResponse(form, result, model, true);
	}

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String edittask(@ModelAttribute("taskForm") TaskForm form, @PathVariable("id") String id) {
		Task task = taskDao.findTaskForId(Long.parseLong(id));
		if (task != null) {
			List<Round> roundsIn = roundDao.findAllRoundsInTask(Long.parseLong(id));
			form.setRoundsInTask(roundsIn);
			List<Round> roundsOut = roundDao.findAllRoundsNotInTask(Long.parseLong(id));
			form.setRoundsNotInTask(roundsOut);
			form.setTask(task);
			return "edittask";
		} else {
			return "404";
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

	@RequestMapping(value = "/edittask/{id}", method = RequestMethod.POST)
	public String editRound(@Valid @ModelAttribute("taskForm") TaskForm form, BindingResult result,
			@PathVariable("id") String id, Model model) {
		return editingResponse(form, result, model, false);
	}

	private String editingResponse(TaskForm form, BindingResult result, Model model, boolean firstSave) {
		Task t = form.getTask();
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getObjectName() + " - " + error.getDefaultMessage());
			}
			form.setTask(t);
			model.addAttribute("errors", errors);
			return "edittask";
		} else {
			if (firstSave) {
				t.setActive(true);
				t.setK(versionDao.getDefaultK());
				t.setT(versionDao.getDefaultT());
			}
			taskDao.store(t);
			return "redirect:/index";
		}
	}
}
