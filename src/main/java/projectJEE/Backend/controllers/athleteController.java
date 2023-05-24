package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.FileNotFoundException;
import java.net.URI;
import projectJEE.Backend.service.athletesService;

import java.io.File;

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
    public ResponseEntity<String> importAthletes(@RequestBody MultiValueMap<String, File> request) throws FileNotFoundException {
        System.out.println("ok");
        athletesService.importathletes(request.getFirst("file"));
        return new ResponseEntity<>("imported", HttpStatus.OK);
    }
}
