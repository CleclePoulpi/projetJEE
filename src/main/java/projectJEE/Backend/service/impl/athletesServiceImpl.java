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

@Service
public class athletesServiceImpl implements athletesService {

    @Autowired
    private athletesRepository athletesRepository;

    @Autowired
    private disciplinesRepository disciplinesRepository;

    @Override
    public List<athlete> getAllAthletes() {
        return athletesRepository.findAll();
    }


    @Override
    public void dropAthletes() {
        athletesRepository.deleteAll();
    }

    @Override
    public void importathletes(List<LinkedHashMap<String,String>> list) throws ParseException {
        for(int i = 0; i<list.size(); i++){
            LinkedHashMap<String,String> a = list.get(i);
            athlete athlete = new athlete(a.get("Prénom"),a.get("Nom de famille"),a.get("Nationalité"),
                    new SimpleDateFormat("dd/MM/yyyy").parse(a.get("Date de naissance")),
                    disciplinesRepository.findByName(a.get("Sport")).get(0),a.get("Genre"));
            System.out.println(athlete);
            athletesRepository.save(athlete);
        }
    }

}
