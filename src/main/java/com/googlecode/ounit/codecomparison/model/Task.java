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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskName;
	private boolean active;
	private Timestamp creationTime;
	private Timestamp lastSyncTime;
	private int t;
	private int k;

	@Transient
	private String fileName;

	@OneToMany(targetEntity = Round.class, mappedBy = "task_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<Round> rounds = new ArrayList<Round>();

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "task_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> savedComparisons = new ArrayList<SavedComparison>();

	@OneToOne
	@JoinColumn(name = "id")
	private Attempt attempt;

	public Task() {
	}

	public void addRound(Round r1) {
		rounds.add(r1);
	}

	public Attempt getAttempt() {
		return attempt;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public String getFileName() {
		return fileName;
	}

	public Long getId() {
		return id;
	}

	public int getK() {
		return k;
	}

	public Timestamp getLastSyncTime() {
		return lastSyncTime;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public int getT() {
		return t;
	}

	public String getTaskName() {
		return taskName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setK(int k) {
		this.k = k;
	}

	public void setLastSyncTime(Timestamp lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public void setT(int t) {
		this.t = t;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", active=" + active + ", creationTime=" + creationTime
				+ ", lastSyncTime=" + lastSyncTime + ", t=" + t + ", k=" + k + ", fileName=" + fileName + ", rounds="
				+ rounds + ", savedComparisons=" + savedComparisons + ", attempt=" + attempt + "]";
	}	

}
