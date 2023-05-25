package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.user;

import java.util.List;

/**
 * Represents the user table in the database
 */
public interface userRepository extends JpaRepository<user, Long> {
    /**
     * Finds a user by its username and password
     * @param username the username of the user
     * @return the user with the given username
     */
    List<user> findByUsernameAndPassword(String username, String password);
}
