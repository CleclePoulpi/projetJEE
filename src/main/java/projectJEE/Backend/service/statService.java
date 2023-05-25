package projectJEE.Backend.service;

import org.springframework.boot.CommandLineRunner;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.entities.discipline;

import java.util.List;

public interface statService {

    public List<Location> getMostPopularLocations();
    public List<discipline> getMostPopularDisciplines();
}
