package com.st.workspace.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.workspace.management.entity.CubicalTypeAssociation;
import com.st.workspace.management.service.CubicalTypeAssociationService;

@RestController
@RequestMapping("/api/cubical-type-associations")
@CrossOrigin("*")
public class CubicalTypeAssociationController {

    @Autowired
    private CubicalTypeAssociationService cubicalTypeAssociationService;

    @PostMapping("/bulk")
    public ResponseEntity<List<CubicalTypeAssociation>> saveAll(@RequestBody List<CubicalTypeAssociation> associations) {
        List<CubicalTypeAssociation> savedAssociations = cubicalTypeAssociationService.saveAll(associations);
        return ResponseEntity.ok(savedAssociations);
    }

    @GetMapping
    public ResponseEntity<List<CubicalTypeAssociation>> getAll() {
        List<CubicalTypeAssociation> associations = cubicalTypeAssociationService.getAll();
        return ResponseEntity.ok(associations);
    }
}
