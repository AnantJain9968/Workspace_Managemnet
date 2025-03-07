package com.st.workspace.management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.dto.DepartmentDTO;
import com.st.workspace.management.dto.DepartmentResponseDTO;
import com.st.workspace.management.dto.SubDepartmentDTO;
import com.st.workspace.management.entity.Department;
import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.repository.DepartmentRepository;
import com.st.workspace.management.repository.JobGradeRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private JobGradeRepository jobGradeRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    public DepartmentResponseDTO getAllDepartmentsAndJobGrades() {
        List<Department> departments = departmentRepository.findAll();

        List<DepartmentDTO> departmentDTOs = departments.stream().map(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getDepartmentId());
            departmentDTO.setName(department.getName());

            List<SubDepartmentDTO> subDepartmentDTOs = department.getSubDepartments().stream().map(subDepartment -> {
                SubDepartmentDTO subDepartmentDTO = new SubDepartmentDTO();
                subDepartmentDTO.setId(subDepartment.getSubDepartmentId());
                subDepartmentDTO.setName(subDepartment.getName());
                return subDepartmentDTO;
            }).collect(Collectors.toList());

            departmentDTO.setSubDepartments(subDepartmentDTOs);
            return departmentDTO;
        }).collect(Collectors.toList());

        List<String> distinctJobGrades = jobGradeRepository.findDistinctGrades();

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setDepartments(departmentDTOs);
        responseDTO.setJobGrades(distinctJobGrades);

        return responseDTO;
    }
}
