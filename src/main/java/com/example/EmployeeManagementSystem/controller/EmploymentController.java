package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employment;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.service.EmployeementService;
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

    @PostMapping("/{compid}/employeeId/{empid}")
    public ResponseEntity<Employment> makeEmployement(@PathVariable("compid") long comp_id , @PathVariable("empid") long emp_id){
        return new ResponseEntity<Employment>(employeementService.makeEmployement(comp_id,emp_id), HttpStatus.CREATED);
    }


    @GetMapping("/{company_id}")
    public ResponseEntity getCompanyEmployees(@PathVariable("company_id") long company_id){
        return new ResponseEntity(employeementService.getCompanyEmployee(company_id),HttpStatus.OK);
    }


}
