package com.googlecode.ounit.codecomparison.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.ounit.codecomparison.dao.PersonDao;
import com.googlecode.ounit.codecomparison.dao.RoundDao;
import com.googlecode.ounit.codecomparison.dao.TaskDao;
import com.googlecode.ounit.codecomparison.model.Person;
import com.googlecode.ounit.codecomparison.model.Phone;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;
import com.googlecode.ounit.codecomparison.view.PersonForm;
import com.googlecode.ounit.codecomparison.view.RoundForm;
import com.googlecode.ounit.codecomparison.view.TaskForm;

@Controller
public class PersonController {

	@Resource
	private PersonDao personDao = new PersonDao();
	@Resource
	private RoundDao roundDao = new RoundDao();
	@Resource
	private TaskDao taskDao = new TaskDao();

	@ModelAttribute("personForm")
	public PersonForm getUserObject() {
		return new PersonForm();
	}

	/*
	 * @ModelAttribute("roundForm") public RoundForm getUserObject2() { return
	 * new RoundForm(); }
	 */
	@RequestMapping("/")
	public String home() {
		return "redirect:/search";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping(value="/edittest/{taskId}", method=RequestMethod.GET)
	public String edittest(@ModelAttribute("taskForm") TaskForm form, @PathVariable("taskId") String taskId,
			BindingResult result, ModelMap model) {
		Task t = taskDao.findTaskForId(Long.parseLong(taskId));
		List<Round> asd = roundDao.getRoundsNotInTask(Long.parseLong(taskId));
		System.out.println("asd"+asd.toString());
		// model.addAttribute("roundsNotInTask", asd);
		form.setRoundsNotInTask(asd);		
		return "edittest";

	}

	@RequestMapping(value="/edittest/{taskId}", method=RequestMethod.POST)
	public String saveTask(@ModelAttribute("taskForm") TaskForm form, @PathVariable("taskId") String taskId, BindingResult result, ModelMap model) {
	
		Task t = taskDao.findTaskForId(Long.parseLong(taskId));

		List<Round> asd = form.getRoundsNotInTask();
		List<Long> roundsToAdd = new ArrayList<>();

		for (int i = 0; i < asd.size(); i++) {
			if (asd.get(i).getChecked()) {
				System.out.println("id on"+asd.get(i).toString());
				roundsToAdd.add(asd.get(i).getId());
			}
		}
		System.out.println(roundsToAdd.size() + "roundsToAdd.size()");
		List<Round> rrr = new ArrayList<>();

		for (int i = 0; i < roundsToAdd.size(); i++) {
			rrr.add(roundDao.findRoundForId(roundsToAdd.get(i)));
		}
		taskDao.addRounds(t, rrr);
		// roundDao.addTask(6L, taskDao.findTaskForId(1L));

		/*
		 * List<Round> roundsNotInTask = new ArrayList<>(); roundsNotInTask =
		 * roundDao.findAllRounds(); model.addAttribute("roundsNotInTask",
		 * roundsNotInTask);
		 */
		taskDao.save(t);
		System.out.println(taskDao.findTaskForId(Long.parseLong(taskId)).toString());
		// edittest(form, taskId, result, model);
		return "redirect:/edittest/"+t.getId();
	}

	@RequestMapping("/comparison")
	public String comparison() {
		return "comparison";
	}

	@RequestMapping("/editround")
	public String editround(@ModelAttribute("roundForm") RoundForm form) {
		prepareRound(form);
		return "editround";
	}

	@RequestMapping("/saveRound")
	public String saveRound(@ModelAttribute("roundForm") @Valid RoundForm form, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			List<String> err = new ArrayList<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				err.add(error.getObjectName() + " - " + error.getDefaultMessage());
				System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("errors", err);
			return "editround";
		} else {
			roundDao.store(form.getRound());
			return "edittest";
		}

	}

	@RequestMapping(value = { "/search?searchString={code}", "/search" })
	public String personList(ModelMap model,
			@RequestParam(name = "searchString", required = false) String searchPhrase) {
		List<Person> persons = new ArrayList<>();
		if (searchPhrase != null && !searchPhrase.isEmpty()) {
			persons = personDao.filterPersons(searchPhrase);
		} else {
			persons = personDao.findAllPersons();
		}
		model.addAttribute("persons", persons);
		return "search";
	}

	@RequestMapping("/addForm")
	public String add(@ModelAttribute("personForm") PersonForm form) {
		prepareForm(form, false);
		return "add";
	}

	@RequestMapping("/save")
	public String saveForm(@ModelAttribute("personForm") PersonForm form) {
		Person person = form.getPerson();
		if (form.getAddPhoneButton() != null) {
			person.addPhone(new Phone());
			prepareForm(form, false);
			return "add";
		} else if (person.getPhoneWithDeletePressed() != null) {
			person.removePhone(person.getPhoneWithDeletePressed());
			prepareForm(form, false);
			return "add";
		} else {
			personDao.store(form.getPerson());
			return "redirect:/search";
		}
	}

	@RequestMapping("/view/{code}")
	public String view(@ModelAttribute("personForm") PersonForm form, @PathVariable("code") String code,
			ModelMap model) {
		form.setPerson(personDao.findPersonForCode(code));
		prepareForm(form, true);
		return "add";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		personDao.delete(id);
		return "redirect:/search";
	}

	@RequestMapping("/clear")
	public String clear() {
		personDao.truncatePersons();
		return "redirect:/search";
	}

	private void prepareForm(PersonForm form, boolean disabled) {
		form.setCustomerGroups(personDao.getCustomerGroups());
		form.setPhoneTypes(personDao.getPhoneTypes());
		form.setDisabled(disabled);
	}

	private void prepareRound(RoundForm form) {
		// form.setCustomerGroups(personDao.getCustomerGroups());
		// form.setPhoneTypes(personDao.getPhoneTypes());
	}
}
