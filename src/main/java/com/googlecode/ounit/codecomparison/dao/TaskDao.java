package com.googlecode.ounit.codecomparison.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Person;
import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;

@Repository
public class TaskDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Task person) {
		em.persist(person);
	}
	@Transactional
	public void save(Task person) {
		if (person != null) {
			em.merge(person);

		}else{
			em.persist(person);

		}
		// em.persist(person);
	}
	public List<Task> findAllRounds() {
		return em.createQuery("select p from Task p", Task.class).getResultList();
	}

	public Task findTaskForId(Long id) {
		TypedQuery<Task> query = em.createQuery("select p from Task p where p.id = :id", Task.class);
		query.setParameter("id", id);
		return getSingleTask(query);
	}

	private Task getSingleTask(TypedQuery<Task> query) {
		List<Task> persons = query.getResultList();
		if (persons.size() < 1)
			return new Task();
		else
			return persons.get(0);
	}
	
	@Transactional
	public void addRounds(Task t, List<Round> roundsToAdd) {
		// Task t= findTaskForId(taskId);
		if (t.getId() == null) {
			return;
		}
		for (Round round : roundsToAdd) {
			System.out.println("foreach");
			t.addRound(round);
			em.merge(t);
		}
		
	}
	
}