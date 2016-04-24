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
public class Version {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int abstractionVersionId;
	private int SimilarityVersionId;

	@OneToMany(targetEntity = SavedComparison.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "version_id")
	private List<SavedComparison> savedComparisons = new ArrayList<SavedComparison>();

	@OneToMany(targetEntity = AbstractedCode.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "version_id")
	private List<AbstractedCode> abstractedCodes = new ArrayList<AbstractedCode>();

	@OneToMany(targetEntity = Hash.class, fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "version_id")
	private List<Hash> hashes = new ArrayList<Hash>();

	public Version() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAbstractionVersionId() {
		return abstractionVersionId;
	}

	public void setAbstractionVersionId(int abstractionVersionId) {
		this.abstractionVersionId = abstractionVersionId;
	}

	public int getSimilarityVersionId() {
		return SimilarityVersionId;
	}

	public void setSimilarityVersionId(int similarityVersionId) {
		SimilarityVersionId = similarityVersionId;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", abstractionVersionId=" + abstractionVersionId + ", SimilarityVersionId="
				+ SimilarityVersionId + ", savedComparisons=" + savedComparisons + ", abstractedCodes="
				+ abstractedCodes + ", hashes=" + hashes + "]";
	}

}
