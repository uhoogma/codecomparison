package com.googlecode.ounit.codecomparison.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Attempt;

@Repository
public class AttemptDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Attempt attempt) {
		if (attempt != null) {
			em.merge(attempt);
		} else {
			em.persist(attempt);
		}
	}

	public Attempt getTaskBoilerPlateAttempt(long id) {
		TypedQuery<Attempt> query = em.createQuery("select r from Attempt r where r.task_id= :id", Attempt.class);
		query.setParameter("id", id);
		return getSingleAttempt(query);
	}

	private Attempt getSingleAttempt(TypedQuery<Attempt> query) {
		List<Attempt> persons = query.getResultList();
		if (persons.size() < 1)
			return null;
		else
			return persons.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAttemptIds() {
		return em.createNativeQuery("select distinct a.id from Attempt a").getResultList();
	}

	public List<Attempt> findAttemptsNotFetched(Long roundId) {
		TypedQuery<Attempt> query = em.createQuery("select a from Attempt a where a.round_id= :roundId and a.codeAcquired= 0 and a.isBoilerplate= 0", Attempt.class);
		query.setParameter("roundId", roundId);
		return query.getResultList();
	}
}
