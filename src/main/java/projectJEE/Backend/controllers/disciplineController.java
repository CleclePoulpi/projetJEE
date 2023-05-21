package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.disciplineService;

@RestController
@RequestMapping("/api/disciplines")
public class disciplineController {

    @Autowired
    private disciplineService disciplineService;

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addDiscipline(@RequestBody MultiValueMap<String, String> request) {
        disciplineService.addDiscipline(request.getFirst("sport_name"), Boolean.parseBoolean(request.getFirst("sport_paralympic")));
        return ResponseEntity.ok("Discipline added");
    }

    @ResponseBody
    @GetMapping("/get")
    public ResponseEntity<JSONObject> getDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", disciplineService.getDisciplines());
        return ResponseEntity.ok(response);
    }
}