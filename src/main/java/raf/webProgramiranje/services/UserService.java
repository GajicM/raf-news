package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUser(Integer id);
    public User changeUserStatus(User user);
}
