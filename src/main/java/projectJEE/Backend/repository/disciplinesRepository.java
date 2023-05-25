package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.discipline;

import java.util.List;

public interface disciplinesRepository extends JpaRepository<discipline, Long> {
    List<discipline> findByName(String name);

    List<discipline> findDisciplineById(Long id);
}
