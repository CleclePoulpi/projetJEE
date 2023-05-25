package projectJEE.Backend.service;

/**
 * This is the interface for the discipline service.
 */
public interface disciplineService {

    /**
     * This method adds a discipline to the database.
     * @param name The name of the discipline.
     * @param paralympic Whether the discipline is paralympic or not.
     */
    void addDiscipline(String name, boolean paralympic);

    /**
     * This method returns all the disciplines in the database.
     * @return An object containing all the disciplines.
     */
    Object getDisciplines();

    /**
     * This method deletes a discipline from the database.
     * @param id The id of the discipline to delete.
     */
    void delDiscipline(int id);
}
