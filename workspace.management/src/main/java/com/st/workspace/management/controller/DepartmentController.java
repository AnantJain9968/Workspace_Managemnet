package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.dto.DepartmentResponseDTO;
import com.st.workspace.management.entity.Department;
import com.st.workspace.management.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }
    
    @GetMapping("/getDepartmentAndGrades")
    public ResponseEntity<DepartmentResponseDTO>  getAllDepartmentsAndJobGrades() {
    	return ResponseEntity.ok(departmentService.getAllDepartmentsAndJobGrades());
    }
}