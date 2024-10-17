package org.example.cassiomolin.user.service;

import org.example.cassiomolin.user.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Service that provides operations for {@link User}s.
 *
 * @author cassiomolin
 */
@ApplicationScoped
public class UserService {

    @PersistenceContext(unitName = "Librarysys-persistence-unit")
    private EntityManager em;

    /**
     * Find a user by username or email.
     *
     * @param identifier
     * @return
     */


// public Void testUri(){
////     SecureSocketClient endpoint = new SecureSocketClient();
////     ClientEndpointConfig clientEndpointConfig = create().build();clientEndpointConfig.getUserProperties().put(SSL_CONTEXT, context);
////     final WebSocketContainer serverContainer = getWebSocketContainer();URI uri = new URI("wss://127.0.0.1:8443/secure-test/session");
////     serverContainer.connectToServer(endpoint, clientEndpointConfig, uri);
// }

    public User findByUsernameOrEmail(String identifier) {




        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :identifier", User.class)
                .setParameter("identifier", identifier)
                .setMaxResults(1)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public User findByUserPersonId(Long userPersonId) {
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.person =:identifier", User.class)
                .setParameter("identifier", userPersonId)
                .setMaxResults(1)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }



    /**
     * Find all users.
     *
     * @return
     */
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    /**
     * Find a user by id.
     *
     * @param userId
     * @return
     */
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(em.find(User.class, userId));
    }
}
