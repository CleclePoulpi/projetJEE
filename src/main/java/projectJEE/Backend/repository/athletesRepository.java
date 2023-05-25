package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.athlete;

/**
 * Represents the athlete table in the database
 */
public interface athletesRepository extends JpaRepository<athlete, Long> {

}
