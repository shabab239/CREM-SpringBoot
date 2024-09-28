package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Unit;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public Optional<Unit> findUnitById(Long id) {
        return unitRepository.findById(id);
    }

    public List<Unit> findAllUnits() {
        return unitRepository.findAll();
    }

    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public void deleteUnitById(Long id) {
        unitRepository.deleteById(id);
    }
}
