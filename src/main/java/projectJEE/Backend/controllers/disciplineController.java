package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.disciplineService;

/**
 * This class is the controller for the discipline entity
 * It handles the requests from the frontend
 * It calls the service layer to execute the requests
 */
@RestController
@RequestMapping("/api/disciplines")
public class disciplineController {

    /**
     * The service layer for the discipline entity
     */
    @Autowired
    private disciplineService disciplineService;

    /**
     * This method is used to add a discipline
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<String> addDiscipline(@RequestBody MultiValueMap<String, String> request) {
        disciplineService.addDiscipline(request.getFirst("sport_name"), Boolean.parseBoolean(request.getFirst("sport_paralympic")));
        return new ResponseEntity<>("sport added", HttpStatus.OK);
    }

    /**
     * This method is used to get the list of disciplines
     * @return the list of disciplines
     */
    @ResponseBody
    @PostMapping("/get")
    public ResponseEntity<JSONObject> getDisciplines() {
        JSONObject response = new JSONObject();
        response.put("disciplines", disciplineService.getDisciplines());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used to delete a discipline
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity<String> delDiscipline(@RequestBody MultiValueMap<String, String> request) {
        disciplineService.delDiscipline(Integer.parseInt(request.getFirst("sport_id")));
        return new ResponseEntity<>("sports deleted", HttpStatus.OK);
    }
}
