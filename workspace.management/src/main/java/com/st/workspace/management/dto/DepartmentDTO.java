package com.st.workspace.management.dto;

import java.util.List;

import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.entity.SubDepartment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<SubDepartmentDTO> subDepartments;

    // Getters and Setters
}