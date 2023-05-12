package projectJEE.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.entities.login;
import projectJEE.Backend.service.impl.apiServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class apiController {

    @Autowired
    private apiServiceImpl apiServiceImpl;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> formData) {
        login login = new login(formData.getFirst("username"), formData.getFirst("password"));
        boolean empty= apiServiceImpl.login(login);
        if (empty) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("authorized", HttpStatus.OK);
        }
    }
}
