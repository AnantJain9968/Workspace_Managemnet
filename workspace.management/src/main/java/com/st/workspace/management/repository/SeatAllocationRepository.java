package com.st.workspace.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.SeatAllocation;
import com.st.workspace.management.entity.SubDepartment;

@Repository
public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Long> {
    List<SeatAllocation> findBySubDepartment(SubDepartment subDepartment);
}