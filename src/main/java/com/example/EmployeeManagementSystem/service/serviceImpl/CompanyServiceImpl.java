package com.example.EmployeeManagementSystem.service.serviceImpl;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Employment;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.repository.CompanyRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.repository.EmploymentRepository;
import com.example.EmployeeManagementSystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    private CompanyRepository companyRepository;

    public EmployeeRepository employeeRepository;

    @Autowired
    private EmploymentRepository employmentRepository;



    @Override
    public Company createCompany(Company company) {
        Optional<Company> tinNumberExist= companyRepository.findByTinNumber(company.getTinNumber());
        if(tinNumberExist.isPresent()){
            throw new ApiRequestException("This Tin Number already exist in our database");
        }
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompanyById(long id) {
//      return companyRepository.findById(id).orElseThrow(()->new CompanyResourcesNotFoundException("Company","Id",id));
        return companyRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
    }

    @Override
    public Company updateCompany(Company company, long id) {
        Company existCompany = companyRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        existCompany.setTinNumber(company.getTinNumber());
        existCompany.setCompanyName(company.getCompanyName());
        existCompany.setCompanyCategory(company.getCompanyCategory());
        companyRepository.save(existCompany);
        return existCompany;
    }

    @Override
    public Company assignEmployeeCompany(long comp_id, long emp_id) {
        return null;
    }

    @Override
    public void deleteCompany(long id) {
        companyRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        companyRepository.deleteById(id);
    }

}
