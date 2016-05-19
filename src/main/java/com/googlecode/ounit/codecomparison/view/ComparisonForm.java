package com.googlecode.ounit.codecomparison.view;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.SavedComparison;
import com.googlecode.ounit.codecomparison.model.Student;

public class ComparisonForm {

	private Student firstStudent;

	private Student secondStudent;

	private Round firstRound;

	private Round secondRound;
	private String returnLink;
	
	private SavedComparison savedComparison;
	private String firstCode;
	private String secondCode;
	public String getFirstCode() {
		return firstCode;
	}

	public Round getFirstRound() {
		return firstRound;
	}

	public Student getFirstStudent() {
		return firstStudent;
	}

	public String getReturnLink() {
		return returnLink;
	}

	public SavedComparison getSavedComparison() {
		return savedComparison;
	}

	public String getSecondCode() {
		return secondCode;
	}

	public Round getSecondRound() {
		return secondRound;
	}

	public Student getSecondStudent() {
		return secondStudent;
	}

	public void Round(Round firstRound) {
		this.firstRound = firstRound;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public void setFirstRound(Round firstRound) {
		this.firstRound = firstRound;
	}
	public void setFirstStudent(Student firstStudent) {
		this.firstStudent = firstStudent;
	}
	public void setReturnLink(String returnLink) {
		this.returnLink = returnLink;
	}

	public void setSavedComparison(SavedComparison savedComparison) {
		this.savedComparison = savedComparison;
	}

	public void setSecondCode(String secondCode) {
		this.secondCode = secondCode;
	}

	public void setSecondRound(Round secondRound) {
		this.secondRound = secondRound;
	}

	public void setSecondStudent(Student secondStudent) {
		this.secondStudent = secondStudent;
	}
}
