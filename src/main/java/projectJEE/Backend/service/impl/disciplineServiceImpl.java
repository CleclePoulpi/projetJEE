package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.service.disciplineService;
import projectJEE.Backend.repository.disciplinesRepository;
import projectJEE.Backend.entities.discipline;

import java.util.List;

@Service
public class disciplineServiceImpl implements disciplineService {

    @Autowired
    private disciplinesRepository disciplinesRepository;

    @Override
    public void addDiscipline(String name, boolean paralympic) {
        System.out.println(name);
        disciplinesRepository.save(new discipline(name, paralympic));
    }

    @Override
    public List<discipline> getDisciplines() {
        return disciplinesRepository.findAll();
    }

    @Override
    public void delDiscipline(int id) {
        disciplinesRepository.delete(new discipline((long)id));
    }
}
