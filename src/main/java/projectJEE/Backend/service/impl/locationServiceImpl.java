package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.Location;
import projectJEE.Backend.service.locationService;
import projectJEE.Backend.repository.locationRepository;

@Service
public class locationServiceImpl implements locationService {

    @Autowired
    private locationRepository locationRepository;

    public void addLocation(String name, String city, String category) {
        locationRepository.save(new Location(name, city, category));
    }

    public Object getLocations() {
        return locationRepository.findAll();
    }
}
