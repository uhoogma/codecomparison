package com.googlecode.ounit.codecomparison.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class VersionDao {

	@PersistenceContext
	private EntityManager em;
	
	public int getDefaultT() {
		Query query = em.createQuery("select defaultT from Version v where v.id = 1");
		return (int) query.getSingleResult();
	}
	
	public int getDefaultK() {
		Query query = em.createQuery("select defaultK from Version v where v.id = 1");
		return (int) query.getSingleResult();
	}
}
