package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.service.userService;
import projectJEE.Backend.entities.user;
import projectJEE.Backend.repository.userRepository;

/**
 * Implementation of the service interface userService
 */
@Service
public class userServiceImpl implements userService{

    /**
     * userRepository
     */
    @Autowired
    private userRepository userRepository;

    /**
     * The method adds a user
     */
    @Override
    public user addUser(user user) {
        return userRepository.save(user);
    }
}
