package com.app.propertyValuatorBE.service.impl;

import com.app.propertyValuatorBE.config.security.CustomUserDetails;
import com.app.propertyValuatorBE.config.security.JwtUtils;
import com.app.propertyValuatorBE.db.entities.RefreshJwtToken;
import com.app.propertyValuatorBE.db.entities.Role;
import com.app.propertyValuatorBE.db.entities.User;
import com.app.propertyValuatorBE.db.repository.RoleRepository;
import com.app.propertyValuatorBE.db.repository.UserRepository;
import com.app.propertyValuatorBE.dto.JwtResponseDto;
import com.app.propertyValuatorBE.dto.JwtTokenRefreshRequestDto;
import com.app.propertyValuatorBE.dto.LoginRequestDto;
import com.app.propertyValuatorBE.dto.UserRequestDto;
import com.app.propertyValuatorBE.enums.UserRole;
import com.app.propertyValuatorBE.service.RefreshJwtTokenService;
import com.app.propertyValuatorBE.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    /** The role repository. */
    @Autowired
    private RoleRepository roleRepository;

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** The jwt utils. */
    @Autowired
    private JwtUtils jwtUtils;

    /** The refresh token service. */
    @Autowired
    private RefreshJwtTokenService refreshTokenService;

    /** The encoder. */
    @Autowired
    PasswordEncoder encoder;

    /**
     * Register user.
     *
     * @param userRequestDTO the user request DTO
     * @return the string
     */
    @Override
    @Transactional
    public String signup(@Valid UserRequestDto userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new RuntimeException("Username is already taken!, Error Code: " + HttpStatus.CONFLICT);
        }
        // Create new user's account
        User user =  User.builder().username(userRequestDTO.getUsername()).password(encoder.encode(userRequestDTO.getPassword()))
                .contactNumber(userRequestDTO.getContactNumber())
                .buisnessUnit(userRequestDTO.getBuisnessUnit())
                .initiatorName(userRequestDTO.getUsername()).build();
        Set<Role> roles = new HashSet<>();

        if (Objects.isNull(userRequestDTO.getRole())) {
            Role userRole = roleRepository.findByName(UserRole.USER)
                    .orElseThrow(() -> new RuntimeException("Role not exist!, Error Code: " + HttpStatus.NOT_FOUND));
            roles.add(userRole);
        } else {
            if (userRequestDTO.getRole().equals(UserRole.ADMIN.name())) {
                Role adminRole = roleRepository.findByName(UserRole.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Role not exist, Error Code: " + HttpStatus.NOT_FOUND));
                roles.add(adminRole);
            } else if (userRequestDTO.getRole().equals(UserRole.USER.name())) {
                Role adminRole = roleRepository.findByName(UserRole.USER)
                        .orElseThrow(() -> new RuntimeException("Role not exist, Error Code: " + HttpStatus.NOT_FOUND));
                roles.add(adminRole);
            } else {
                throw new RuntimeException("Role not exist!, Error Code: " + HttpStatus.NOT_FOUND);
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * Authenticate user.
     *
     * @param loginRequest the login request
     * @return the jwt response
     */
    @Override
    public JwtResponseDto authenticateUser(@Valid LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        RefreshJwtToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        return new JwtResponseDto(jwt, refreshToken.getJwtToken(), userDetails.getId(), userDetails.getUsername(),
                userDetails.getRoleId(), optionalUser.get().getBuisnessUnit());
    }

    /**
     * Signout.
     */
    @Override
    public void signout() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);

    }

    @Override
    public JwtResponseDto refreshToken(@Valid JwtTokenRefreshRequestDto request) {
        return refreshTokenService.findByJwtToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshJwtToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    String roleId = user.getRoles().stream().findFirst().get().getId();
                    return new JwtResponseDto(token, request.getRefreshToken(), user.getId(), user.getUsername(), roleId);
                })
                .orElseThrow(() -> new RuntimeException(request.getRefreshToken() +
                        " JWT token is not in database!"));
    }

}
