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
public class EmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getListEmployee(String name) {
        return employeeRepository.getEmployeeStartwithfullName(name);
    }

    @Transactional
    public boolean deleteEmployee(Long userId) {
        Optional<Employee> employee = employeeRepository.findById(userId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
