package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.event;

/**
 * Represents the event table in the database
 */
public interface eventRepository extends JpaRepository<event, Long> {

}
