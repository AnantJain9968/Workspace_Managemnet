package com.st.workspace.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spam_job_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobGradeId")
public class JobGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobGradeId;

    @ManyToOne
    @JoinColumn(name = "sub_department_id")
    private SubDepartment subDepartment;

    private String grade; // JT-1, JT-2, ..., JT-10
    private Integer headCount;
    private String status; // Unallocated, Reserved, Allocated
    
    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = "Unallocated";
        }
    }
}
