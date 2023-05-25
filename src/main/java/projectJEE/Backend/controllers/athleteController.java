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

@RestController
@RequestMapping("/api/athletes")
public class athleteController {

    @Autowired
    private athletesService athletesService;

    @ResponseBody
    @PostMapping("/getAllAthletes")
    public ResponseEntity<JSONObject> getAllAthletes() {
        JSONObject response = new JSONObject();
        response.put("athletes", athletesService.getAllAthletes());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/importAthletes")
    public ResponseEntity<String> importAthletes(@RequestBody JSONObject request) throws ParseException {
        System.out.println(request.get("athletes"));
        athletesService.importathletes((List<LinkedHashMap<String, String>>) request.get("athletes"));
        return new ResponseEntity<>("imported", HttpStatus.OK);
    }
}
