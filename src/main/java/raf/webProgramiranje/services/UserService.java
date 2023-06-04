package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUser(Integer id);
    public User changeUserStatus(User user);
    public String login(String email, String password);

    boolean isAuthorized(String token);
    boolean isAuthorizedAdmin(String token);
    User changeUser(User user);

    List<User> getAllUsers(int limit, int offset);
}
