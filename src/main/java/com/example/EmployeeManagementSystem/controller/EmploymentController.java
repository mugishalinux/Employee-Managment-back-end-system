package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Company;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Employment;
import com.example.EmployeeManagementSystem.entity.EmploymentProducer;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.kafka.EmploymentKafkaProducer;
import com.example.EmployeeManagementSystem.kafka.KafkaProducer;
import com.example.EmployeeManagementSystem.service.CompanyService;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import com.example.EmployeeManagementSystem.service.EmployeementService;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employment")
public class EmploymentController {

    private EmployeementService employeementService;

    private EmployeeService employeeService;

    private CompanyService companyService;

    private KafkaProducer kafkaProducer;

    private EmploymentKafkaProducer employmentKafkaProducer;

    public EmploymentController(EmployeementService employeementService, EmployeeService employeeService, CompanyService companyService, KafkaProducer kafkaProducer, EmploymentKafkaProducer employmentKafkaProducer) {
        this.employeementService = employeementService;
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.kafkaProducer = kafkaProducer;
        this.employmentKafkaProducer = employmentKafkaProducer;
    }

    //assign employee to a company
    @ApiOperation(value = "assign employee to a company")
    @PostMapping("company/{compId}/employee/{empId}")
    public ResponseEntity<Employment> makeEmployment(@PathVariable("compId") long comp_id , @PathVariable("empId") long emp_id){
          Employment employment = employeementService.makeEmployement(comp_id,emp_id);
          Employee exitEmployee = employeeService.getSingleEmployeeById(emp_id);
          Company existCompany = companyService.getSingleCompanyById(comp_id);
          EmploymentProducer smsPayLoad = new EmploymentProducer();

        smsPayLoad.setMsisdn(exitEmployee.getPhoneNumber());
        smsPayLoad.setMessage("Hello Dear " + exitEmployee.getFirstName() + " now you have been assigned to " + existCompany.getCompanyName());
        smsPayLoad.setMsgRef("1237099157119");
        smsPayLoad.setSender_id("Testing");
        String message = smsPayLoad.getMsisdn()+","+smsPayLoad.getMessage()+","+smsPayLoad.getSender_id();
//        try {
//            JSONObject obj = new JSONObject(smsPayLoad);
//            String obj_temp = obj.toString();
//            System.out.println("producer object" + obj_temp);
//            kafkaProducer.sendMessage(obj_temp);
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }

        kafkaProducer.sendMessage(message);
        return new ResponseEntity<Employment>(employment, HttpStatus.CREATED);
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
