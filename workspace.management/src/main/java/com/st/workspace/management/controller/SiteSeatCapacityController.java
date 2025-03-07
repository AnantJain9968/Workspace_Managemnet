package com.st.workspace.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.workspace.management.entity.SiteSeatCapacity;
import com.st.workspace.management.service.SiteSeatCapacityService;

import java.util.List;

@RestController
@RequestMapping("/api/site-seat-capacity")
@CrossOrigin("*")
public class SiteSeatCapacityController {
    @Autowired
    private SiteSeatCapacityService siteSeatCapacityService;

    @GetMapping
    public ResponseEntity<List<SiteSeatCapacity>> getAllSiteSeatCapacities() {
        return ResponseEntity.ok(siteSeatCapacityService.getAllSiteSeatCapacities());
    }

    @PostMapping
    public ResponseEntity<SiteSeatCapacity> saveSiteSeatCapacity(@RequestBody SiteSeatCapacity siteSeatCapacity) {
        return ResponseEntity.ok(siteSeatCapacityService.saveSiteSeatCapacity(siteSeatCapacity));
    }

    @PostMapping("/bulkLoad")
    public ResponseEntity<List<SiteSeatCapacity>> saveAllSiteSeatCapacities(@RequestBody List<SiteSeatCapacity> siteSeatCapacities) {
    	List<SiteSeatCapacity> seats =siteSeatCapacityService.saveAllSiteSeatCapacities(siteSeatCapacities);
    	siteSeatCapacityService.populateBuildingData();    	
    	return ResponseEntity.ok(seats);
        
    }
    
    @PostMapping("/populate")
    public ResponseEntity<Void> populateBuildingData() {
        siteSeatCapacityService.populateBuildingData();
        return ResponseEntity.ok().build();
    }
}
