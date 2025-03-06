package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "spam_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    private String name;
    private String email;
    private String role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "sub_department_id")
    private SubDepartment subDepartment;

//    @ManyToOne
//    @JoinColumn(name = "job_grade_id")
//    private JobGrade jobGrade;
    
    private String jobGrade;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String laptopAssetNumber;
    private String laptopSerialNumber;
    private String monitorAssetNumber;
    private String monitorSerialNumber;
    private String desktopAssetNumber;
    private String desktopSerialNumber;
}
