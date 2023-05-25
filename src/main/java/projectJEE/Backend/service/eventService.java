package projectJEE.Backend.service;

import projectJEE.Backend.entities.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface eventService {

    List<event> getEvents();

    void addEvent(LocalDate event_date, String event_sport, String event_location, String event_desc, String event_category, String event_type, LocalTime event_starting_hour, LocalTime event_ending_hour);

    LocalDateTime isValid(String discipline, LocalDate date, LocalTime starting_hour, LocalTime ending_hour);
}
