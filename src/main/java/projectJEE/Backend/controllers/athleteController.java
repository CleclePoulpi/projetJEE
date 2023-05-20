package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import projectJEE.Backend.service.athletesService;

@RestController
@RequestMapping("/api/athletes")
public class athleteController {

    @Autowired
    private athletesService athletesService;

    @ResponseBody
    @RequestMapping("/getAllAthletes")
    public ResponseEntity<JSONObject> getAllAthletes() {
        JSONObject response = new JSONObject();
        response.put("athletes", athletesService.getAllAthletes());
        return ResponseEntity.ok(response);
    }
}
