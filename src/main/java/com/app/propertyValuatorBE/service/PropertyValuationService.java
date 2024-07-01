package com.app.propertyValuatorBE.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.propertyValuatorBE.dto.PropertyValuationDto;
import com.app.propertyValuatorBE.entity.PropertyValuation;

public interface PropertyValuationService {
    
	PropertyValuation createEvaluationApplication(PropertyValuationDto pvAppDto, MultipartFile file);
   
	String updateEvaluationApplication(PropertyValuationDto pvAppDto);
    
	List<PropertyValuationDto> fetchApplication();

    PropertyValuationDto fetchApplicationById(Long id);
}
