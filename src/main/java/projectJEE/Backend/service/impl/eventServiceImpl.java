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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * This class implements the eventService interface.
 */
@Service
public class eventServiceImpl implements eventService{

    /**
     * This attribute represents the event repository.
     */
    @Autowired
    private eventRepository eventRepository;

    /**
     * This attribute represents the location repository.
     */
    @Autowired
    private locationRepository locationRepository;

    /**
     * This attribute represents the discipline repository.
     */
    @Autowired
    private disciplinesRepository disciplineRepository;

    /**
     * This method returns all the events.
     * @return a list of events
     */
    @Override
    public List<event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void delEvent(String eventId) {
        eventRepository.delete(new event((long) Integer.parseInt(eventId)));
    }
    /**
     * This method adds an event inside the database.
     * @param event_date the date of the event
     * @param event_sport the sport of the event
     * @param event_location the location of the event
     * @param event_desc the description of the event
     * @param event_category the category of the event
     * @param event_type the type of the event
     * @param event_starting_hour the starting hour of the event
     * @param event_ending_hour the ending hour of the event
     */
    @Override
    public void addEvent(LocalDate event_date, String event_sport, String event_location, String event_desc, String event_category, String event_type, LocalTime event_starting_hour, LocalTime event_ending_hour) {
        discipline sport = disciplineRepository.findDisciplineById((long) Integer.parseInt(event_sport)).get(0);
        boolean notValid = true;
        Random obj = new Random();
        String code = "";
        while (notValid) {
            code = sport.getName().substring(0, 3) + obj.nextInt(10) + obj.nextInt(10);
            notValid = eventRepository.findEventByCodeAndDiscipline(code, sport).size() != 0;
        }
        event event = new event(
                event_date,
                event_starting_hour,
                event_desc,
                sport,
                event_ending_hour,
                locationRepository.findLocationById((long) Integer.parseInt(event_location)).get(0),
                type.valueOf(event_type),
                category.valueOf(event_category),
                code);
        eventRepository.save(event);
    }

    /**
     * This method checks if the event is valid.
     * @return true if the event is valid, false otherwise
     */
    @Override
    public LocalDateTime isValid(String discipline, LocalDate date, LocalTime starting_hour, LocalTime ending_hour) {
        List<event> events = eventRepository.findAll();
        discipline sport = disciplineRepository.findDisciplineById((long) Integer.parseInt(discipline)).get(0);
        boolean possible = true;
        LocalDateTime result = null;
        LocalTime lowestTime = LocalTime.of(0, 1);
        if (starting_hour.isAfter(ending_hour)) {
            return date.atTime(ending_hour);
        }
        for (event event : events) {
            if (event.getDiscipline().equals(sport) && event.getDate().equals(date)) {
                if (lowestTime.isBefore(event.getEnding_hour())) {
                    lowestTime = event.getEnding_hour();
                }
               if (Math.abs(starting_hour.compareTo(event.getEnding_hour())) < 60 || Math.abs(ending_hour.compareTo(event.getStarting_hour())) < 60 || Math.abs(starting_hour.compareTo(event.getStarting_hour())) < 60 || Math.abs(ending_hour.compareTo(event.getEnding_hour())) < 60) {
                   possible = false;
               }
            }
        }
        if (!possible) {
            result = LocalDateTime.of(date, lowestTime.plus(1, ChronoUnit.HOURS));
            return result;
        }
        else {
            return LocalDateTime.of(date, starting_hour);
        }
    }
}
