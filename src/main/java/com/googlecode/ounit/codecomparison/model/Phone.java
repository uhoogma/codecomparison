package com.googlecode.ounit.codecomparison.model;

import javax.persistence.*;

@Entity
public class Phone {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String number;

	private String phoneType;

	@Transient
	private String deleteButton;
	
	public Phone() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(String deleteButton) {
		this.deleteButton = deleteButton;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", phoneType=" + phoneType + "]";
	}
}
