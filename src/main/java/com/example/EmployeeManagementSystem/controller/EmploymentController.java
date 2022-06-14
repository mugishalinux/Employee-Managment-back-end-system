package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employment;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.service.EmployeementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employment")
public class EmploymentController {

    private EmployeementService employeementService;

    public EmploymentController(EmployeementService employeementService) {
        this.employeementService = employeementService;
    }
    //assign employee to a company
    @ApiOperation(value = "assign employee to a company")
    @PostMapping("company/{compId}/employee/{empId}")
    public ResponseEntity<Employment> makeEmployement(@PathVariable("compId") long comp_id , @PathVariable("empId") long emp_id){
        return new ResponseEntity<Employment>(employeementService.makeEmployement(comp_id,emp_id), HttpStatus.CREATED);
    }
    @ApiOperation(value = "assign company to an employee")
    @PostMapping("/employee/{empId}/company/{compId}")
    public ResponseEntity<Employment> assigningCompanyToEmployees(@PathVariable("empId") long emp_id , @PathVariable("compId") long comp_id){
        return new ResponseEntity<Employment>(employeementService.assigningCompanyToEmployees(emp_id,comp_id), HttpStatus.CREATED);
    }
    // get employee of certain company by using company id
    @ApiOperation(value = "get list employee to a certain company by using passing company ID")
    @GetMapping("company/{company_id}")
    public ResponseEntity getCompanyEmployees(@PathVariable("company_id") long company_id){
        return new ResponseEntity(employeementService.getCompanyEmployee(company_id),HttpStatus.OK);
    }
    // get list of company to certain employee by using employee id
    @ApiOperation(value = "get list of company to certain employee by using employee ID")
    @GetMapping("employee/{employee_id}")
    public ResponseEntity getEmployeeCompanies(@PathVariable("employee_id") long employee_id){
        return new ResponseEntity(employeementService.getEmployeeCompanies(employee_id),HttpStatus.OK);
    }

}
