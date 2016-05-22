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
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.view.RoundForm;

@Controller
public class RoundController {

	@Resource
	private RoundDao roundDao = new RoundDao();

	@ModelAttribute("roundForm")
	public RoundForm getRoundForm() {
		return new RoundForm();
	}

	@RequestMapping("/editround")
	public String newRound(Model model) {
		model.addAttribute("roundForm", new RoundForm());
		return "editround";
	}

	@RequestMapping(value = "/editround/{id}", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String viewRoundForId(@ModelAttribute("roundForm") RoundForm form, @PathVariable("id") String id) {
		Round round = roundDao.findRoundForId(Long.parseLong(id));
		if (round != null) {
			form.setRound(round);
			return "editround";
		} else {
			return "404";
		}
	}

	@RequestMapping(value = "/editround", method = RequestMethod.POST)
	public String saveRound(@Valid @ModelAttribute("roundForm") RoundForm form, BindingResult result, Model model) {
		return editingResponse(form, result, model, null);
	}

	@RequestMapping(value = "/editround/{id}", method = RequestMethod.POST)
	public String editRound(@Valid @ModelAttribute("roundForm") RoundForm form, BindingResult result,
			@PathVariable("id") String id, Model model) {
		return editingResponse(form, result, model, id);
	}

	@RequestMapping(value = "/deleteround/{id}")
	public String deleteRound(@PathVariable("id") String id) {
		Round round = roundDao.findRoundForId(Long.parseLong(id));
		if (round != null) {
			roundDao.delete(Long.parseLong(id));
			return "redirect:/index";
		} else {
			return "404";
		}
	}

	private String editingResponse(RoundForm form, BindingResult result, Model model, String id) {
		List<String> customErrors = form.validate(form.getRound());
		if (result.hasErrors() || !customErrors.isEmpty()) {
			if (id == null) {
				List<String> errors = new ArrayList<>();
				for (FieldError error : result.getFieldErrors()) {
					errors.add(error.getObjectName() + " - " + error.getDefaultMessage());
				}
				errors.addAll(customErrors);
				form.setRound(form.getRound());
				model.addAttribute("errors", errors);
				return "editround";
			} else {
				List<String> errors = new ArrayList<>();
				for (FieldError error : result.getFieldErrors()) {
					errors.add(error.getObjectName() + " - " + error.getDefaultMessage());
				}
				errors.addAll(customErrors);
				form.setRound(form.getRound());
				model.addAttribute("errors", errors);
				return "editround";
			}
		} else {
			if (id == null) {
				Round newRound = new Round();
				setRoundData(form, newRound);
				roundDao.store(newRound);
				return "redirect://index";
			} else {
				Round round = roundDao.findRoundForId(Long.parseLong(id));
				setRoundData(form, round);
				roundDao.store(round);
				return "redirect://editround/" + id;
			}
		}
	}

	private void setRoundData(RoundForm form, Round round) {
		Round formData = form.getRound();
		round.setRoundName(formData.getRoundName());
		round.setSemester(formData.getSemester());
		round.setSubject(formData.getSubject());
		round.setYear(formData.getYear());
		round.setUrl(formData.getUrl());
	}
}
