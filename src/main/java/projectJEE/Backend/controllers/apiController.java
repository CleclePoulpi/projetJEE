package projectJEE.Backend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response) {
        login login = new login(formData.getFirst("username"), formData.getFirst("password"));
        boolean empty= apiServiceImpl.login(login);
        if (empty) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            user user = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).get(0);
            String token = apiServiceImpl.generateToken(user);
            final Cookie JWebToken = new Cookie("JWebToken", token);
            JWebToken.setMaxAge(86400);
            JWebToken.setHttpOnly(true);
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return Response;
        }
    }

}
