package com.st.workspace.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.CubicalTypeAssociation;

@Repository
public interface CubicalTypeAssociationRepository extends JpaRepository<CubicalTypeAssociation, Long> {
    List<CubicalTypeAssociation> findByJobGrade(String jobGrade);
}