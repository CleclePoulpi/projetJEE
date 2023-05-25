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

@RestController
@RequestMapping("/api/events")
public class eventController {

    @Autowired
    private eventService eventService;

    @ResponseBody
    @PostMapping("/get")
    public ResponseEntity<JSONObject> getEvents(){
        JSONObject response = new JSONObject();
        response.put("events", eventService.getEvents());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody JSONObject request){
        LocalDate date_f = LocalDate.parse(request.getAsString("event_date"), DateTimeFormatter.ISO_DATE);
        LocalTime starting_hour_f = LocalTime.parse(request.getAsString("event_starting_hour"));
        LocalTime ending_hour_f = LocalTime.parse(request.getAsString("event_ending_hour"));
        LocalDateTime isValid = eventService.isValid(request.getAsString("event_discipline"), date_f, starting_hour_f, ending_hour_f);
        if (isValid == null){
            eventService.addEvent(date_f,request.getAsString("event_sport"), request.getAsString("event_location"), request.getAsString("event_desc"), request.getAsString("event_category"), request.getAsString("event_type"), starting_hour_f, ending_hour_f);
            return ResponseEntity.ok("Event added");
        }
        else{
                LocalDateTime test = date_f.atTime(ending_hour_f);
                if (isValid == test) {
                    return new ResponseEntity<>("End before start", HttpStatus.BAD_REQUEST);
                }
                else{
                    return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity<String> delEvent(@RequestBody MultiValueMap<String, String> request){
        eventService.delEvent(request.getFirst("event_id"));
        return ResponseEntity.ok("event deleted");
    }
}
