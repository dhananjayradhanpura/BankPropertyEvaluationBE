package com.app.propertyValuatorBE.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @Size(min = 3, max = 20)
    private String username;

    private String role;

    @Size(min = 6, max = 40)
    private String password;

    private String contactNumber;

    @Size(min = 3, max = 50)
    private String buisnessUnit;
}