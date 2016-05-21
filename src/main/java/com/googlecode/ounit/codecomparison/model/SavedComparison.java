package com.googlecode.ounit.codecomparison.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SavedComparison {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long task_id;
	private Long version_id;
	private Integer firstStudentId;
	private Integer secondStudentId;
	private Integer firstAttemptId;
	private Integer secondAttemptId;
	private double firstToSecondResult;
	private double secondToFirstResult;
	private boolean firstToSecondIsInfinite;
	private boolean secondToFirstIsInfinite;
	@Transient
	private String largestSimilarityResultAsString;
	@Transient
	private String smallestSimilarityResultAsString;
	
	public SavedComparison() {
	}

	public Integer getFirstAttemptId() {
		return firstAttemptId;
	}
	
	public Integer getFirstStudentId() {
		return firstStudentId;
	}

	public double getFirstToSecondResult() {
		return firstToSecondResult;
	}

	public String getFirstToSecondResultAsString() {
		return firstToSecondIsInfinite ? "Infinity" : String.valueOf(firstToSecondResult);
	}

	public Long getId() {
		return id;
	}

	public Double getLargestSimilarityResult() {
		return (secondToFirstResult > firstToSecondResult) ? secondToFirstResult : firstToSecondResult;
	}

	public String getLargestSimilarityResultAsString() {
		return String.valueOf(getLargestSimilarityResult());
	}

	public Integer getSecondAttemptId() {
		return secondAttemptId;
	}

	public Integer getSecondStudentId() {
		return secondStudentId;
	}

	public double getSecondToFirstResult() {
		return secondToFirstResult;
	}

	public String getSecondToFirstResultAsString() {
		return secondToFirstIsInfinite ? "Infinity" : String.valueOf(secondToFirstResult);
	}

	public Double getSmallestSimilarityResult() {
		return (firstToSecondResult < secondToFirstResult) ? firstToSecondResult : secondToFirstResult ;
	}

	public String getSmallestSimilarityResultAsString() {
		return String.valueOf(getSmallestSimilarityResult());
	}

	public Long getTask_id() {
		return task_id;
	}

	public Long getVersion_id() {
		return version_id;
	}

	public boolean isFirstToSecondIsInfinite() {
		return firstToSecondIsInfinite;
	}

	public boolean isSecondToFirstIsInfinite() {
		return secondToFirstIsInfinite;
	}

	public void setFirstAttemptId(Integer firstAttemptId) {
		this.firstAttemptId = firstAttemptId;
	}

	public void setFirstStudentId(Integer firstStudentId) {
		this.firstStudentId = firstStudentId;
	}


	public void setFirstToSecondIsInfinite(boolean firstToSecondIsInfinite) {
		this.firstToSecondIsInfinite = firstToSecondIsInfinite;
	}

	public void setFirstToSecondResult(double firstToSecondResult) {
		this.firstToSecondResult = firstToSecondResult;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLargestSimilarityResultAsString(String largestSimilarityResultAsString) {
		this.largestSimilarityResultAsString = largestSimilarityResultAsString;
	}

	public void setSecondAttemptId(Integer secondAttemptId) {
		this.secondAttemptId = secondAttemptId;
	}

	public void setSecondStudentId(Integer secondStudentId) {
		this.secondStudentId = secondStudentId;
	}

	public void setSecondToFirstIsInfinite(boolean secondToFirstIsInfinite) {
		this.secondToFirstIsInfinite = secondToFirstIsInfinite;
	}

	public void setSecondToFirstResult(double secondToFirstResult) {
		this.secondToFirstResult = secondToFirstResult;
	}

	public void setSmallestSimilarityResultAsString(String smallestSimilarityResultAsString) {
		this.smallestSimilarityResultAsString = smallestSimilarityResultAsString;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
	}

	@Override
	public String toString() {
		return "SavedComparison [id=" + id + ", task_id=" + task_id + ", version_id=" + version_id + ", firstStudentId="
				+ firstStudentId + ", secondStudentId=" + secondStudentId + ", firstAttemptId=" + firstAttemptId
				+ ", secondAttemptId=" + secondAttemptId + ", firstToSecondResult=" + firstToSecondResult
				+ ", secondToFirstResult=" + secondToFirstResult + ", firstToSecondIsInfinite="
				+ firstToSecondIsInfinite + ", secondToFirstIsInfinite=" + secondToFirstIsInfinite
				+ ", firstToSecondResultAsString="  + ", secondToFirstResultAsString="
				 + "]";
	}
}
