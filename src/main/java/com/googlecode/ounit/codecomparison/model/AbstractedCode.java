package com.googlecode.ounit.codecomparison.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AbstractedCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long attempt_id;
	private Long task_id;
	private Long version_id;
	@Column(columnDefinition = "TEXT")
	private String abstractedCode;

	public AbstractedCode() {
	}

	public AbstractedCode(Long taskId, Long boilerplateId, Long versionId, String abstractedCode) {
		this.task_id = taskId;
		this.attempt_id = boilerplateId;
		this.version_id = versionId;
		this.abstractedCode = abstractedCode;
	}

	public String getAbstractedCode() {
		return abstractedCode;
	}

	public Long getAttempt_id() {
		return attempt_id;
	}

	public Long getId() {
		return id;
	}

	public Long getTask_id() {
		return task_id;
	}

	public Long getVersion_id() {
		return version_id;
	}

	public void setAbstractedCode(String abstractedCode) {
		this.abstractedCode = abstractedCode;
	}

	public void setAttempt_id(Long attempt_id) {
		this.attempt_id = attempt_id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
	}

	@Override
	public String toString() {
		return "AbstractedCode [id=" + id + ", attempt_id=" + attempt_id + ", task_id=" + task_id + ", version_id="
				+ version_id + ", abstractedCode=" + abstractedCode + "]";
	}

}
