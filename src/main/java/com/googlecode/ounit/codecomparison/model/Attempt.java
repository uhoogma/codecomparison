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
	private Integer moodleId;
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

	public Attempt(Long task_id, Long round_id, Integer moodleId, Integer student_id) {
		this.moodleId = moodleId;
		this.task_id = task_id;
		this.round_id = round_id;
		this.student_id = student_id;
	}

	public Attempt(Long taskId, String originalFilename, String code, boolean codeAcquired, boolean isBoilerplate) {
		this.task_id = taskId;
		this.fileName = originalFilename;
		this.code = code;
		this.codeAcquired = codeAcquired;
		this.isBoilerplate = isBoilerplate;
	}

	public List<AbstractedCode> getAbstractedCodes() {
		return abstractedCodes;
	}

	public String getCode() {
		return code;
	}

	public String getFileName() {
		return fileName;
	}

	public Long getId() {
		return id;
	}

	public Integer getMoodleId() {
		return moodleId;
	}

	public Long getRound_id() {
		return round_id;
	}

	public Integer getStudentId() {
		return student_id;
	}

	public Long getTask_id() {
		return task_id;
	}

	public boolean isBoilerplate() {
		return isBoilerplate;
	}

	public boolean isBoilerPlate() {
		return isBoilerplate;
	}

	public boolean isCodeAcquired() {
		return codeAcquired;
	}

	public void setAbstractedCodes(List<AbstractedCode> abstractedCodes) {
		this.abstractedCodes = abstractedCodes;
	}

	public void setBoilerplate(boolean isBoilerplate) {
		this.isBoilerplate = isBoilerplate;
	}

	public void setBoilerPlate(boolean isBoilerPlate) {
		this.isBoilerplate = isBoilerPlate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCodeAcquired(boolean codeAcquired) {
		this.codeAcquired = codeAcquired;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMoodleId(Integer moodleId) {
		this.moodleId = moodleId;
	}

	public void setRound_id(Long round_id) {
		this.round_id = round_id;
	}

	public void setStudentId(Integer studentId) {
		this.student_id = studentId;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	@Override
	public String toString() {
		return "Attempt [id=" + id + ", moodleId=" + moodleId + ", task_id=" + task_id + ", round_id=" + round_id
				+ ", student_id=" + student_id + ", code=" + code + ", fileName=" + fileName + ", codeAcquired="
				+ codeAcquired + ", isBoilerplate=" + isBoilerplate + ", firstAttempt=" + firstAttempt
				+ ", secondAttempt=" + secondAttempt + ", abstractedCodes=" + abstractedCodes + "]";
	}

}
