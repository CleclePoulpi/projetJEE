package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.service.disciplineService;
import projectJEE.Backend.repository.disciplinesRepository;
import projectJEE.Backend.entities.discipline;

import java.util.List;

/**
 * Implementations of disciplineService interface
 */
@Service
public class disciplineServiceImpl implements disciplineService {

    /**
     * private disciplinesRepository disciplinesRepository;
     */
    @Autowired
    private disciplinesRepository disciplinesRepository;

    /**
     * The method adds a discipline to the database
     * @param name name of the discipline
     * @param paralympic boolean if the discipline is paralympic
     */
    @Override
    public void addDiscipline(String name, boolean paralympic) {
        disciplinesRepository.save(new discipline(name, paralympic));
    }

    /**
     * The method returns all disciplines from the database
     * @return List<discipline>
     */
    @Override
    public List<discipline> getDisciplines() {
        return disciplinesRepository.findAll();
    }

    /**
     * The method deletes a discipline from the database
     * @param id id of the discipline
     */
    @Override
    public void delDiscipline(int id) {
        disciplinesRepository.delete(new discipline((long)id));
    }
}
