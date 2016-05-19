package com.googlecode.ounit.codecomparison.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Student;

@Repository
public class StudentDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void store(Student student) {
		if (student != null) {
			em.merge(student);
		} else {
			em.persist(student);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Long> getAllMoodleIds() {
		return em.createNativeQuery("select distinct s.moodleId from Student s").getResultList();
	}

	public Student findStudentForMoodleId(int moodleId) {
		TypedQuery<Student> query = em.createQuery("select r from Student r where r.moodleId = :moodleId",
				Student.class);
		query.setParameter("moodleId", moodleId);
		return getSingleRound(query);
	}

	private Student getSingleRound(TypedQuery<Student> query) {
		List<Student> students = query.getResultList();
		if (students.size() < 1)
			return null;
		else
			return students.get(0);
	}
}
