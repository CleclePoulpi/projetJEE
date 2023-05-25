package projectJEE.Backend.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import projectJEE.Backend.service.eventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is the controller for the event entity
 * It handles the requests from the frontend
 * It calls the service layer to execute the requests
 */
@RestController
@RequestMapping("/api/events")
public class eventController {

    /**
     * The service layer for the event entity
     */
    @Autowired
    private eventService eventService;

    /**
     * This method is used to get the list of events
     * @return the list of events
     */
    @ResponseBody
    @PostMapping("/get")
    public ResponseEntity<JSONObject> getEvents(){
        JSONObject response = new JSONObject();
        response.put("events", eventService.getEvents());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used to delete an event
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody JSONObject request){
        LocalDate date_f = LocalDate.parse(request.getAsString("event_date"), DateTimeFormatter.ISO_DATE);
        LocalTime starting_hour_f = LocalTime.parse(request.getAsString("event_starting_hour"));
        LocalTime ending_hour_f = LocalTime.parse(request.getAsString("event_ending_hour"));
        String discipline = request.getAsString("event_sport");
        LocalDateTime isValid = eventService.isValid(discipline, date_f, starting_hour_f, ending_hour_f);
        LocalDateTime test = date_f.atTime(ending_hour_f);
        LocalDateTime test2 = date_f.atTime(starting_hour_f);
        if (isValid.toString().equals(test2.toString())) {
            eventService.addEvent(date_f,discipline, request.getAsString("event_location"), request.getAsString("event_desc"), request.getAsString("event_category"), request.getAsString("event_type"), starting_hour_f, ending_hour_f);
            return ResponseEntity.ok("Event added");
        }
        else{
                if (isValid.toString().equals(test.toString())) {
                    return new ResponseEntity<>("End before start", HttpStatus.BAD_REQUEST);
                }
                else{
                    return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * This method is used to delete an event
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity<String> delEvent(@RequestBody MultiValueMap<String, String> request){
        eventService.delEvent(request.getFirst("event_id"));
        return ResponseEntity.ok("event deleted");
    }
}
