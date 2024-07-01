package com.app.propertyValuatorBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

	Optional<List<Document>> findByUserId(Long userId);

	
}
