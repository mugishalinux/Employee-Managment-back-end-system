package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Company;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Company getSingleCompanyById(Long id);

    Company updateCompany(Company company , long id);

    void deleteCompany(long id);
}
