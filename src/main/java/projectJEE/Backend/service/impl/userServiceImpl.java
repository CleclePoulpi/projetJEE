package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.service.userService;
import projectJEE.Backend.entities.user;
import projectJEE.Backend.repository.userRepository;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;

    @Override
    public user addUser(user user) {
        return userRepository.save(user);
    }
}
