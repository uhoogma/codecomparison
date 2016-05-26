package com.googlecode.ounit.codecomparison.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.AbstractedCode;

@Repository
public class AbstractedCodeDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(AbstractedCode code) {
		if (code != null) {
			em.merge(code);
		} else {
			em.persist(code);
		}
	}

	public AbstractedCode getCodeForAttempt(Long taskId, Long versionId) {
		TypedQuery<AbstractedCode> query = em.createQuery(
				"select r from AbstractedCode r where r.attempt_id= :taskId and r.version_id= :versionId",
				AbstractedCode.class);
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		return getSingleAbstractedCode(query);
	}

	public List<AbstractedCode> getAbstractedAttemptsForTask(Long taskId, Long versionId) {
		TypedQuery<AbstractedCode> query = em.createQuery(
				"select r from AbstractedCode r where r.task_id= :taskId and r.version_id= :versionId",
				AbstractedCode.class);
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		return query.getResultList();
	}

	private AbstractedCode getSingleAbstractedCode(TypedQuery<AbstractedCode> query) {
		List<AbstractedCode> codeList = query.getResultList();
		if (codeList.size() < 1)
			return null;
		else
			return codeList.get(0);
	}

	@SuppressWarnings("unchecked")
	public String getTaskBoilerPlateAbstracted(long taskId, long versionId) {
		Query query = em.createNativeQuery(
				"select ac.abstractedCode from AbstractedCode ac where ac.version_id = :versionId and ac.attempt_id in (select a.id from Attempt a where a.task_id= :taskId and a.isBoilerPlate= 1)");
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		List<String> result = query.getResultList();
		return result.isEmpty() ? "" : result.get(0);
	}
}
