package com.st.workspace.management.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    private List<String> jobGrades;
    private List<DepartmentDTO> departments;

    // Getters and Setters
}
