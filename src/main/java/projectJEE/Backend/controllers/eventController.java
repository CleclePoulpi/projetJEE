package projectJEE.Backend.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import projectJEE.Backend.service.eventService;

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
        System.out.println("test");
        eventService.addEvent(request.getAsString("event_date"),request.getAsString("event_sport"), request.getAsString("event_location"), request.getAsString("event_desc"), request.getAsString("event_category"), request.getAsString("event_type"), request.getAsString("event_starting_hour"), request.getAsString("event_ending_hour"));
        return ResponseEntity.ok("Event added");
    }
}
