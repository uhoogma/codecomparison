package com.googlecode.ounit.codecomparison.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Round;
import com.googlecode.ounit.codecomparison.model.Task;

@Repository
public class TaskDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Task task) {
		if (task != null) {
			em.merge(task);
		} else {
			em.persist(task);
		}
	}
	
	public Task findTaskForId(Long id) {
		TypedQuery<Task> query = em.createQuery("select t from Task t where t.id = :id", Task.class);
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
	public void addRound(Task t, Round round) {
		if (t.getId() == null || round == null) {
			return;
		}
		round.setTask_id(t.getId());
		em.merge(round);
	}
	
	@Transactional
	public void removeRound(Task t, Round round) {
		if (round == null) {
			return;
		}
		round.setTask_id(null);
		em.merge(round);
	}

	public List<Task> findActiveTasks() {
		TypedQuery<Task> query = em.createQuery("select t from Task t where t.active = 1", Task.class);
		return query.getResultList();
	}

	public List<Task> findHiddenTasks() {
		TypedQuery<Task> query = em.createQuery("select t from Task t where t.active = 0", Task.class);
		return query.getResultList();
	}

}
