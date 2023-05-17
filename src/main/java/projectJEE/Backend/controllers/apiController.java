package projectJEE.Backend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
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
import java.util.List;
import javax.print.DocFlavor;
import java.net.URI;
import java.util.Map;


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
        if (empty) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).location(URI.create("/")).build();
        } else {
            user user = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).get(0);
            String token = apiServiceImpl.generateToken(user);
            final Cookie JWebToken = new Cookie("JWebToken", token);
            JWebToken.setMaxAge(86400);
            //JWebToken.setHttpOnly(true); @TODO: Si on laisse ce flag, peut pas lire le cookie en JS sur le site => revoir l'architecture d'authentification
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
        }
    }

    @ResponseBody
    @PostMapping("/check-token")
    public ResponseEntity<String> checkToken(@CookieValue("JWebToken") String bearertoken) throws NoSuchAlgorithmException {
        System.out.println(bearertoken);
        JWebToken incomingToken = new JWebToken(bearertoken);
        if (!incomingToken.isValid()) {
            return new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
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
