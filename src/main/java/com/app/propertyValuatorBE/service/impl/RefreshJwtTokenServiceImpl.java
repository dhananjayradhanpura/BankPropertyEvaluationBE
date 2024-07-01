package com.app.propertyValuatorBE.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.app.propertyValuatorBE.db.entities.RefreshJwtToken;
import com.app.propertyValuatorBE.db.repository.RefreshJwtTokenRepository;
import com.app.propertyValuatorBE.db.repository.UserRepository;
import com.app.propertyValuatorBE.service.RefreshJwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class RefreshTokenService.
 */
@Service
public class RefreshJwtTokenServiceImpl implements RefreshJwtTokenService {

    /** The refresh token duration in ms. */
    @Value("${propertyValuation.refreshTokenTime}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshJwtTokenRepository refreshJwtTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<RefreshJwtToken> findByJwtToken(String token) {
        return refreshJwtTokenRepository.findByJwtToken(token);
    }

    @Override
    public RefreshJwtToken createRefreshToken(String userId) {
        RefreshJwtToken refreshToken = new RefreshJwtToken();
        Optional<RefreshJwtToken> refreshTokenoption = refreshJwtTokenRepository.findByUserId(userId);
        if (refreshTokenoption.isPresent()) {
            refreshToken = refreshTokenoption.get();
            refreshToken.setId(refreshTokenoption.get().getId());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setJwtToken(UUID.randomUUID().toString());
            refreshToken = refreshJwtTokenRepository.save(refreshToken);
        } else {
            refreshToken.setUser(userRepository.findById(userId).get());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setJwtToken(UUID.randomUUID().toString());
            refreshToken = refreshJwtTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    @Override
    public RefreshJwtToken verifyExpiration(RefreshJwtToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshJwtTokenRepository.delete(token);
            throw new RuntimeException(token.getJwtToken() +
                    " JWT token was expired. Please make a new signin request");
        }
        return token;
    }

    @Override
    @Transactional
    public int deleteByUserId(String userId) {
        return refreshJwtTokenRepository.deleteByUserId(userId);
    }
}