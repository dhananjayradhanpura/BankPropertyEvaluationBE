package com.app.propertyValuatorBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    private String accessToken = "Bearer";
    private String refreshToken;
    private String id;
    private String username;
    private String roleId;
    private String businessUnit;

    public JwtResponseDto(String accessToken, String refreshToken, String id, String username, String roleId) {
        super();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.roleId = roleId;
    }

}
