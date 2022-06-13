package com.example.EmployeeManagementSystem.service.serviceImpl;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Employment;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.repository.CompanyRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.repository.EmploymentRepository;
import com.example.EmployeeManagementSystem.service.EmployeementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployementServiceImpl implements EmployeementService {

    private EmploymentRepository employementRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;



    public EmployementServiceImpl(EmploymentRepository employementRepository) {
        this.employementRepository = employementRepository;
    }
    @Override
    public Employment makeEmployement(long emp_id ,long comp_id) {
        Optional<Company> companyExist= companyRepository.findById(comp_id);
        if(!companyExist.isPresent()){
            throw new ApiRequestException("This company id  don't exist in our database");
        }else {
            System.out.println(comp_id);
        }
        Optional<Employee> employeeExist= employeeRepository.findById(emp_id);
        if(!employeeExist.isPresent()){
            throw new ApiRequestException("This employee id  don't exist in our database");
        }else {
            System.out.print(emp_id);
        }
        Company c=companyExist.get();
        Employee em=employeeExist.get();

        Employment e=new Employment();
        e.setCompanyId(c);
        e.setEmployeeId(em);

        System.out.println(emp_id +  " ---  "  + comp_id);
        return employementRepository.save(e);
    }

    @Override
    public Company getCompanyEmployee(long comp_id) {
        Optional<Company> companyExist=companyRepository.findById(comp_id);
        if(!companyExist.isPresent()){
            // error
        }
        Company com=companyExist.get();
        List<Employment> employess=employementRepository.findByCompanyId(com);
        List<Employee> employees=new ArrayList<>();
        for(Employment e:employess){
            employees.add(e.getEmployeeId());
        }

        com.setEmployees(employees);



        return com;
    }

}
