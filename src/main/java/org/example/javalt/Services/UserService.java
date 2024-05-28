package org.example.javalt.Services;

import org.example.javalt.Entities.User;
import org.example.javalt.Repositories.UserRepository;
import org.example.javalt.RequestEntities.RequestCreateUser;
import org.example.javalt.RequestEntities.RequestDeleteUser;
import org.example.javalt.RequestEntities.RequestUpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
    }
    public User createUser(RequestCreateUser requestCreateUser) {
        try {
            User user = new User();
            user.setUserName(requestCreateUser.getUserName());
            user.setPassWord(requestCreateUser.getPassWord());
            user.setEmail(requestCreateUser.getEmail());
            user.setFirstName(requestCreateUser.getFirstName());
            user.setLastName(requestCreateUser.getLastName());
            user.setIsDeleted(requestCreateUser.getIsDeleted());
            user.setRole(requestCreateUser.getRole());
            userRepository.save(user);
            return user;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public User updateUser(String id, RequestUpdateUser requestUpdateUser) {
        try {
            User user = getUserById(id);
            user.setUserName(requestUpdateUser.getUserName());
            user.setPassWord(requestUpdateUser.getPassWord());
            user.setEmail(requestUpdateUser.getEmail());
            user.setFirstName(requestUpdateUser.getFirstName());
            user.setLastName(requestUpdateUser.getLastName());
            user.setRole(requestUpdateUser.getRole());
            userRepository.save(user);
            return user;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteUserById(RequestDeleteUser requestDeleteUser) {
        User user = userRepository.findById(requestDeleteUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    public List<User> findAllActiveUsersWithActiveRoles() {
        return userRepository.findActiveUsersWithActiveRoles();
    }
}
