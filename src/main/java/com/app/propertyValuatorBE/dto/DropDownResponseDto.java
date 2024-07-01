package com.app.propertyValuatorBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DropDownResponseDto {
    Map<String, List<String>> dropDownOptions;
}
