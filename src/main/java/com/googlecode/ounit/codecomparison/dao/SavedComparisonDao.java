package com.googlecode.ounit.codecomparison.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.SavedComparison;

@Repository
public class SavedComparisonDao {

	@PersistenceContext
	private EntityManager em;

	public List<SavedComparison> fillTable(Long taskId) {
		TypedQuery<SavedComparison> query = em.createQuery(
				"select r from SavedComparison r where r.task_id = :taskId order by r.id", SavedComparison.class);
		query.setParameter("taskId", taskId);
		query.setFirstResult(22);
		query.setMaxResults(8);
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
