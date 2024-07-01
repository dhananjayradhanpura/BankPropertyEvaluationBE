package com.app.propertyValuatorBE.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.app.propertyValuatorBE.entity.RefreshToken;
import com.app.propertyValuatorBE.entity.User;

/**
 * The Interface RefreshTokenRepository.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	Optional<RefreshToken> findByToken(String token);

	Optional<RefreshToken> findByUserId(Long userId);
	
	@Modifying
	int deleteByUser(User user);
}
