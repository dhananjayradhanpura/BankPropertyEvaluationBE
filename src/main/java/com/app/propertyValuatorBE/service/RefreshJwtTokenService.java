package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.db.entities.RefreshJwtToken;

import java.util.Optional;

public interface RefreshJwtTokenService {
    RefreshJwtToken verifyExpiration(RefreshJwtToken token);

    int deleteByUserId(String userId);

    RefreshJwtToken createRefreshToken(String id);

    Optional<RefreshJwtToken> findByJwtToken(String token);
}
