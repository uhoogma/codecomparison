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
	private Long moodleId;
	private Long task_id;
	private Long round_id;
	private Integer student_id;
	@Column(columnDefinition = "TEXT")
	private String code;
	private String fileName;
	private boolean codeAcquired;
	private boolean isBoilerplate;

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "firstAttemptId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> firstAttempt = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "secondAttemptId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> secondAttempt = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = AbstractedCode.class, mappedBy = "attempt_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<AbstractedCode> abstractedCodes = new ArrayList<AbstractedCode>();

	public Attempt() {
	}

	public Attempt(Long task_id, Long round_id, Long moodleId, Integer student_id) {
		this.moodleId = moodleId;
		this.task_id = task_id;
		this.round_id = round_id;
		this.student_id = student_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return student_id;
	}

	public void setStudentId(Integer studentId) {
		this.student_id = studentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public Long getMoodleId() {
		return moodleId;
	}

	public void setMoodleId(Long moodleId) {
		this.moodleId = moodleId;
	}

	@Override
	public String toString() {
		return "Attempt [id=" + id + ", moodleId=" + moodleId + ", task_id=" + task_id + ", round_id=" + round_id
				+ ", student_id=" + student_id + ", code=" + code + ", fileName=" + fileName + ", codeAcquired="
				+ codeAcquired + ", isBoilerplate=" + isBoilerplate + ", firstAttempt=" + firstAttempt
				+ ", secondAttempt=" + secondAttempt + ", abstractedCodes=" + abstractedCodes + "]";
	}

}
