package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.SubDepartment;
import com.st.workspace.management.repository.SubDepartmentRepository;

import java.util.List;

@Service
public class SubDepartmentService {
    @Autowired
    private SubDepartmentRepository subDepartmentRepository;

    public List<SubDepartment> getAllSubDepartments() {
        return subDepartmentRepository.findAll();
    }

    public SubDepartment saveSubDepartment(SubDepartment subDepartment) {
        return subDepartmentRepository.save(subDepartment);
    }
}