package com.st.workspace.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.entity.Seat;
import com.st.workspace.management.entity.SubDepartment;

@Repository
public interface JobGradeRepository extends JpaRepository<JobGrade, Long> {
	
	@Query("SELECT DISTINCT j.grade FROM JobGrade j order by j.grade")
    List<String> findDistinctGrades();

	Optional<JobGrade> findBySubDepartmentAndGrade(SubDepartment subDepartment, String jobGradeName);
}