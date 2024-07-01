package com.app.propertyValuatorBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {
	
	private String message;
	private Long referenceId;
	
	
}
