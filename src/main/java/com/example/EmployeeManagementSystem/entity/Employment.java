package com.example.EmployeeManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Company companyId;
    @ManyToOne
    private Employee employeeId;
    private String obtainPhoneNumber(String phoneNumber){
       return phoneNumber;
    }
}
