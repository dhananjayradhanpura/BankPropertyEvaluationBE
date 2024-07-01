package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.JwtResponseDto;
import com.app.propertyValuatorBE.dto.JwtTokenRefreshRequestDto;
import com.app.propertyValuatorBE.dto.LoginRequestDto;
import com.app.propertyValuatorBE.dto.UserRequestDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public interface UserService {
    @Transactional
    String signup(@Valid UserRequestDto userRequestDTO);

    JwtResponseDto authenticateUser(LoginRequestDto loginRequest);

    void signout();

    JwtResponseDto refreshToken(@Valid JwtTokenRefreshRequestDto request);
}
