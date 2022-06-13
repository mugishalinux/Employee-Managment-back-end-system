package com.example.EmployeeManagementSystem.service.serviceImpl;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    // saving new employee
    public Employee createEmployee(Employee employee) {
        // check if email is not already taken by other employee
        Optional<Employee> employeeEmailExist= employeeRepository.findByEmail(employee.getEmail());
        if(employeeEmailExist.isPresent()){
            throw new ApiRequestException("This email already exist in our database");
        }
        // check if phone is not already used by other doctor
        Optional<Employee> employeePhoneExist=employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
        if(employeePhoneExist.isPresent()){
            throw new ApiRequestException("This phone number already exist in our database");
        }
        return employeeRepository.save(employee);
    }

    //returning all employee
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // returning single employee
    @Override
    public Employee getSingleEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
    }

    // updating employee
    @Override
    public Employee updateEmployee(Employee employee , long id) {
        Employee existEmployee = employeeRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setLastName(employee.getLastName());
        existEmployee.setDepartmentName(employee.getDepartmentName());
        existEmployee.setDob(employee.getDob());
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setPhoneNumber(employee.getPhoneNumber());
        existEmployee.setGender(existEmployee.getGender());
        existEmployee.setSalary(employee.getSalary());
//        existEmployee.setCompany(employee.getCompany());
//        existEmployee.setCompany(employee.getCompany());
        employeeRepository.save(existEmployee);
        return existEmployee;
    }
    //deleting an employee
    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        employeeRepository.deleteById(id);
    }


}
