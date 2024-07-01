package com.app.propertyValuatorBE.service;


import jakarta.validation.Valid;
import com.app.propertyValuatorBE.dto.JwtResponse;
import com.app.propertyValuatorBE.dto.LoginRequest;
import com.app.propertyValuatorBE.dto.TokenRefreshRequest;
import com.app.propertyValuatorBE.dto.UserRequestDTO;

/**
 * The Interface UserService.
 */
public interface UserService {

	Long registerUser(@Valid UserRequestDTO userRequestDTO);

	JwtResponse authenticateUser(@Valid LoginRequest loginRequest);

	void signout();

	JwtResponse refreshToken(@Valid TokenRefreshRequest request);

}
