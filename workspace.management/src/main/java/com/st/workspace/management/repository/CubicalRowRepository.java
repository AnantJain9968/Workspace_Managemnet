package com.st.workspace.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.CubicalRow;

@Repository
public interface CubicalRowRepository extends JpaRepository<CubicalRow, Long> {
}
