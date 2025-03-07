package com.st.workspace.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.JobGrade;
import com.st.workspace.management.entity.SeatAllocation;
import com.st.workspace.management.entity.SubDepartment;

@Repository
public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Long> {
	List<SeatAllocation> findBySubDepartment(SubDepartment subDepartment);

	@Query("SELECT sa FROM SeatAllocation sa WHERE sa.subDepartment = :subDepartment AND sa.seat.seatStatus = :seatStatus AND sa.subDepartment.jobGrades = :jobGrade")
	Optional<SeatAllocation> findFirstBySubDepartmentAndSeat_SeatStatusAndJobGrade(
			@Param("subDepartment") SubDepartment subDepartment, @Param("seatStatus") String seatStatus,
			@Param("jobGrade") JobGrade jobGrade);

	List<SeatAllocation> findBySubDepartmentAndStatus(SubDepartment subDepartment, String string);
}