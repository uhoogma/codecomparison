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
	public List<Integer> getAllMoodleIds() {
		return em.createNativeQuery("select distinct s.moodleId from Student s").getResultList();
	}

	public List<Student> findStudentsForMoodleIds(int firstStudentId, int secondStudentId) {
		TypedQuery<Student> query = em.createQuery(
				"select r from Student r where r.moodleId = :firstStudentId or r.moodleId = :secondStudentId",
				Student.class);
		query.setParameter("firstStudentId", firstStudentId);
		query.setParameter("secondStudentId", secondStudentId);
		return query.getResultList();
	}
}
