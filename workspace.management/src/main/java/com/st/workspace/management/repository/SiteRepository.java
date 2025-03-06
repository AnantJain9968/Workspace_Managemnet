package com.st.workspace.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.workspace.management.entity.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}
