package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.user;

import java.util.List;

public interface userRepository extends JpaRepository<user, Long> {
    List<user> findByUsernameAndPassword(String username, String password);
}
