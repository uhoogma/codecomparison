package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;

public class TaskForm {

	private Task task;
	private String addTaskButton;
	private List<Round> roundsNotInTask = new ArrayList<>();
	private List<Round> roundsInTask = new ArrayList<>();
	private String lastSyncDifference;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Round> getRoundsInTask() {
		return roundsInTask;
	}

	public void setRoundsInTask(List<Round> roundsInTask) {
		this.roundsInTask = roundsInTask;
	}

	public List<Round> getRoundsNotInTask() {
		return roundsNotInTask;
	}

	public void setRoundsNotInTask(List<Round> roundsNotInTask) {
		this.roundsNotInTask = roundsNotInTask;
	}

	public String getLastSyncDifference() {
		if (task.getLastSyncTime() == null) {
			setLastSyncDifference("Mitte kunagi");
			return lastSyncDifference;
		} else {
			return String.format("%.3f h tagasi", (System.currentTimeMillis() - task.getLastSyncTime().getTime()) / 3600000.0);
		}
	}

	public void setLastSyncDifference(String lastSyncDifference) {
		this.lastSyncDifference = lastSyncDifference;
	}

	public String getAddTaskButton() {
		return addTaskButton;
	}

	public void setAddTaskButton(String addTaskButton) {
		this.addTaskButton = addTaskButton;
	}

}
