package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spam_sub_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subDepartmentId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String name;
    private Integer headCount;

    @OneToMany(mappedBy = "subDepartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobGrade> jobGrades;
}