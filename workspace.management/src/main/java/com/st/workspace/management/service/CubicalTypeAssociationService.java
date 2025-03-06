package com.st.workspace.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.workspace.management.entity.CubicalTypeAssociation;
import com.st.workspace.management.repository.CubicalTypeAssociationRepository;

@Service
public class CubicalTypeAssociationService {

    @Autowired
    private CubicalTypeAssociationRepository cubicalTypeAssociationRepository;

    @Transactional
    public List<CubicalTypeAssociation> saveAll(List<CubicalTypeAssociation> associations) {
        return cubicalTypeAssociationRepository.saveAll(associations);
    }

    @Transactional(readOnly = true)
    public List<CubicalTypeAssociation> getAll() {
        return cubicalTypeAssociationRepository.findAll();
    }
}
