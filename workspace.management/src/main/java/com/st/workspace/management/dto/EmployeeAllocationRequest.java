package com.st.workspace.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAllocationRequest {
    private String employeeName;
    private Long departmentId;
    private Long subDepartmentId;
    private String jobGradeName;
}