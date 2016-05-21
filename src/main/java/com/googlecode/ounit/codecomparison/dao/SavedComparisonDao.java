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

import com.googlecode.ounit.codecomparison.model.SavedComparison;

@Repository
public class SavedComparisonDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void deletePreviousAnalysis(Long taskId, Long versionId) {
		Query query = em
				.createQuery("DELETE FROM SavedComparison r where r.task_id = :taskId and r.version_id = :versionId");
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		query.executeUpdate();
	}

	public List<SavedComparison> fillTable(Long taskId, Integer startId, Integer resultCount) {
		TypedQuery<SavedComparison> query = em.createQuery(
				"select r from SavedComparison r where r.task_id = :taskId order by r.id", SavedComparison.class);
		query.setParameter("taskId", taskId);
		query.setFirstResult(startId);
		query.setMaxResults(resultCount);
		List<SavedComparison> rounds = query.getResultList();
		if (rounds == null) {
			return new ArrayList<>();
		}
		return rounds;
	}

	public SavedComparison findComparisonForId(Long comparisonId) {
		TypedQuery<SavedComparison> query = em.createQuery("select r from SavedComparison r where r.id = :comparisonId",
				SavedComparison.class);
		query.setParameter("comparisonId", comparisonId);
		return getSingleSavedComparison(query);
	}

	@SuppressWarnings("unchecked")
	public List<Double> getChartData(Long taskId, Long versionId) {
		Query query = em.createNativeQuery(
				"select GREATEST(sc.firstToSecondResult, sc.secondToFirstResult) from SavedComparison sc where GREATEST(sc.firstToSecondResult, sc.secondToFirstResult) < '1.7976931348623157e308' and sc.task_id = :taskId and sc.version_id = :versionId");
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Integer getResultCount(Long taskId, Long versionId) {
		Query query = em.createNativeQuery(
				"select count(r.id) from SavedComparison r where r.task_id = :taskId and r.version_id = :versionId");
		query.setParameter("taskId", taskId);
		query.setParameter("versionId", versionId);
		List<BigInteger> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0).intValue();
	}

	private SavedComparison getSingleSavedComparison(TypedQuery<SavedComparison> query) {
		List<SavedComparison> savedComparisons = query.getResultList();
		if (savedComparisons.size() < 1)
			return null;
		else
			return savedComparisons.get(0);
	}

	@Transactional
	public void store(SavedComparison sc) {
		if (sc != null) {
			em.merge(sc);
		} else {
			em.persist(sc);
		}
	}
}
