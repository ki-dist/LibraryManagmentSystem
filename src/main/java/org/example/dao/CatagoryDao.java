package org.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.example.model.Catagory;

/**
 * DAO for Catagory
 */
@Stateless
public class CatagoryDao {
	@PersistenceContext(unitName = "Librarysys-persistence-unit")
	private EntityManager em;

	public void create(Catagory entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Catagory entity = em.find(Catagory.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Catagory findById(int id) {
		return em.find(Catagory.class, id);
	}

	public Catagory update(Catagory entity) {
		return em.merge(entity);
	}

	public List<Catagory> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Catagory> findAllQuery = em
				.createQuery("SELECT DISTINCT c FROM Catagory c LEFT JOIN FETCH c.books ORDER BY c.id", Catagory.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
