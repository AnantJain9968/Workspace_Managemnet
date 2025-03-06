package com.st.workspace.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "spam_job_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobGradeId;

    @ManyToOne
    @JoinColumn(name = "sub_department_id")
    private SubDepartment subDepartment;

    private String grade; // JT-1, JT-2, ..., JT-10
    private Integer headCount;
}
