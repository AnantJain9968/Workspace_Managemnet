package com.st.workspace.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.workspace.management.entity.SiteSeatCapacity;
import com.st.workspace.management.repository.SiteSeatCapacityRepository;

import java.util.List;

@Service
public class SiteSeatCapacityService {
    @Autowired
    private SiteSeatCapacityRepository siteSeatCapacityRepository;

    public List<SiteSeatCapacity> getAllSiteSeatCapacities() {
        return siteSeatCapacityRepository.findAll();
    }

    public SiteSeatCapacity saveSiteSeatCapacity(SiteSeatCapacity siteSeatCapacity) {
        return siteSeatCapacityRepository.save(siteSeatCapacity);
    }

    public List<SiteSeatCapacity> saveAllSiteSeatCapacities(List<SiteSeatCapacity> siteSeatCapacities) {
        return siteSeatCapacityRepository.saveAll(siteSeatCapacities);
    }
}
