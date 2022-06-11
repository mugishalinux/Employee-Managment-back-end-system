package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }
    //registering a new employee
    @ApiOperation(value = "registering new employees")
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.createEmployee(employee) , HttpStatus.CREATED);
    }
    //retrieving all employees
    @ApiOperation(value = "retrieve a list of employees")
    @GetMapping
    public List <Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //retrieving employee By Id Rest Api

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        return  new ResponseEntity<Employee>(employeeService.getSingleEmployeeById(id), HttpStatus.OK);
    }
    //updating employee information
    @ApiOperation(value = "update employee information")
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id , @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    //deleting employee by using id
    @ApiOperation(value = "delete of employees ")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deletion succesfully.", HttpStatus.OK);
    }
}
