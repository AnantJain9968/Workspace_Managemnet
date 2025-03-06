package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.repository.JobGradeRepository;

import java.util.List;

@Service
public class JobGradeService {
    @Autowired
    private JobGradeRepository jobGradeRepository;

    public List<JobGrade> getAllJobGrades() {
        return jobGradeRepository.findAll();
    }

    public JobGrade saveJobGrade(JobGrade jobGrade) {
        return jobGradeRepository.save(jobGrade);
    }
}
