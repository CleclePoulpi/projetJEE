package projectJEE.Backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJEE.Backend.entities.login;
import projectJEE.Backend.service.apiService;
import projectJEE.Backend.repository.userRepository;

@Service
public class apiServiceImpl implements apiService {

    @Autowired
    private userRepository userRepository;

    public boolean login(login login) {
        return userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword()).isEmpty();
    }
}
