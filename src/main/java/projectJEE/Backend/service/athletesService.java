package projectJEE.Backend.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import projectJEE.Backend.entities.athlete;
public interface athletesService {
    List<athlete> getAllAthletes();

    void importathletes(File file) throws FileNotFoundException;

}
