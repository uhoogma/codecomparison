package com.googlecode.ounit.codecomparison.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		TypedQuery<Attempt> query = em.createQuery("select r from Attempt r where r.task_id= :id and r.isBoilerplate =1", Attempt.class);
		query.setParameter("id", id);
		return getSingleAttempt(query);
	}

	public List<Attempt> findAttemptsForIds(int firstAttemptId, int secondAttemptId) {
		TypedQuery<Attempt> query = em.createQuery("select r from Attempt r where r.moodleId = :firstAttemptId or r.moodleId = :secondAttemptId",
				Attempt.class);
		query.setParameter("firstAttemptId", firstAttemptId);
		query.setParameter("secondAttemptId", secondAttemptId);
		return query.getResultList();
	}
	
	private Attempt getSingleAttempt(TypedQuery<Attempt> query) {
		List<Attempt> attemptList = query.getResultList();
		if (attemptList.size() < 1)
			return null;
		else
			return attemptList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAttemptMoodleIds(Long taskId) {
		Query query = em.createNativeQuery("select distinct a.moodleId from Attempt a where a.task_id= :taskId"); // .getResultList();
		query.setParameter("taskId", taskId);
		return query.getResultList();
	}

	public List<Attempt> findAttemptsNotFetched(Long roundId) {
		TypedQuery<Attempt> query = em.createQuery("select a from Attempt a where a.round_id= :roundId and a.codeAcquired= 0 and a.isBoilerplate= 0", Attempt.class);
		query.setParameter("roundId", roundId);
		return query.getResultList();
	}
	
	public List<Attempt> getAttemptsForTask(Long taskId) {
		TypedQuery<Attempt> query = em.createQuery("select a from Attempt a where a.task_id= :taskId and a.codeAcquired= 1 and a.isBoilerplate= 0", Attempt.class);
		query.setParameter("taskId", taskId);
		return query.getResultList();
	}

	public Integer getTasksStudentCount(Long taskId) {
		Query query = em.createNativeQuery(
				"select count(distinct(a.student_id)) from Attempt a where a.task_id= :taskId");
		query.setParameter("taskId", taskId);
		@SuppressWarnings("unchecked")
		List<BigInteger> result = query.getResultList();
		return result.isEmpty() ? 0 : result.get(0).intValue();
	}

	public Integer getTasksAttemptCount(Long taskId) {
		Query query = em.createNativeQuery(
				"select count(a.id) from Attempt a where a.task_id= :taskId and a.codeAcquired= 1 and a.isBoilerplate= 0");
		query.setParameter("taskId", taskId);
		@SuppressWarnings("unchecked")
		List<BigInteger> result = query.getResultList();
		return result.isEmpty() ? 0 : result.get(0).intValue();
	}
	
	public Integer getUnfetchedTasksAttemptCount(Long taskId) {
		Query query = em.createNativeQuery(
				"select count(a.id) from Attempt a where a.task_id= :taskId and a.codeAcquired= 0");
		query.setParameter("taskId", taskId);
		@SuppressWarnings("unchecked")
		List<BigInteger> result = query.getResultList();
		return result.isEmpty() ? 0 : result.get(0).intValue();
	}
}
