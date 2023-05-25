package projectJEE.Backend.service;

import projectJEE.Backend.entities.event;

import java.util.List;

public interface eventService {

    List<event> getEvents();

    void delEvent(String eventId);

    void addEvent(String event_date, String event_sport, String event_location, String event_desc, String event_category, String event_type, String event_starting_hour, String event_ending_hour);


}
