package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ApiRequestException;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/employee")

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }
    //registering a new employee
    @ApiOperation(value = "registering new employees")
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){



        if(employee.getLastName() == null){
            throw new ApiRequestException("please provide the last name");
        } else if (employee.getFirstName() == null || employee.getFirstName().isEmpty()){
            throw new ApiRequestException("please provide the first name");
        }else if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty()) {
            throw new ApiRequestException("please provide the phone number");
        } else if (employee.getDepartmentName() == null || employee.getDepartmentName().isEmpty()) {
            throw new ApiRequestException("please provide the department name");
        } else if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new ApiRequestException("please provide the email");
        }else if (employee.getGender() == null ||employee.getGender().isEmpty()){
            throw new ApiRequestException("please provide the gender");
        }
        else {

            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
            Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
            Matcher matcher = emailPat.matcher(employee.getEmail());
            if( matcher.find() == true){

                if(employee.getPhoneNumber().length() != 13){
                    throw new ApiRequestException("Please phone number must be eqaul to 10 include the code country : ex:: +250783381277");
                }else{
                    String mtn = "+25078";
                    String airtel = "+25073";
                    String tigo = "+25072";
                    if(employee.getPhoneNumber().substring(0,6).equals(mtn) || employee.getPhoneNumber().substring(0,6).equals(airtel) || employee.getPhoneNumber().substring(0,6).equals(tigo)){
                        return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.CREATED);
                    }else {
                        throw new ApiRequestException("Invalid Phone number : ex :: allowed cells '078','073,'072'");
                    }

                }

            }else {

                throw new ApiRequestException("This email is invalid");
            }

        }
    }
    //retrieving all employees
    @ApiOperation(value = "retrieve a list of employees")
    @GetMapping
    public List <Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //retrieving employee By Id Rest Api

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        return  new ResponseEntity<Employee>(employeeService.getSingleEmployeeById(id), HttpStatus.OK);
    }
    //updating employee information
    @ApiOperation(value = "update employee information")
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id , @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    //deleting employee by using id
    @ApiOperation(value = "delete of employees ")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deletion succesfully.", HttpStatus.OK);
    }
}
