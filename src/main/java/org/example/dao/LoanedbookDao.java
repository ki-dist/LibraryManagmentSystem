package org.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.example.model.Loanedbook;

/**
 * DAO for Loanedbook
 */
@Stateless
public class LoanedbookDao {
	@PersistenceContext(unitName = "Librarysys-persistence-unit")
	private EntityManager em;

	public void create(Loanedbook entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Loanedbook entity = em.find(Loanedbook.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Loanedbook findById(int id) {
		return em.find(Loanedbook.class, id);
	}

	public Loanedbook update(Loanedbook entity) {
		return em.merge(entity);
	}

	public List<Loanedbook> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Loanedbook> findAllQuery = em.createQuery(
				"SELECT DISTINCT l FROM Loanedbook l LEFT JOIN FETCH l.book LEFT JOIN FETCH l.users ORDER BY l.id",
				Loanedbook.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
