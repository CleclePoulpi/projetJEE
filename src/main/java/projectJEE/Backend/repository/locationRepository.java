package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.Location;

import java.util.List;

/**
 * Represents the location table in the database
 */
public interface locationRepository extends JpaRepository<Location,Long> {
    /**
     * Find a location by its id
     * @param id the id of the location
     * @return the location
     */
    List<Location> findLocationById(Long id);
}