package com.app.propertyValuatorBE.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.app.propertyValuatorBE.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import mu.mcb.property.evalution.constants.UserRole;
import com.app.propertyValuatorBE.dto.JwtResponse;
import com.app.propertyValuatorBE.dto.LoginRequest;
import com.app.propertyValuatorBE.dto.TokenRefreshRequest;
import com.app.propertyValuatorBE.dto.UserRequestDTO;
import com.app.propertyValuatorBE.entity.RefreshToken;
import com.app.propertyValuatorBE.entity.Role;
import com.app.propertyValuatorBE.entity.User;
import com.app.propertyValuatorBE.repository.RoleRepository;
import com.app.propertyValuatorBE.repository.UserRepository;
import com.app.propertyValuatorBE.config.JwtUtils;
import com.app.propertyValuatorBE.config.RefreshTokenService;

public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtUtils jwtUtils;

	@Mock
	private RefreshTokenService refreshTokenService;

	@Mock
	private PasswordEncoder encoder;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegisterUser() {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUsername("test");
		dto.setPassword("password");
		dto.setContactNumber("1234567890");
		dto.setBuisnessUnit("Business Unit");
		dto.setRole(UserRole.USER.name());

		when(userRepository.existsByUsername(dto.getUsername())).thenReturn(false);
		when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role()));

		Long userId = userService.registerUser(dto);

		assertNotNull(userId);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void testAuthenticateUser() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("test");
		loginRequest.setPassword("password");

		Authentication authentication = mock(Authentication.class);
		when(authenticationManager.authenticate(any())).thenReturn(authentication);

		JwtResponse response = userService.authenticateUser(loginRequest);

		assertNotNull(response);
		// Verify other interactions as needed
	}

	@Test
	void testSignout() {
		userService.signout();
		// Verify other interactions as needed
	}

	@Test
	void testRefreshToken() {
		TokenRefreshRequest request = new TokenRefreshRequest();
		request.setRefreshToken("refreshToken");

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken("refreshToken");

		when(refreshTokenService.findByToken("refreshToken")).thenReturn(Optional.of(refreshToken));
		when(refreshTokenService.verifyExpiration(refreshToken)).thenReturn(refreshToken);
		when(jwtUtils.generateTokenFromUsername(anyString())).thenReturn("token");

		JwtResponse response = userService.refreshToken(request);

		assertNotNull(response);
		// Verify other interactions as needed
	}
}
