package org.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.example.model.Book;

/**
 * DAO for Book
 */
@Stateless
public class BookDao {
	@PersistenceContext(unitName = "Librarysys-persistence-unit")
	private EntityManager em;

	public void create(Book entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Book entity = em.find(Book.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Book findById(int id) {
		return em.find(Book.class, id);
	}

	public Book update(Book entity) {
		return em.merge(entity);
	}

	public List<Book> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Book> findAllQuery = em.createQuery(
				"SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.catagory LEFT JOIN FETCH b.loanedbooks ORDER BY b.id",
				Book.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
