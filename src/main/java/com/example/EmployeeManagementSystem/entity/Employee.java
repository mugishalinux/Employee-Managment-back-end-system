package com.example.EmployeeManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@ToString
@Table(name = "employee",
        uniqueConstraints = { @UniqueConstraint(
                name="email",
                columnNames="email"
        ),
                @UniqueConstraint(
                        name="phone_number",
                        columnNames = "phone_number"
                )
        }

)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "First_Name" , nullable = false)
    private String firstName;
    @Column(name = "Last_Name" , nullable = false)
    private String lastName;
    @Column(name = "Department_Name" )
    private String DepartmentName;
    @Column(name = "Date_Of_Birth")
    private LocalDate dob;
    @Column(name = "Email" , nullable = false)
    private String email;
    @Column(name = "Phone_Number" , nullable = false)
    private String phoneNumber;
    private String gender;
    private long salary;
    private LocalDate createdDate = LocalDate.now();

    public Employee(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;

}
