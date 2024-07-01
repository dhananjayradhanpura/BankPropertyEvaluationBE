package com.app.propertyValuatorBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.PropertyValuation;

@Repository
public interface PropertyValuationRepository extends JpaRepository<PropertyValuation, Long> {
}
