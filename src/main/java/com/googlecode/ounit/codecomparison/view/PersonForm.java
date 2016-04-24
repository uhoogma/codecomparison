package com.googlecode.ounit.codecomparison.view;

import java.util.Map;

import com.googlecode.ounit.codecomparison.model.Person;

public class PersonForm {

	private Person person;
	private boolean disabled;
	private Map<String, String> customerGroups;
	private Map<String, String> phoneTypes;
	private String addButton;
	private String addPhoneButton;

	public String getAddButton() {
		return addButton;
	}

	public void setAddButton(String addButton) {
		this.addButton = addButton;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Map<String, String> getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(Map<String, String> ageGroups) {
		this.customerGroups = ageGroups;
	}

	public Map<String, String> getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(Map<String, String> phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

	public String getAddPhoneButton() {
		return addPhoneButton;
	}

	public void setAddPhoneButton(String addPhoneButton) {
		this.addPhoneButton = addPhoneButton;
	}

	public boolean wasSavePressed() {
		return addButton != null;
	}

	public boolean wasAddPressed() {
		return addPhoneButton != null;
	}
}
