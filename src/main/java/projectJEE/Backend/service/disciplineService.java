package projectJEE.Backend.service;

public interface disciplineService {
    void addDiscipline(String name, boolean paralympic);

    Object getDisciplines();

    void delDiscipline(int id);
}
