package com.st.workspace.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.Site;
import com.st.workspace.management.interfaces.SiteProjection;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
	List<SiteProjection> findAllProjectedBy();

	Site findByNameAndLocation(String name, String location);
}
