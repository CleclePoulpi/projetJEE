package projectJEE.Backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.entities.login;

@RestController
@RequestMapping("/api")
public class apiController {

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<login> login(@RequestBody login login) {

    }
}
