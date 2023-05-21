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
    @GetMapping("/getAllAthletes")
    public ResponseEntity<JSONObject> getAllAthletes() {
        JSONObject response = new JSONObject();
        response.put("athletes", athletesService.getAllAthletes());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/importathletes")
    public ResponseEntity<String> importathletes(@RequestBody MultiValueMap<String, File> request) throws FileNotFoundException {
        athletesService.importathletes(request.getFirst("file"));
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/athletesAdmin")).build();
    }
}
