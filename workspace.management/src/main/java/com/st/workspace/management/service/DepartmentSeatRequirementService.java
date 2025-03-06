package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.workspace.management.entity.Department;
import com.st.workspace.management.entity.DepartmentSeatRequirement;
import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.entity.SubDepartment;
import com.st.workspace.management.repository.DepartmentRepository;
import com.st.workspace.management.repository.DepartmentSeatRequirementRepository;
import com.st.workspace.management.repository.JobGradeRepository;
import com.st.workspace.management.repository.SubDepartmentRepository;

import java.util.List;

@Service
public class DepartmentSeatRequirementService {
    @Autowired
    private DepartmentSeatRequirementRepository departmentSeatRequirementRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private SubDepartmentRepository subDepartmentRepository;
    
    @Autowired
    private JobGradeRepository jobGradeRepository;

    public List<DepartmentSeatRequirement> getAllDepartmentSeatRequirements() {
        return departmentSeatRequirementRepository.findAll();
    }

    public DepartmentSeatRequirement saveDepartmentSeatRequirement(DepartmentSeatRequirement departmentSeatRequirement) {
        return departmentSeatRequirementRepository.save(departmentSeatRequirement);
    }

    public List<DepartmentSeatRequirement> saveAllDepartmentSeatRequirements(List<DepartmentSeatRequirement> departmentSeatRequirements) {
        return departmentSeatRequirementRepository.saveAll(departmentSeatRequirements);
    }
    
    @Transactional
    public void populateDepartmentData() {
        List<DepartmentSeatRequirement> requirements = getAllDepartmentSeatRequirements();

        for (DepartmentSeatRequirement requirement : requirements) {
            // Create or find the department
            Department department = departmentRepository.findByName(requirement.getDepartment());
            if (department == null) {
                department = new Department();
                department.setName(requirement.getDepartment());
                department = departmentRepository.save(department);
            }

            // Create or find the sub-department
            SubDepartment subDepartment = subDepartmentRepository.findByNameAndDepartment(requirement.getSubDepartment(), department);
            if (subDepartment == null) {
                subDepartment = new SubDepartment();
                subDepartment.setName(requirement.getSubDepartment());
                subDepartment.setDepartment(department);
                subDepartment.setHeadCount(requirement.getHeadCount());
                subDepartment = subDepartmentRepository.save(subDepartment);
            }

            // Create job grades
            createJobGrade(subDepartment, "JT-1", requirement.getJt1());
            createJobGrade(subDepartment, "JT-2", requirement.getJt2());
            createJobGrade(subDepartment, "JT-3", requirement.getJt3());
            createJobGrade(subDepartment, "JT-4", requirement.getJt4());
            createJobGrade(subDepartment, "JT-5", requirement.getJt5());
            createJobGrade(subDepartment, "JT-6", requirement.getJt6());
            createJobGrade(subDepartment, "JT-7", requirement.getJt7());
            createJobGrade(subDepartment, "JT-8", requirement.getJt8());
            createJobGrade(subDepartment, "JT-9", requirement.getJt9());
            createJobGrade(subDepartment, "JT-10", requirement.getJt10());
        }
    }

    private void createJobGrade(SubDepartment subDepartment, String grade, Integer headCount) {
        if (headCount != null && headCount > 0) {
            JobGrade jobGrade = new JobGrade();
            jobGrade.setSubDepartment(subDepartment);
            jobGrade.setGrade(grade);
            jobGrade.setHeadCount(headCount);
            jobGradeRepository.save(jobGrade);
        }
    }
}