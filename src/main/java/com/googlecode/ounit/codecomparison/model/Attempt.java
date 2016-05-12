package com.googlecode.ounit.codecomparison.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Attempt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long round_id;
	private Long student_id;
	@Column(columnDefinition = "TEXT")
	private String code;
	private boolean codeAcquired;
	private boolean isBoilerplate;

	@OneToMany(targetEntity = SavedComparison.class, mappedBy="firstAttemptId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> firstAttempt = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = SavedComparison.class,mappedBy="secondAttemptId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> secondAttempt = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = AbstractedCode.class,mappedBy="attempt_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<AbstractedCode> abstractedCodes = new ArrayList<AbstractedCode>();

	public Attempt() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return student_id;
	}

	public void setStudentId(Long studentId) {
		this.student_id = studentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isCodeAcquired() {
		return codeAcquired;
	}

	public void setCodeAcquired(boolean codeAcquired) {
		this.codeAcquired = codeAcquired;
	}

	public boolean isBoilerPlate() {
		return isBoilerplate;
	}

	public void setBoilerPlate(boolean isBoilerPlate) {
		this.isBoilerplate = isBoilerPlate;
	}

	public Long getRound_id() {
		return round_id;
	}

	public void setRound_id(Long round_id) {
		this.round_id = round_id;
	}

	@Override
	public String toString() {
		return "Attempt [id=" + id + ", round_id=" + round_id + ", student_id=" + student_id + ", code=" + code
				+ ", codeAcquired=" + codeAcquired + ", isBoilerPlate=" + isBoilerplate + ", firstAttempt="
				+ firstAttempt + ", secondAttempt=" + secondAttempt + ", abstractedCodes=" + abstractedCodes + "]";
	}
	
}
