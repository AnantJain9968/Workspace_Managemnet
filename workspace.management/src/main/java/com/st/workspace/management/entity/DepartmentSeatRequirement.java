package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "spam_department_seat_requirement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSeatRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String department;
    private String subDepartment;
    private Integer headCount;
    private Integer jt1;
    private Integer jt2;
    private Integer jt3;
    private Integer jt4;
    private Integer jt5;
    private Integer jt6;
    private Integer jt7;
    private Integer jt8;
    private Integer jt9;
    private Integer jt10;
}