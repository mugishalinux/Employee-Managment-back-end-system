package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
   Employee createEmployee(Employee employee);
   List<Employee> getAllEmployees();
   Employee getSingleEmployeeById(long id);

   Employee updateEmployee(Employee employee , long id);

   void deleteEmployee(long id);
}
