package com.example.EmployeeManagementSystem.service.serviceImpl;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.repository.CompanyRepository;
import com.example.EmployeeManagementSystem.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    private CompanyRepository companyRepository;



    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompanyById(Long id) {
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
    public void deleteCompany(long id) {
        companyRepository.findById(id).orElseThrow(()->new ApiRequestException("This id  don't exist in our database"));
        companyRepository.deleteById(id);
    }


}
