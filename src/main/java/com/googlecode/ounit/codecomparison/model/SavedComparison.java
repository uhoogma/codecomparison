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
	private int t;
	private int k;
	private double comparisonResult;

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

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public double getComparisonResult() {
		return comparisonResult;
	}

	public void setComparisonResult(double comparisonResult) {
		this.comparisonResult = comparisonResult;
	}

	@Override
	public String toString() {
		return "SavedComparison [id=" + id + ", task_id=" + task_id + ", version_id=" + version_id + ", firstStudentId="
				+ firstStudentId + ", secondStudentId=" + secondStudentId + ", firstAttemptId=" + firstAttemptId
				+ ", secondAttemptId=" + secondAttemptId + ", t=" + t + ", k=" + k + ", comparisonResult="
				+ comparisonResult + "]";
	}

}
