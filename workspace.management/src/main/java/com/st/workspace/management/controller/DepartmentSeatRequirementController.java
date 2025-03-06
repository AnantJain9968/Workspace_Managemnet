package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.DepartmentSeatRequirement;
import com.st.workspace.management.service.DepartmentSeatRequirementService;

import java.util.List;

@RestController
@RequestMapping("/api/department-seat-requirement")
public class DepartmentSeatRequirementController {
    @Autowired
    private DepartmentSeatRequirementService departmentSeatRequirementService;

    @GetMapping
    public ResponseEntity<List<DepartmentSeatRequirement>> getAllDepartmentSeatRequirements() {
        return ResponseEntity.ok(departmentSeatRequirementService.getAllDepartmentSeatRequirements());
    }

    @PostMapping
    public ResponseEntity<DepartmentSeatRequirement> saveDepartmentSeatRequirement(@RequestBody DepartmentSeatRequirement departmentSeatRequirement) {
        return ResponseEntity.ok(departmentSeatRequirementService.saveDepartmentSeatRequirement(departmentSeatRequirement));
    }

    @PostMapping("/bulkLoad")
    public ResponseEntity<List<DepartmentSeatRequirement>> saveAllDepartmentSeatRequirements(@RequestBody List<DepartmentSeatRequirement> departmentSeatRequirements) {
        return ResponseEntity.ok(departmentSeatRequirementService.saveAllDepartmentSeatRequirements(departmentSeatRequirements));
    }
}