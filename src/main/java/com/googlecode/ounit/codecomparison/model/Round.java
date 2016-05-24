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
import javax.persistence.Transient;

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
	@Transient
	private boolean checked;
	@Transient
	private String saveRoundButton;
	@OneToMany(targetEntity = Attempt.class, mappedBy = "round_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<Attempt> attempts = new ArrayList<Attempt>();
	
	public Round() {
	}

	public String formData() {
		return subject;
	}

	public boolean getChecked() {
		return checked;
	}
	
	public Long getId() {
		return id;
	}

	public String getRoundName() {
		return roundName;
	}

	public String getSaveRoundButton() {
		return saveRoundButton;
	}
	
	public String getSemester() {
		return semester;
	}

	public String getSubject() {
		return subject;
	}

	public Long getTask_id() {
		return task_id;
	}

	public int getUrl() {
		return url;
	}

	public String getYear() {
		return year;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public void setSaveRoundButton(String saveRoundButton) {
		this.saveRoundButton = saveRoundButton;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public void setUrl(int url) {
		this.url = url;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Round [id=" + id + ", task_id=" + task_id + ", year=" + year + ", semester=" + semester + ", subject="
				+ subject + ", roundName=" + roundName + ", url=" + url + ", checked=" + checked + ", saveRoundButton="
				+ saveRoundButton + ", attempts=" + attempts + "]";
	}
	
}
