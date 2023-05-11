package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.user;

public interface userRepository extends JpaRepository<user, Long> {

}
