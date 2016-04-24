package com.googlecode.ounit.codecomparison.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ounit.codecomparison.model.Person;

@Repository
public class PersonDao {

	@PersistenceContext
	private EntityManager em;

	public List<Person> findAllPersons() {
		return em.createQuery("select p from Person p", Person.class).getResultList();
	}

	public Person findPersonForId(Long id) {
		TypedQuery<Person> query = em.createQuery("select p from Person p where p.id = :id", Person.class);
		query.setParameter("id", id);
		return GetSinglePerson(query);
	}

	public Person findPersonForCode(String code) {
		TypedQuery<Person> query = em.createQuery("SELECT c FROM Person c WHERE c.code = :code", Person.class);
		query.setParameter("code", code);
		return GetSinglePerson(query);
	}

	private Person GetSinglePerson(TypedQuery<Person> query) {
		List<Person> persons = query.getResultList();
		if (persons.size() < 1)
			return new Person();
		else
			return persons.get(0);
	}

	@Transactional
	public void store(Person person) {
		em.persist(person);
	}

	@Transactional
	public void delete(Long personId) {
		Person p = findPersonForId(personId);
		if (p != null) {
			em.remove(p);
		}
	}

	public List<Person> filterPersons(String fragment) {
		TypedQuery<Person> query = em.createQuery("select p from Person p where upper(p.givenName) like :fragment or "
				+ "upper(p.sureName) like :fragment", Person.class);
		query.setParameter("fragment", "%" + fragment.toUpperCase() + "%");
		List<Person> persons = query.getResultList();
		if (persons == null) {
			return new ArrayList<>();
		}
		return persons;
	}

	public void truncatePersons() {
		SetUpDao a = new SetUpDao();
		a.executeUpdate("DELETE FROM PERSON");
	}

	public Map<String, String> getCustomerGroups() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("customerType.notset", "");
		map.put("customerType.private", "Private");
		map.put("customerType.corporate", "Corporate");
		return map;
	}

	public Map<String, String> getPhoneTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("phoneType.fixed", "Fixed");
		map.put("phoneType.mobile", "Mobile");
		return map;
	}
}
