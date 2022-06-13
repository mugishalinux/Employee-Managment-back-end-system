package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Employment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeementService{
    Employment makeEmployement(long emp_id ,long comp_id);
    Company getCompanyEmployee(long comp_id);
}
