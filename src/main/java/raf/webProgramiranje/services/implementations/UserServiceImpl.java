package raf.webProgramiranje.services.implementations;

import raf.webProgramiranje.entities.User;
import raf.webProgramiranje.repositories.specifications.UserRepository;
import raf.webProgramiranje.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.getAllUsers();
    }
    @Override
    public User getUser(Integer id){
        return userRepository.getUser(id);
    }
    public User changeUserStatus(User user){
        return userRepository.changeUserStatus(user);
    }
}
