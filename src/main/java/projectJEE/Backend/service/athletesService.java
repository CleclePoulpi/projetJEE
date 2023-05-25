package projectJEE.Backend.service;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import projectJEE.Backend.entities.athlete;
public interface athletesService {
    List<athlete> getAllAthletes();

    void importathletes(List<LinkedHashMap<String,String>> list) throws ParseException;

}
