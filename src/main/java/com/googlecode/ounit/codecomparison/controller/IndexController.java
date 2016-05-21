package com.googlecode.ounit.codecomparison.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.ounit.codecomparison.dao.TaskDao;
import com.googlecode.ounit.codecomparison.model.Task;

@Controller
public class IndexController {
	
	@Resource
	private TaskDao taskDao = new TaskDao();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String newRound(Model model) {
		List<Task> active = taskDao.findActiveTasks();
		model.addAttribute("active", active);
		List<Task> hidden = taskDao.findHiddenTasks();
		model.addAttribute("hidden", hidden);
		return "index";
	}
}
