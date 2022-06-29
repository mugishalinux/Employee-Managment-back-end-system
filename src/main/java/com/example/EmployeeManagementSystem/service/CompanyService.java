package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Company getSingleCompanyById(long id);

    Company updateCompany(Company company , long id);

    Company assignEmployeeCompany(long comp_id , long emp_id );

    void deleteCompany(long id);
}
