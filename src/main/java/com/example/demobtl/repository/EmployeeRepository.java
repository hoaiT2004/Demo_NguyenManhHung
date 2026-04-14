package com.example.demobtl.repository;

import com.example.demobtl.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.fullName LIKE CONCAT(:fullName, '%')")
    List<Employee> getEmployeeStartwithfullName(@Param("fullName") String fullName);
}
