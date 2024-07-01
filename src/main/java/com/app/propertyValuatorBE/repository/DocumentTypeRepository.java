package com.app.propertyValuatorBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findByName(String name);
}
