package com.googlecode.ounit.codecomparison.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long abstractedCode_id;
	private Long version_id;
	private int hashPosition;
	private int hashValue;

	public Hash() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAbstractedCode_id() {
		return abstractedCode_id;
	}

	public void setAbstractedCode_id(Long abstractedCode_id) {
		this.abstractedCode_id = abstractedCode_id;
	}

	public Long getVersion_id() {
		return version_id;
	}

	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
	}

	public int getHashPosition() {
		return hashPosition;
	}

	public void setHashPosition(int hashPosition) {
		this.hashPosition = hashPosition;
	}

	public int getHashValue() {
		return hashValue;
	}

	public void setHashValue(int hashValue) {
		this.hashValue = hashValue;
	}

	@Override
	public String toString() {
		return "Hash [id=" + id + ", abstractedCode_id=" + abstractedCode_id + ", version_id=" + version_id
				+ ", hashPosition=" + hashPosition + ", hashValue=" + hashValue + "]";
	}

}
