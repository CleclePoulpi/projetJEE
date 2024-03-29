package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.athlete;
import projectJEE.Backend.service.athletesService;
import projectJEE.Backend.repository.athletesRepository;
import projectJEE.Backend.repository.disciplinesRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Implementation of athletesService interface
 */
@Service
public class athletesServiceImpl implements athletesService {

    /**
     * athletesRepository
     */
    @Autowired
    private athletesRepository athletesRepository;

    /**
     * disciplinesRepository
     */
    @Autowired
    private disciplinesRepository disciplinesRepository;

    /**
     * The method gets all athletes
     */
    @Override
    public List<athlete> getAllAthletes() {
        return athletesRepository.findAll();
    }

    /**
     * The method drops all athletes
     */
    @Override
    public void dropAthletes() {
        athletesRepository.deleteAll();
    }

    /**
     * The method imports athletes
     * @param list list of athletes
     * @throws ParseException exception if the date is not in the right format
     */
    @Override
    public void importathletes(List<LinkedHashMap<String,String>> list) throws ParseException {
        for(int i = 0; i<list.size(); i++){
            LinkedHashMap<String,String> a = list.get(i);
            athlete athlete = new athlete(a.get("Prénom"),a.get("Nom de famille"),a.get("Nationalité"),
                    new SimpleDateFormat("dd/MM/yyyy").parse(a.get("Date de naissance")),
                    disciplinesRepository.findByName(a.get("Sport")).get(0),a.get("Genre"));
            athletesRepository.save(athlete);
        }
    }

}
