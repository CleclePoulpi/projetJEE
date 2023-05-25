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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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
    public void delEvent(String eventId) {
        eventRepository.delete(new event((long) Integer.parseInt(eventId)));
    }

    @Override
    public void addEvent(String event_date, String event_sport, String event_location, String event_desc, String event_category, String event_type, String event_starting_hour, String event_ending_hour) {

        discipline sport = disciplineRepository.findDisciplineById((long) Integer.parseInt(event_sport)).get(0);
        boolean notValid = true;
        Random obj = new Random();
        String code = "";
        while (notValid) {
            code = sport.getName().substring(0, 3) + obj.nextInt(10) + obj.nextInt(10);
            notValid = eventRepository.findEventByCodeAndDiscipline(code, sport).size() != 0;
        }
        event event = new event(
                LocalDate.parse(event_date, DateTimeFormatter.ISO_DATE),
                LocalTime.parse(event_starting_hour),
                event_desc,
                sport,
                LocalTime.parse(event_ending_hour),
                locationRepository.findLocationById((long) Integer.parseInt(event_location)).get(0),
                type.valueOf(event_type),
                category.valueOf(event_category),
                code);
        eventRepository.save(event);
    }
}
