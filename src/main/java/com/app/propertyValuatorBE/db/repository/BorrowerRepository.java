package com.app.propertyValuatorBE.db.repository;

import com.app.propertyValuatorBE.db.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, String> {
    Optional<List<Borrower>> findByUserId(String userId);

    Optional<List<Borrower>> findByUserIdAndPropertyValuationId(String id, String id1);
}
