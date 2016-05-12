package com.googlecode.ounit.codecomparison.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;

@Repository
public class RoundDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Round person) {
		if (person != null) {
			em.merge(person);
		} else {
			em.persist(person);
		}
	}

	public List<Round> findAllRounds() {
		return em.createQuery("select p from Round p", Round.class).getResultList();
	}

	public List<Round> findAllRoundsInTask(Long taskId) {
		TypedQuery<Round> query = em.createQuery("select r from Round r where r.task_id = :taskId", Round.class);
		query.setParameter("taskId", taskId);
		List<Round> persons = query.getResultList();
		if (persons == null) {
			return new ArrayList<>();
		}
		return persons;
	}

	public Round findRoundForId(Long id) {
		System.out.println("id on"+id);
		TypedQuery<Round> query = em.createQuery("select p from Round p where p.id = :id", Round.class);
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

	/*public void addTask(long roundId, Task findTaskForId) {
		Round r = findRoundForId(roundId);

	}
*/
	public List<Round> getRoundsNotInTask(long parseLong) {
		TypedQuery<Round> query = em.createQuery("select p from Round p where p.task_id != :id or p.task_id is null",
				Round.class);
		query.setParameter("id", parseLong);
		return query.getResultList();
	}
	
	@Transactional
	public void delete(Long id) {
		System.out.println(id +"id on null");
		Round round = findRoundForId(id);
		if (round != null) {
			em.remove(round);
		}
	}
}
