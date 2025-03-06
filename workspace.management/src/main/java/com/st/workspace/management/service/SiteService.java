package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.Site;
import com.st.workspace.management.interfaces.SiteProjection;
import com.st.workspace.management.repository.SiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
    public List<SiteProjection> getAllSitesName() {
        return siteRepository.findAllProjectedBy();
    }
    
    

    public Optional<Site> getSiteById(Long siteId) {
        return siteRepository.findById(siteId);
    }

    public void deleteSite(Long siteId) {
        siteRepository.deleteById(siteId);
    }
}