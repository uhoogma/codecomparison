package com.googlecode.ounit.codecomparison.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Round;

@Repository
public class RoundDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Long store(Round round) {
		if (round != null) {
			em.merge(round);
			return getId();
		} else {
			em.persist(round);
			return getId();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Long getId() {
		Query query = em.createNativeQuery( "select last_insert_id()" );
		List<BigInteger> result = query.getResultList();
		return result.isEmpty() ? -1L : result.get(0).longValue();
	}

	public Round findRoundForId(Long id) {
		TypedQuery<Round> query = em.createQuery("select r from Round r where r.id = :id", Round.class);
		query.setParameter("id", id);
		return getSingleRound(query);
	}

	private Round getSingleRound(TypedQuery<Round> query) {
		List<Round> roundList = query.getResultList();
		if (roundList.size() < 1)
			return null;
		else
			return roundList.get(0);
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

	public Round findRoundForAttemptId(int attemptId, Long taskId) {
		TypedQuery<Round> query = em.createQuery(
				"select r from Round r where r.id = (SELECT a.round_id FROM Attempt a where a.moodleId = :attemptId and a.task_id = :taskId)",
				Round.class);
		query.setParameter("attemptId", attemptId);
		query.setParameter("taskId", taskId);
		return getSingleRound(query);
	}
}
