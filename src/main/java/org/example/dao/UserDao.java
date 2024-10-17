package org.example.dao;


import org.example.cassiomolin.user.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * DAO for User
 */
@Stateless
public class UserDao {
    @PersistenceContext(unitName = "Librarysys-persistence-unit")
    private EntityManager em;

    public void create(User entity) {
        em.persist(entity);
    }

    public void deleteById(Long id) {
        User entity = em.find(User.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User findByIds(Long id) {
        List<User> resultList = em.createQuery(
                "SELECT DISTINCT p FROM User p where p.id=:id ORDER BY p.id",
                User.class).setParameter("id",id).getResultList();
        if(!resultList.isEmpty()){
            return resultList.get(0);
        }
        return null;
    }


    public User update(User entity) {
        em.flush();
        return em.merge(entity);
    }

    public List<User> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u ORDER BY u.id", User.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }


    public List<User> userList(String searchQuery, Integer startPosition, Integer maxResult) {
        Optional.ofNullable(searchQuery).orElse("");
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u where u.username like concat('%',:query,'%') ORDER BY u.id", User.class).setParameter("query",searchQuery);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public List<User> userListCount(String searchQuery ) {
        Optional.ofNullable(searchQuery).orElse("");
     return em.createQuery(
                "SELECT DISTINCT u FROM User u where u.username like concat('%',:query,'%')  ORDER BY u.id", User.class)
            .setParameter("query",searchQuery).getResultList();

    }

    public Long countForListAll() {
        return em.createQuery(
                "SELECT DISTINCT count(c) FROM User c",
                Long.class).getSingleResult();
    }



    public User findUserByUserName(String userName) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u WHERE u.username = :userName", User.class);
        findAllQuery.setParameter("userName", userName);
        List<User> users = findAllQuery.getResultList();
        return (users.isEmpty()) ? null : users.get(0);
    }
    public User updateUser(User entity)
    {
        User user =em.merge(entity);
        em.flush();
        return user;
    }



}
