package projectJEE.Backend.service;

import projectJEE.Backend.entities.login;
import projectJEE.Backend.entities.user;

public interface apiService {
    boolean login(login login);

    String generateToken(user user);

}
