package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.event;


public interface eventRepository extends JpaRepository<event, Long> {

}
