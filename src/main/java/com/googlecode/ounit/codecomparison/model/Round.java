package com.googlecode.ounit.codecomparison.model;

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
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long task_id;
	private String year;
	private String semester;
	private String subject;
	private String roundName;
	private int url;

	public Round() {
	}

	@OneToMany(targetEntity = Attempt.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "round_id")
	private List<Attempt> attempts = new ArrayList<Attempt>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public int getUrl() {
		return url;
	}

	public void setUrl(int url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Round [id=" + id + ", task_id=" + task_id + ", year=" + year + ", semester=" + semester + ", subject="
				+ subject + ", roundName=" + roundName + ", url=" + url + ", attempts=" + attempts + "]";
	}

}
