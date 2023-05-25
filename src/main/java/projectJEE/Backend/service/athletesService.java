package projectJEE.Backend.service;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import projectJEE.Backend.entities.athlete;

/**
 * athletesService interface
 */
public interface athletesService {
    /**
     * Gets all athletes
     */
    List<athlete> getAllAthletes();

    /**
     * Imports athletes from a csv file
     * @param list  List of athletes
     */
    void importathletes(List<LinkedHashMap<String,String>> list) throws ParseException;

    /**
     * Deletes all athletes
     */
    void dropAthletes();

}
