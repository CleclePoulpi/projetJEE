package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.discipline;

import java.util.List;

/**
 * Represents the discipline table in the database
 */
public interface disciplinesRepository extends JpaRepository<discipline, Long> {
    /**
     * Find a discipline by its name
     * @param name the name of the discipline
     * @return the discipline
     */
    List<discipline> findByName(String name);

    List<discipline> findDisciplineById(Long id);
}
