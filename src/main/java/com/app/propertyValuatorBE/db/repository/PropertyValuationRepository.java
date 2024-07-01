package com.app.propertyValuatorBE.db.repository;

import com.app.propertyValuatorBE.db.entities.PropertyValuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyValuationRepository extends JpaRepository<PropertyValuation, String> {
}
