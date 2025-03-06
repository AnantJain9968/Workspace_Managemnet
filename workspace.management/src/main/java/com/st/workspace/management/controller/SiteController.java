package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.Site;
import com.st.workspace.management.interfaces.SiteProjection;
import com.st.workspace.management.service.SiteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin("*")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        Site savedSite = siteService.saveSite(site);
        return ResponseEntity.ok(savedSite);
    }

    @GetMapping
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> sites = siteService.getAllSites();
        return ResponseEntity.ok(sites);
    }
    
    @GetMapping("/siteName")
    public ResponseEntity<List<SiteProjection>> getAllSitesName() {
    	 List<SiteProjection> sites = siteService.getAllSitesName();
    	    for (SiteProjection site : sites) {
    	        System.out.println("Site ID: " + site.getSiteId());
    	        System.out.println("Name: " + site.getName());
    	        System.out.println("Location: " + site.getLocation());
    	    }
    	    return ResponseEntity.ok(sites);
    }
    
    
   

    @GetMapping("/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long id) {
        Optional<Site> site = siteService.getSiteById(id);
        return site.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}