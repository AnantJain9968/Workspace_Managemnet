package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.Building;
import com.st.workspace.management.entity.Site;
import com.st.workspace.management.interfaces.SiteProjection;
import com.st.workspace.management.service.BuildingService;
import com.st.workspace.management.service.SiteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/building")
@CrossOrigin("*")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private SiteService siteService;

   
    @GetMapping
    public ResponseEntity<List<Site>> getAllBuildings() {
        List<Site> sites = buildingService.getAllSites();
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
    public ResponseEntity<Building> getBuildingsBySiteId(@PathVariable Long id) {
        Optional<Building> building = buildingService.getBuildingsBySiteId(id);
        return building.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}