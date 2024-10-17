package org.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.example.model.Role;

/**
 * DAO for Role
 */
@Stateless
public class RoleDao {
	@PersistenceContext(unitName = "Librarysys-persistence-unit")
	private EntityManager em;

	public void create(Role entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Role entity = em.find(Role.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Role findById(int id) {
		return em.find(Role.class, id);
	}

	public Role update(Role entity) {
		return em.merge(entity);
	}

	public List<Role> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Role> findAllQuery = em
				.createQuery("SELECT DISTINCT r FROM Role r LEFT JOIN FETCH r.userses ORDER BY r.id", Role.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
