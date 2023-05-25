package projectJEE.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.userService;
import projectJEE.Backend.entities.user;

/**
 * The Class userController.
 */
@RestController
@RequestMapping("/user")
public class userController {

    /**
     * The user service.
     */
    @Autowired
    private userService userService;

    /**
     * Add a new user.
     * @param user the user
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<user> addUser(@RequestBody user user) {
        user user1 = this.userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
}
