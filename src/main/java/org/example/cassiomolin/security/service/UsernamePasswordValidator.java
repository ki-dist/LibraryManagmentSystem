package org.example.cassiomolin.security.service;


import org.example.cassiomolin.security.exception.AuthenticationException;
import org.example.cassiomolin.user.domain.User;
import org.example.cassiomolin.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Component for validating user credentials.
 *
 * @author cassiomolin
 */
@ApplicationScoped
public class UsernamePasswordValidator {

    @Inject
    private UserService userService;

    @Inject
    private PasswordEncoder passwordEncoder;


    /**
     * Validate username and password.
     *
     * @param username
     * @param password
     * @return
     */
    public User validateCredentials(String username, String password) {

        User user = userService.findByUsernameOrEmail(username);

        if (user == null) {
            // User cannot be found with the given username/email
            throw new AuthenticationException("Bad credentials ! There is no user by this name");
        }

        if (!user.isActive()) {
            // User is not active
            throw new AuthenticationException("The user is inactive.");
        }

        if (!passwordEncoder.checkPassword(password, user.getPassword())) {
//            loginAttemptServices.updateFailedLogin(user.getId());
            // Invalid password
            throw new AuthenticationException("Bad credentials.");
        }

        return user;
    }
}