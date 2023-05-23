package projectJEE.Backend.service;

public interface locationService {
    void addLocation(String name, String city, String category);
    Object getLocations();
}
