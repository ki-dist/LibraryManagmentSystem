package org.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.example.model.Users;

/**
 * DAO for Users
 */
@Stateless
public class UsersDao {
	@PersistenceContext(unitName = "Librarysys-persistence-unit")
	private EntityManager em;

	public void create(Users entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Users entity = em.find(Users.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Users findById(int id) {
		return em.find(Users.class, id);
	}

	public Users update(Users entity) {
		return em.merge(entity);
	}

	public List<Users> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Users> findAllQuery = em.createQuery(
				"SELECT DISTINCT u FROM Users u LEFT JOIN FETCH u.role LEFT JOIN FETCH u.loanedbooks ORDER BY u.id",
				Users.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
