package com.googlecode.ounit.codecomparison.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int moodleId;
	private String fullName;

	@OneToMany(targetEntity = Attempt.class, mappedBy = "student_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<Attempt> attempts = new ArrayList<Attempt>();

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "firstStudentId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> firstStudent = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "secondStudentId", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> secondStudent = new ArrayList<SavedComparison>();

	public Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMoodleId() {
		return moodleId;
	}

	public void setMoodleId(int moodleId) {
		this.moodleId = moodleId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", moodleId=" + moodleId + ", fullName=" + fullName + ", attempts=" + attempts
				+ ", firstStudent=" + firstStudent + ", secondStudent=" + secondStudent + "]";
	}

}
