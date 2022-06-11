package com.example.EmployeeManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@ToString
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Tin_Number" , nullable = false)
    private Long tinNumber;
    @Column(name = "Company_Name")
    private String companyName;
    @Column(name = "Company_Category")
    private String companyCategory;
    @Column(name = "Created_At")
    private LocalDate createdDate = LocalDate.now();
}
