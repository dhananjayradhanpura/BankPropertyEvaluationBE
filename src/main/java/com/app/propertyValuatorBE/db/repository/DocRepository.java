package com.app.propertyValuatorBE.db.repository;

import com.app.propertyValuatorBE.db.entities.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocRepository extends JpaRepository<Doc, String> {
    Optional<List<Doc>> findByUserId(String userId);
}
