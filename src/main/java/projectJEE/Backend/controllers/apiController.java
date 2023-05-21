package projectJEE.Backend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.entities.JWebToken;
import projectJEE.Backend.entities.login;
import projectJEE.Backend.repository.userRepository;
import projectJEE.Backend.service.impl.apiServiceImpl;
import projectJEE.Backend.entities.user;

import java.security.NoSuchAlgorithmException;
import java.net.URI;


@RestController
@RequestMapping("/api")
public class apiController {

    @Autowired
    private apiServiceImpl apiServiceImpl;

    @Autowired
    private userRepository userRepository;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response) {
        login login = new login(formData.getFirst("username"), formData.getFirst("password"));
        boolean empty= apiServiceImpl.login(login);
        System.out.println(empty);
        if (empty) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
        } else {
            user user = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).get(0);
            String token = apiServiceImpl.generateToken(user);
            final Cookie JWebToken = new Cookie("JWebToken", token);
            JWebToken.setMaxAge(86400);
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
        }
    }

    @ResponseBody
    @PostMapping("/token")
    public ResponseEntity<String> checkToken(@CookieValue("JWebToken") String bearerToken, HttpServletResponse response) throws NoSuchAlgorithmException {
        System.out.println(bearerToken);
        JWebToken incomingToken = new JWebToken(bearerToken);
        if (!incomingToken.isValid()) {
            final Cookie JWebToken = new Cookie("JWebToken", "");
            JWebToken.setMaxAge(0);
            JWebToken.setHttpOnly(true);
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("unauthorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return Response;
        }
        else {
            return new ResponseEntity<>("logged", HttpStatus.OK);
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        final Cookie JWebToken = new Cookie("JWebToken", "");
        JWebToken.setMaxAge(0);
        JWebToken.setHttpOnly(true);
        JWebToken.setPath("/");
        JWebToken.setDomain("localhost");
        ResponseEntity<String> Response = new ResponseEntity<>("logged out", HttpStatus.OK);
        response.addCookie(JWebToken);
        return Response;
    }
}
