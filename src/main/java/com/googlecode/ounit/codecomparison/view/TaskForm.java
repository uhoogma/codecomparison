package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.SavedComparison;
import com.googlecode.ounit.codecomparison.model.Task;

public class TaskForm {

	private Task task;
	private String addTaskButton;
	private List<Round> roundsNotInTask = new ArrayList<>();
	private List<Round> roundsInTask = new ArrayList<>();
	private String lastSyncDifference;
	private List<SavedComparison> comparisons = new ArrayList<>();
	private Integer sequentialNumber = 0;
	private Map<Integer, Integer> pages = new LinkedHashMap<>();
	private String chartScript;
	private int resultCount;
	private int studentCount;
	private int attemptCount;
	private int unfetchedAttemptCount;

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public int getAttemptCount() {
		return attemptCount;
	}

	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}

	public int getUnfetchedAttemptCount() {
		return unfetchedAttemptCount;
	}

	public void setUnfetchedAttemptCount(int unfetchedAttemptCount) {
		this.unfetchedAttemptCount = unfetchedAttemptCount;
	}

	public String getAddTaskButton() {
		return addTaskButton;
	}

	public String getChartScript() {
		return chartScript;
	}

	public List<SavedComparison> getComparisons() {
		return comparisons;
	}

	public String getLastSyncDifference() {
		if (task.getLastSyncTime() == null) {
			setLastSyncDifference("Mitte kunagi");
			return lastSyncDifference;
		} else {
			return String.format("%.3f h tagasi",
					(System.currentTimeMillis() - task.getLastSyncTime().getTime()) / 3600000.0);
		}
	}

	public Map<Integer, Integer> getPages() {
		return pages;
	}

	public int getResultCount() {
		return resultCount;
	}

	public List<Round> getRoundsInTask() {
		return roundsInTask;
	}

	public List<Round> getRoundsNotInTask() {
		return roundsNotInTask;
	}

	public Integer getSequentialNumber() {
		return sequentialNumber;
	}

	public Task getTask() {
		return task;
	}

	public void setAddTaskButton(String addTaskButton) {
		this.addTaskButton = addTaskButton;
	}

	public void setChartScript(String chartScript) {
		this.chartScript = chartScript;
	}

	public void setComparisons(List<SavedComparison> comparisons) {
		this.comparisons = comparisons;
	}

	public void setLastSyncDifference(String lastSyncDifference) {
		this.lastSyncDifference = lastSyncDifference;
	}

	public void setPages(Map<Integer, Integer> pages) {
		this.pages = pages;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public void setRoundsInTask(List<Round> roundsInTask) {
		this.roundsInTask = roundsInTask;
	}

	public void setRoundsNotInTask(List<Round> roundsNotInTask) {
		this.roundsNotInTask = roundsNotInTask;
	}

	public void setSequentialNumber(Integer sequentialNumber) {
		this.sequentialNumber = sequentialNumber;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<String> validate(Task task) {
		List<String> errors = new ArrayList<>();
		if (task.getTaskName().length() > 50) {
			errors.add("Testi nimi liiga pikk, ei tohi ületada 50 tähemärki");
		}
		return errors;
	}

}
