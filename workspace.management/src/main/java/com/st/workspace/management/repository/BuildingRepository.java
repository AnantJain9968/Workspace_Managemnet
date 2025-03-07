package com.st.workspace.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.Building;
import com.st.workspace.management.entity.Site;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

	Building findByNameAndSite(String building, Site site);

	Optional<Building> findBySite(Long id);
}