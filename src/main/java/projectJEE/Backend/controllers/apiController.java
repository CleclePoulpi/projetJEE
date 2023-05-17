package projectJEE.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.entities.login;
import projectJEE.Backend.repository.userRepository;
import projectJEE.Backend.service.impl.apiServiceImpl;
import projectJEE.Backend.entities.user;

@RestController
@RequestMapping("/api")
public class apiController {

    @Autowired
    private apiServiceImpl apiServiceImpl;

    @Autowired
    private userRepository userRepository;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> formData) {
        login login = new login(formData.getFirst("username"), formData.getFirst("password"));
        boolean empty= apiServiceImpl.login(login);
        if (empty) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            user user = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).get(0);
            String token = apiServiceImpl.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
}
