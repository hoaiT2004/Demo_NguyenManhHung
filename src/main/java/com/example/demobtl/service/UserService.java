package com.example.demobtl.service;

import com.example.demobtl.model.Employee;
import com.example.demobtl.model.User;
import com.example.demobtl.repository.EmployeeRepository;
import com.example.demobtl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean checkLogin(User u) {
        Optional<User> userOptional = userRepository.findByUsername(u.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(u.getPassword());
        }
        return false;
    }

    public boolean registerUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false; // Username already exists
        }
        userRepository.save(user); // Save password as plain text per requirement
        return true;
    }
}
