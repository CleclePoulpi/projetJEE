package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.athlete;
import projectJEE.Backend.entities.discipline;
import projectJEE.Backend.service.athletesService;
import projectJEE.Backend.repository.athletesRepository;
import projectJEE.Backend.repository.disciplinesRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
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
    public void importathletes(File csvFile) throws FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader(csvFile));
        List<athlete> athletes = in.lines().skip(1).map(line -> {
            String[] x = line.split(",");
            athlete athlete = new athlete();
            athlete.setSurname(x[0]);
            athlete.setName(x[1]);
            athlete.setNationality(x[2]);
            athlete.setDateOfBirth(Date.valueOf(x[3]));
            athlete.setGender(x[4]);
            discipline discipline = disciplinesRepository.findByName(x[5]).get(0);
            athlete.setDiscipline(discipline);
            return athlete;
        }).toList();
        athletesRepository.saveAll(athletes);
    }

}
