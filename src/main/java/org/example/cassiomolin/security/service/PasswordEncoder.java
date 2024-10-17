package org.example.cassiomolin.security.service;

import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;

/**
 * bcrypt password encoder.
 *
 * @author cassiomolin
 */
@ApplicationScoped
public class PasswordEncoder {

    /**
     * Hashes a password using BCrypt.
     *
     * @param plainTextPassword
     * @return
     */
    public String hashPassword(String plainTextPassword) {
       String salt = BCrypt.gensalt();
    	
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    /**
     * Checks a password against a stored hash using BCrypt.
     *
     * @param plainTextPassword
     * @param hashedPassword
     * @return
     */
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {

        if (null == hashedPassword || !hashedPassword.startsWith("$2a$")) {
            throw new RuntimeException("Hashed password is invalid");
        }
   //     System.out.println("The hashed password ========="+hashedPassword);
   //     System.out.println("Hassing the password ==========" + this.hashPassword(plainTextPassword));
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
