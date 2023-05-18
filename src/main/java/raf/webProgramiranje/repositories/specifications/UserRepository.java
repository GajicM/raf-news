package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.User;

import java.util.List;

public interface UserRepository {
    public List<User> getAllUsers();
    public User getUser(Integer id);
    public User addUser(User user);
    public User changeUserStatus(User user);
}
