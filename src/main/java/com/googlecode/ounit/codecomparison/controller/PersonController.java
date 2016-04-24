package com.googlecode.ounit.codecomparison.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.ounit.codecomparison.dao.PersonDao;
import com.googlecode.ounit.codecomparison.model.Person;
import com.googlecode.ounit.codecomparison.model.Phone;
import com.googlecode.ounit.codecomparison.view.PersonForm;

@Controller
public class PersonController {

	@Resource
	private PersonDao personDao = new PersonDao();

	@ModelAttribute("personForm")
	public PersonForm getUserObject() {
		return new PersonForm();
	}

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

	@RequestMapping("/edittest")
	public String edittest() {
		return "edittest";
	}

	@RequestMapping("/comparison")
	public String comparison() {
		return "comparison";
	}

	@RequestMapping("/editround")
	public String editround() {
		return "editround";
	}
	

	@RequestMapping(value = { "/search?searchString={code}", "/search" })
	public String personList(ModelMap model, @RequestParam(name = "searchString", required = false) String searchPhrase) {
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
}
