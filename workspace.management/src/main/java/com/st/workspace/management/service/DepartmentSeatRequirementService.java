package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.DepartmentSeatRequirement;
import com.st.workspace.management.repository.DepartmentSeatRequirementRepository;

import java.util.List;

@Service
public class DepartmentSeatRequirementService {
    @Autowired
    private DepartmentSeatRequirementRepository departmentSeatRequirementRepository;

    public List<DepartmentSeatRequirement> getAllDepartmentSeatRequirements() {
        return departmentSeatRequirementRepository.findAll();
    }

    public DepartmentSeatRequirement saveDepartmentSeatRequirement(DepartmentSeatRequirement departmentSeatRequirement) {
        return departmentSeatRequirementRepository.save(departmentSeatRequirement);
    }

    public List<DepartmentSeatRequirement> saveAllDepartmentSeatRequirements(List<DepartmentSeatRequirement> departmentSeatRequirements) {
        return departmentSeatRequirementRepository.saveAll(departmentSeatRequirements);
    }
}