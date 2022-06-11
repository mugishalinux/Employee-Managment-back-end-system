package com.example.EmployeeManagementSystem.service.serviceImpl;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getSingleEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
    }

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

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        employeeRepository.deleteById(id);
    }


}
