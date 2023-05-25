package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import projectJEE.Backend.service.athletesService;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This class is the controller for the athlete entity
 * It handles the requests from the frontend
 * It calls the service layer to execute the requests
 */
@RestController
@RequestMapping("/api/athletes")
public class athleteController {
    /**
     * The athletes service
     */
    @Autowired
    private athletesService athletesService;

    /**
     * This method is used to get all the athletes
     * @return a response entity with the list of all the athletes
     */
    @ResponseBody
    @PostMapping("/getAllAthletes")
    public ResponseEntity<JSONObject> getAllAthletes() {
        JSONObject response = new JSONObject();
        response.put("athletes", athletesService.getAllAthletes());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used to drop all the athletes
     * @return a response entity with the message "dropped"
     */
    @ResponseBody
    @PostMapping("/dropAthletes")
    public ResponseEntity<String> dropAthletes() {
        athletesService.dropAthletes();
        return new ResponseEntity<>("dropped", HttpStatus.OK);
    }


    /**
     * This method is used to import athletes
     * @param request the request containing the athletes to import in a JSON object
     * @return a response entity with the message "imported"
     * @throws ParseException if the date is not in the correct format
     */
    @ResponseBody
    @PostMapping("/importAthletes")
    public ResponseEntity<String> importAthletes(@RequestBody JSONObject request) throws ParseException {
        System.out.println(request.get("athletes"));
        athletesService.importathletes((List<LinkedHashMap<String, String>>) request.get("athletes"));
        return new ResponseEntity<>("imported", HttpStatus.OK);
    }
}
