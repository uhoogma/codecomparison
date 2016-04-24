package com.googlecode.ounit.codecomparison.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class AbstractedCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long attempt_id;
	private Long version_id;
	@Column(columnDefinition = "TEXT")
	private String abstractedCode;

	@OneToMany(targetEntity = Hash.class, fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "abstractedCode_id")
	private List<Hash> hashes = new ArrayList<Hash>();

	public AbstractedCode() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAttempt_id() {
		return attempt_id;
	}

	public void setAttempt_id(Long attempt_id) {
		this.attempt_id = attempt_id;
	}

	public Long getVersion_id() {
		return version_id;
	}

	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
	}

	public String getAbstractedCode() {
		return abstractedCode;
	}

	public void setAbstractedCode(String abstractedCode) {
		this.abstractedCode = abstractedCode;
	}

	@Override
	public String toString() {
		return "AbstractedCode [id=" + id + ", attempt_id=" + attempt_id + ", version_id=" + version_id
				+ ", abstractedCode=" + abstractedCode + ", hashes=" + hashes + "]";
	}

}
