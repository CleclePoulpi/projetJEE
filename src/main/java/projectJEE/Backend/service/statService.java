package projectJEE.Backend.service;

import org.springframework.boot.CommandLineRunner;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.entities.countcalc;
import projectJEE.Backend.entities.discipline;
import projectJEE.Backend.entities.timecalc;

import java.util.List;

public interface statService {

    public List<countcalc> getMostPopularLocations();
    public List<timecalc> getMostPopularDisciplines();
}
