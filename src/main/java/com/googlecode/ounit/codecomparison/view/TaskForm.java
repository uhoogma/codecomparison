package com.googlecode.ounit.codecomparison.view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;

public class TaskForm {
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	List<Round> roundsNotInTask = new ArrayList<>();
	List<Round> roundsInTask = new ArrayList<>();

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

	public String getAddTaskButton() {
		return addTaskButton;
	}

	public void setAddTaskButton(String addTaskButton) {
		this.addTaskButton = addTaskButton;
	}

	private Task task;

	private String addTaskButton;
}
