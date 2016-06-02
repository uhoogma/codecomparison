package com.googlecode.ounit.codecomparison.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
// import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class VersionDao {

	@PersistenceContext
	private EntityManager em;

	public int getDefaultT() {
		// Query query = em.createQuery("select defaultT from Version v where v.id = (SELECT max(v.id) FROM Version v)");
		return 39; //(int) query.getSingleResult();
	}

	public int getDefaultK() {
		// Query query = em.createQuery("select defaultK from Version v where v.id = (SELECT max(v.id) FROM Version v)");
		return 13; // (int) query.getSingleResult();
	}

	public Long getCurrentVersion() {
		// Query query = em.createQuery("SELECT max(v.id) FROM Version v");
		return 2L; //(Long) query.getSingleResult();
	}
}
