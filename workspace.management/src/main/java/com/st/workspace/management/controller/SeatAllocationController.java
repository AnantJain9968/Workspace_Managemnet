package com.st.workspace.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.workspace.management.dto.EmployeeAllocationRequest;
import com.st.workspace.management.entity.Department;
import com.st.workspace.management.entity.SeatAllocation;
import com.st.workspace.management.service.SeatAllocationService;

@RestController
@RequestMapping("/api/seat-allocation")
@CrossOrigin("*")
public class SeatAllocationController {
    @Autowired
    private SeatAllocationService seatAllocationService;

    @PostMapping("/allocate")
    public ResponseEntity<Void> allocateSeats() {
        seatAllocationService.allocateSeats();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/allocate-new-department")
    public ResponseEntity<Void> allocateNewDepartment(@RequestBody Department department) {
        seatAllocationService.allocateNewDepartment(department);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/department-allocation-status/{departmentId}")
    public ResponseEntity<List<SeatAllocation>> getDepartmentAllocationStatus(@PathVariable Long departmentId) {
        List<SeatAllocation> allocations = seatAllocationService.getDepartmentAllocationStatus(departmentId);
        if (allocations == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allocations);
    }
    
    @PostMapping("/allocateEmployee")
    public ResponseEntity<String> allocateEmployee(@RequestBody EmployeeAllocationRequest request) {
    	seatAllocationService.allocateEmployee(request);
        return ResponseEntity.ok("Employee is allocated");
    }
}
