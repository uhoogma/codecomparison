package com.googlecode.ounit.codecomparison.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskName;
	private boolean active;
	private Timestamp creationTime;
	private Timestamp lastSyncTime;

	@OneToMany(targetEntity = Round.class, mappedBy="task_id", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	// @JoinColumn(name = "task_id")
	private List<Round> rounds = new ArrayList<Round>();

	@OneToMany(targetEntity = SavedComparison.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "task_id")
	private List<SavedComparison> savedComparisons = new ArrayList<SavedComparison>();

	public Task() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Timestamp lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", active=" + active + ", creationTime=" + creationTime
				+ ", lastSyncTime=" + lastSyncTime + ", rounds=" + rounds + ", savedComparisons=" + savedComparisons
				+ "]";
	}

	public void addRound(Round r1) {
		rounds.add(r1);
	}

}
