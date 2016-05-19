package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.List;

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

	public String getAddTaskButton() {
		return addTaskButton;
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

	public void setComparisons(List<SavedComparison> comparisons) {
		this.comparisons = comparisons;
	}

	public void setLastSyncDifference(String lastSyncDifference) {
		this.lastSyncDifference = lastSyncDifference;
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

}
