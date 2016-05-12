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
	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	private String year;
	private String semester;
	private String subject;
	private String roundName;
	private int url;

	@Transient
	private String saveRoundButton;
	@Transient
	private String deleteRoundButton;
	
	public String getDeleteRoundButton() {
		return deleteRoundButton;
	}

	public void setDeleteRoundButton(String deleteRoundButton) {
		this.deleteRoundButton = deleteRoundButton;
	}

	public String getSaveRoundButton() {
		return saveRoundButton;
	}

	public void setSaveRoundButton(String saveRoundButton) {
		this.saveRoundButton = saveRoundButton;
	}
	
	@Transient
	private boolean checked;

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Round() {
	}

	@OneToMany(targetEntity = Attempt.class, mappedBy = "round_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
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
				+ subject + ", roundName=" + roundName + ", url=" + url + ", saveRoundButton=" + saveRoundButton
				+ ", deleteRoundButton=" + deleteRoundButton + ", checked=" + checked + ", attempts=" + attempts + "]";
	}
	
}
