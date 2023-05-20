package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.athlete;
import projectJEE.Backend.service.athletesService;
import projectJEE.Backend.repository.athletesRepository;

import java.util.List;

@Service
public class athletesServiceImpl implements athletesService {

    @Autowired
    private athletesRepository athletesRepository;

    @Override
    public List<athlete> getAllAthletes() {
        return athletesRepository.findAll();
    }

}
