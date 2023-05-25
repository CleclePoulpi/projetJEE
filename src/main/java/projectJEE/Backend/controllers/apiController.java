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

/**
 * This class is the controller for the api
 * It contains the login/logout and checkToken methods
 */
@RestController
@RequestMapping("/api")
public class apiController {

    /**
     * The api service
     */
    @Autowired
    private apiServiceImpl apiServiceImpl;

    /**
     * This is the userRepository object that we will use to manipulate the database
     */
    @Autowired
    private userRepository userRepository;

    /**
     * This method is used to log in the user
     * @param formData the form data
     * @param response the response
     * @return ResponseEntity<String>
     */
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
            JWebToken.setPath("/");
            JWebToken.setDomain("localhost");
            ResponseEntity<String> Response = new ResponseEntity<>("authorized", HttpStatus.OK);
            response.addCookie(JWebToken);
            return Response;
        }
    }

    /**
     * This method is used to check if the token is valid
     * @param bearerToken the token
     * @param response the response
     * @return ResponseEntity<String>
     * @throws NoSuchAlgorithmException
     */
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
            ResponseEntity<String> Response = new ResponseEntity<>("unauthorized", HttpStatus.UNAUTHORIZED);
            response.addCookie(JWebToken);
            return Response;
        }
        else {
            String privilege = incomingToken.getPrivilege();
            return new ResponseEntity<>(privilege, HttpStatus.OK);
        }
    }

    /**
     * This method is used to log out the user
     * @param response the response
     * @return ResponseEntity<String>
     */
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
