package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.User;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UserService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUser(Integer id);
    public User changeUserStatus(User user);
    public String login(String email, String password);

    boolean isAuthorized(String token);
}
