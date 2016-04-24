package com.googlecode.ounit.codecomparison.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String givenName;
	private String sureName;
	private String code;
	private String customerType;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="PERSON_PHONE")
	private List<Phone> phones = new ArrayList<Phone>();
	
	public Person() {}

	public List<Phone> getPhones() {
		return phones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", givenName=" + givenName + ", sureName=" + sureName + ", code=" + code
				+ ", customerType=" + customerType + ", phones=" + phones + "]";
	}
	
	public Phone getPhoneWithDeletePressed() {
		for (Phone phone : phones) {
			if (phone.getDeleteButton() != null) {
				return phone;
			}
		}
		return null;
	}
	
	public void addPhone(Phone phone) {
		phones.add(phone);
	}
	
	public void removePhone(Phone phone) {
		phones.remove(phone);
	}

}
