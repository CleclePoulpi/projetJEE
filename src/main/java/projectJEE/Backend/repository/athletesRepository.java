package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.athlete;

public interface athletesRepository extends JpaRepository<athlete, Long> {

}
