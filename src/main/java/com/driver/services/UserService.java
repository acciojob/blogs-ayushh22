package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public User createUser(String username, String password) {
        User user = new User();
        user.setFirstName("frstName");
        user.setLastName("lstName");
        user.setUsername(username);
        user.setPassword(password);
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId) {
        try {
            userRepository3.deleteById(userId);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public User updateUser(Integer id, String password) {
//        userRepository3.save(id);
        User user;
        try {
            user = userRepository3.findById(id).get();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        user.setPassword(password);
        userRepository3.save(user);
        return user;
    }
}
