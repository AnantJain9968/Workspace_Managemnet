package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.service.JobGradeService;

import java.util.List;

@RestController
@RequestMapping("/api/job-grade")
public class JobGradeController {
    @Autowired
    private JobGradeService jobGradeService;

    @GetMapping
    public ResponseEntity<List<JobGrade>> getAllJobGrades() {
        return ResponseEntity.ok(jobGradeService.getAllJobGrades());
    }

    @PostMapping
    public ResponseEntity<JobGrade> saveJobGrade(@RequestBody JobGrade jobGrade) {
        return ResponseEntity.ok(jobGradeService.saveJobGrade(jobGrade));
    }
}
