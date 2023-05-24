package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.event;
import projectJEE.Backend.service.eventService;
import projectJEE.Backend.repository.eventRepository;

import java.util.List;

@Service
public class eventServiceImpl implements eventService{

    @Autowired
    private eventRepository eventRepository;

    @Override
    public List<event> getEvents() {
        return eventRepository.findAll();
    }
}
