package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.SubDepartment;
import com.st.workspace.management.service.SubDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/sub-department")
@CrossOrigin("*")
public class SubDepartmentController {
    @Autowired
    private SubDepartmentService subDepartmentService;

    @GetMapping
    public ResponseEntity<List<SubDepartment>> getAllSubDepartments() {
        return ResponseEntity.ok(subDepartmentService.getAllSubDepartments());
    }

    @PostMapping
    public ResponseEntity<SubDepartment> saveSubDepartment(@RequestBody SubDepartment subDepartment) {
        return ResponseEntity.ok(subDepartmentService.saveSubDepartment(subDepartment));
    }
}
