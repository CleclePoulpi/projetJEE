package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.disciplineService;

import java.net.URI;

@RestController
@RequestMapping("/api/disciplines")
public class disciplineController {

    @Autowired
    private disciplineService disciplineService;

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addDiscipline(@RequestBody MultiValueMap<String, String> request) {
        disciplineService.addDiscipline(request.getFirst("sport_name"), Boolean.parseBoolean(request.getFirst("sport_paralympic")));
        return new ResponseEntity<>("sport added", HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/get")
    public ResponseEntity<JSONObject> getDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", disciplineService.getDisciplines());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity<String> delDiscipline(@RequestBody MultiValueMap<String, String> request) {
        disciplineService.delDiscipline(Integer.parseInt(request.getFirst("sport_id")));
        return new ResponseEntity<>("sports deleted", HttpStatus.OK);
    }
}
