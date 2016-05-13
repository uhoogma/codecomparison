package com.googlecode.ounit.codecomparison.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Round;

@Repository
public class RoundDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Round round) {
		if (round != null) {
			em.merge(round);
		} else {
			em.persist(round);
		}
	}

	public Round findRoundForId(Long id) {
		TypedQuery<Round> query = em.createQuery("select r from Round r where r.id = :id", Round.class);
		query.setParameter("id", id);
		return getSingleRound(query);
	}

	private Round getSingleRound(TypedQuery<Round> query) {
		List<Round> persons = query.getResultList();
		if (persons.size() < 1)
			return null;
		else
			return persons.get(0);
	}

	@Transactional
	public void delete(Long id) {
		Round round = findRoundForId(id);
		if (round != null) {
			em.remove(round);
		}
	}

	public List<Round> findAllRoundsNotInAnyTask() {
		return em.createQuery("select r from Round r where r.task_id IS NULL", Round.class).getResultList();
	}

	public List<Round> findAllRoundsInTask(Long taskId) {
		TypedQuery<Round> query = em.createQuery("select r from Round r where r.task_id = :taskId", Round.class);
		query.setParameter("taskId", taskId);
		List<Round> rounds = query.getResultList();
		if (rounds == null) {
			return new ArrayList<>();
		}
		return rounds;
	}

}
