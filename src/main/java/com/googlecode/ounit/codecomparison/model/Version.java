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
public class Version {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int abstractionVersionId;
	private int similarityVersionId;
	private int defaultT;
	private int defaultK;

	@OneToMany(targetEntity = SavedComparison.class, mappedBy = "version_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<SavedComparison> savedComparisons = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = AbstractedCode.class, mappedBy = "version_id", fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private List<AbstractedCode> abstractedCodes = new ArrayList<AbstractedCode>();

	public Version() {
	}

	public int getAbstractionVersionId() {
		return abstractionVersionId;
	}

	public int getDefaultK() {
		return defaultK;
	}

	public int getDefaultT() {
		return defaultT;
	}

	public Long getId() {
		return id;
	}

	public int getSimilarityVersionId() {
		return similarityVersionId;
	}

	public void setAbstractionVersionId(int abstractionVersionId) {
		this.abstractionVersionId = abstractionVersionId;
	}

	public void setDefaultK(int defaultK) {
		this.defaultK = defaultK;
	}

	public void setDefaultT(int defaultT) {
		this.defaultT = defaultT;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSimilarityVersionId(int similarityVersionId) {
		this.similarityVersionId = similarityVersionId;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", abstractionVersionId=" + abstractionVersionId + ", similarityVersionId="
				+ similarityVersionId + ", defaultT=" + defaultT + ", defaultK=" + defaultK + ", savedComparisons="
				+ savedComparisons + ", abstractedCodes=" + abstractedCodes + "]";
	}

}
