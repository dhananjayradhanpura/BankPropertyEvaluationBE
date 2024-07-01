package com.app.propertyValuatorBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long>{

	Optional<List<Borrower>> findByUserId(Long userId);

	Optional<List<Borrower>> findByUserIdAndPropertyValuationId(Long id, Long id2);

}
