package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.impl.statServiceImpl;


@RestController
@RequestMapping("/api")
public class statController {

    @Autowired
    private statServiceImpl statService;

    @ResponseBody
    @GetMapping("/stats")
    public ResponseEntity<JSONObject> getStat() {
        JSONObject response = new JSONObject();
        response.put("locations", statService.getMostPopularLocations());
        response.put("disciplines", statService.getMostPopularDisciplines());
        return ResponseEntity.ok(response);
    }
}
