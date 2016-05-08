package com.googlecode.ounit.codecomparison.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SavedComparison {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long task_id;
	private Long version_id;
	private Long firstStudentId;
	private Long secondStudentId;
	private Long firstAttemptId;
	private Long secondAttemptId;
	private double firstToSecondResult;
	private double secondToFirstResult;
	private boolean firstToSecondIsInfinite;
	private boolean secondToFirstIsInfinite;

	public SavedComparison() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public Long getVersion_id() {
		return version_id;
	}

	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
	}

	public Long getFirstStudentId() {
		return firstStudentId;
	}

	public void setFirstStudentId(Long firstStudentId) {
		this.firstStudentId = firstStudentId;
	}

	public Long getSecondStudentId() {
		return secondStudentId;
	}

	public void setSecondStudentId(Long secondStudentId) {
		this.secondStudentId = secondStudentId;
	}

	public Long getFirstAttemptId() {
		return firstAttemptId;
	}

	public void setFirstAttemptId(Long firstAttemptId) {
		this.firstAttemptId = firstAttemptId;
	}

	public Long getSecondAttemptId() {
		return secondAttemptId;
	}

	public void setSecondAttemptId(Long secondAttemptId) {
		this.secondAttemptId = secondAttemptId;
	}

	public double getFirstToSecondResult() {
		return firstToSecondResult;
	}

	public void setFirstToSecondResult(double firstToSecondResult) {
		this.firstToSecondResult = firstToSecondResult;
	}

	public double getSecondToFirstResult() {
		return secondToFirstResult;
	}

	public void setSecondToFirstResult(double secondToFirstResult) {
		this.secondToFirstResult = secondToFirstResult;
	}

	public boolean isFirstToSecondIsInfinite() {
		return firstToSecondIsInfinite;
	}

	public void setFirstToSecondIsInfinite(boolean firstToSecondIsInfinite) {
		this.firstToSecondIsInfinite = firstToSecondIsInfinite;
	}

	public boolean isSecondToFirstIsInfinite() {
		return secondToFirstIsInfinite;
	}

	public void setSecondToFirstIsInfinite(boolean secondToFirstIsInfinite) {
		this.secondToFirstIsInfinite = secondToFirstIsInfinite;
	}

	@Override
	public String toString() {
		return "SavedComparison [id=" + id + ", task_id=" + task_id + ", version_id=" + version_id + ", firstStudentId="
				+ firstStudentId + ", secondStudentId=" + secondStudentId + ", firstAttemptId=" + firstAttemptId
				+ ", secondAttemptId=" + secondAttemptId + ", firstToSecondResult=" + firstToSecondResult
				+ ", secondToFirstResult=" + secondToFirstResult + ", firstToSecondIsInfinite="
				+ firstToSecondIsInfinite + ", secondToFirstIsInfinite=" + secondToFirstIsInfinite + "]";
	}

}
