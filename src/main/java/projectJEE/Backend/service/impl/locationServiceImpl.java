package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.service.locationService;
import projectJEE.Backend.repository.locationRepository;

/**
 * Implementation of the locationService interface
 */
@Service
public class locationServiceImpl implements locationService {

    /**
     * locationRepository
     */
    @Autowired
    private locationRepository locationRepository;

    /**
     * The method adds a location
     */
    public void addLocation(String name, String city, String category) {
        locationRepository.save(new Location(name, city, category));
    }

    /**
     * The method deletes a location
     */
    public void delLocation(int id) {
        locationRepository.delete(new Location((long)id));
    }

    /**
     * The method returns all locations
     */
    public Object getLocations() {
        return locationRepository.findAll();
    }
}
