package com.app.propertyValuatorBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.FacilityType;

@Repository
public interface FacilityTypeRepository extends JpaRepository<FacilityType, Long> {
    FacilityType findByName(String name);
}
