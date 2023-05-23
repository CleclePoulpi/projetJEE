package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.Location;

import java.util.List;

public interface locationRepository extends JpaRepository<Location,Long> {

    List<Location> findLocationById(Long id);
}
