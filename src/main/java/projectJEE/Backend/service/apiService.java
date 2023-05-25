package projectJEE.Backend.service;

import projectJEE.Backend.entities.login;
import projectJEE.Backend.entities.user;

/**
 * apiService interface
 */
public interface apiService {
    /**
     * login method
     * @param login login to check
     * @return boolean true if login is correct
     */
    boolean login(login login);

    /**
     * generateToken method
     * @param user user to generate token for
     * @return String token
     */
    String generateToken(user user);

}
