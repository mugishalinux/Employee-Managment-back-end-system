package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/company")

public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        super();
        this.companyService = companyService;
    }
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        if(company.getCompanyCategory() == null || company.getCompanyCategory().isEmpty()){
            throw new ApiRequestException("please provide the the company category");
        } else if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new ApiRequestException("please provide the company name");
        } else if (company.getTinNumber() == null || company.getTinNumber().toString().isEmpty()) {
            throw new ApiRequestException("please provide the tin number");
        } else {
            if(company.getTinNumber().toString().length() != 10){
                throw new ApiRequestException("please tin number digit must be equal to ten");
            }else {
                return new ResponseEntity<Company>(companyService.createCompany(company) , HttpStatus.CREATED);
            }
        }

    }

    @GetMapping()
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getSingleCompanyById(@PathVariable("id") long id){
        return new ResponseEntity<Company>(companyService.getSingleCompanyById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company , @PathVariable("id") long id){
        return new ResponseEntity<Company>(companyService.updateCompany(company,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<String>("Company deletion succesfully!!!",HttpStatus.OK);
    }
    @PostMapping("/{compid}/id/{empid}")
    public ResponseEntity<Company> assignCompanyToEmployee(@PathVariable("compid") long comp_id , @PathVariable("empid") long emp_id){
        return new ResponseEntity<Company>(HttpStatus.CREATED);
    }
}
