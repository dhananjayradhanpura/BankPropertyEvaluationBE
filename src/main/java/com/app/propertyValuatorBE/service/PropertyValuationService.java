package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.db.entities.PropertyValuation;
import com.app.propertyValuatorBE.dto.PropertyValuationDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyValuationService {
    PropertyValuation createEvaluationApplication(PropertyValuationDto pvDto, MultipartFile file);

    @Transactional
    List<PropertyValuationDto> fetchApplication();

    PropertyValuationDto fetchApplicationById(String id);

    String updateEvaluationApplication(PropertyValuationDto pvDto);
}
