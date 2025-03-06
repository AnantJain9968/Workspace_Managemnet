package com.st.workspace.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.Department;
import com.st.workspace.management.entity.SubDepartment;

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {

	SubDepartment findByNameAndDepartment(String subDepartment,
			Department department);
}
