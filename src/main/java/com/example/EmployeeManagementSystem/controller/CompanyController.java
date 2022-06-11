package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Company;
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
        return new ResponseEntity<Company>(companyService.createCompany(company) , HttpStatus.CREATED);
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
}
