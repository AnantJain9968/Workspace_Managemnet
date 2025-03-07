package com.st.workspace.management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.workspace.management.entity.Floor;
import com.st.workspace.management.repository.FloorRepository;

@RestController
@RequestMapping("/api/floors")
@CrossOrigin("*")
public class FloorController {
    @Autowired
    private FloorRepository floorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Floor> getFloorById(@PathVariable Long id) {
        Optional<Floor> floor = floorRepository.findById(id);
        if (floor.isPresent()) {
            return ResponseEntity.ok(floor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
