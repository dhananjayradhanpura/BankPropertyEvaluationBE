package com.app.propertyValuatorBE.apiManager;

import com.app.propertyValuatorBE.dto.JwtResponseDto;
import com.app.propertyValuatorBE.dto.JwtTokenRefreshRequestDto;
import com.app.propertyValuatorBE.dto.LoginRequestDto;
import com.app.propertyValuatorBE.dto.UserRequestDto;
import com.app.propertyValuatorBE.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    /** The user service. */
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRequestDto userRequestDTO) {
        log.info("AuthApi Signup method is called.");
        String userId = userService.signup(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully "+ userId);
    }

    /**
     * Authenticate user.
     * @param loginRequest the login request
     * @return the response entity
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        log.info("AuthController authenticateUser method is called.");
        log.debug("UserID : {}, Password : ",loginRequest.getUsername(),loginRequest.getPassword());
        JwtResponseDto jwtResponse = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * Logout user.
     * @return the response entity
     */
    @GetMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        userService.signout();
        return ResponseEntity.ok("SESSION ENDED");
    }

    /**
     * Refreshtoken.
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/refresh-jwt-token")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody JwtTokenRefreshRequestDto request) {
        return ResponseEntity.ok(userService.refreshToken(request));
    }
}