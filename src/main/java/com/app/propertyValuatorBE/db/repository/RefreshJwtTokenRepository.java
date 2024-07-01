package com.app.propertyValuatorBE.db.repository;

import com.app.propertyValuatorBE.db.entities.RefreshJwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshJwtTokenRepository extends JpaRepository<RefreshJwtToken, String> {
    Optional<RefreshJwtToken> findByJwtToken(String token);

    Optional<RefreshJwtToken> findByUserId(String userId);

    int deleteByUserId(String userId);
}
