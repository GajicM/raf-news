package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.User;

import java.util.List;

public interface UserRepository {
    public List<User> getAllUsers();
    public User getUser(Integer id);
    public User addUser(User user);
    public User changeUserStatus(User user);
    public User findUser(String email);

    User changeUser(User user);
    public List<User> getAllUsers(int limit, int offset);
}
