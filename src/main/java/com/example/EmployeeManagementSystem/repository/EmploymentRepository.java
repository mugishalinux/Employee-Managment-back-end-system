package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment,Long> {
    List<Employment> findByCompanyId(Company companyId);
    List<Employment> findByEmployeeId(Employee employeeId);

}
