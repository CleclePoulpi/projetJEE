package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.category;
import projectJEE.Backend.entities.discipline;
import projectJEE.Backend.entities.event;
import projectJEE.Backend.entities.type;
import projectJEE.Backend.service.eventService;
import projectJEE.Backend.repository.eventRepository;
import projectJEE.Backend.repository.locationRepository;
import projectJEE.Backend.repository.disciplinesRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class eventServiceImpl implements eventService{

    @Autowired
    private eventRepository eventRepository;

    @Autowired
    private locationRepository locationRepository;

    @Autowired
    private disciplinesRepository disciplineRepository;

    @Override
    public List<event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void addEvent(String event_date, String event_sport, String event_location, String event_desc, String event_category, String event_type, String event_starting_hour, String event_ending_hour) {
        discipline sport = disciplineRepository.findDisciplineById((long) Integer.parseInt(event_sport)).get(0);
        event event = new event(
                        new Date(event_date),
                        new Time(Long.parseLong(event_starting_hour)),
                        event_desc,
                        sport,
                        new Time(Long.parseLong(event_ending_hour)),
                        locationRepository.findLocationById((long) Integer.parseInt(event_location)).get(0),
                        type.valueOf(event_type),
                        category.valueOf(event_category));
        String event_id  = event.getId().toString();
        event.setCode(sport.getName().substring(0,3) + event_id.substring(event_id.length()-3));
        System.out.println(event);
        eventRepository.save(event);
    }
}
