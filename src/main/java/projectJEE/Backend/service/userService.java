package projectJEE.Backend.service;

import projectJEE.Backend.entities.user;

/**
 * Interface userService
 */
public interface userService {

    /** Adds a user to the database
     * @param user user to add
     */
    user addUser(user user);

}
