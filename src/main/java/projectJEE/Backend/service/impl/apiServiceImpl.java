package projectJEE.Backend.service.impl;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.JWebToken;
import projectJEE.Backend.entities.login;
import projectJEE.Backend.entities.user;
import projectJEE.Backend.service.apiService;
import projectJEE.Backend.repository.userRepository;

import java.time.LocalDateTime;
import org.json.JSONObject;

/**
 * Implementation of the apiService interface.
 */
@Service
public class apiServiceImpl implements apiService {
    private static final int EXPIRATION_TIME = 900000;

    /**
     * The user repository.
     */
    @Autowired
    private userRepository userRepository;

    /**
     * Check if the user has given the right credentials.
     * @param login the login
     * @return the response entity
     */
    public boolean login(login login) {
        return userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).isEmpty();
    }

    /**
     * Generate a token for the user.
     * @param user the user
     * @return the string
     */
    public String generateToken(user user) {

        JSONObject jwtPayload = new JSONObject();
        jwtPayload.put("status", "connected");
        jwtPayload.put("sub", user.getUsername());
        jwtPayload.put("privilege", user.getPrivilege().toString());

        JSONArray aud = new JSONArray();
        aud.put("user");
        jwtPayload.put("aud", aud);

        LocalDateTime ldt = LocalDateTime.now().plus(EXPIRATION_TIME, java.time.temporal.ChronoUnit.MILLIS);

        jwtPayload.put("exp", ldt.toEpochSecond(java.time.ZoneOffset.UTC));

        String token = new JWebToken(jwtPayload).toString();
        return token;

    }
}
