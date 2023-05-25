package projectJEE.Backend.service;

/**
 * locationService interface
 */
public interface locationService {
    /**
     * Adds a location
     * @param name name of the location
     * @param city city of the location
     * @param category category of the location
     */
    void addLocation(String name, String city, String category);

    /**
     * Deletes a location by id
     * @param id id of the location
     */
    void delLocation(int id);

    /**
     * gets all locations
     * @return all locations
     */
    Object getLocations();
}
