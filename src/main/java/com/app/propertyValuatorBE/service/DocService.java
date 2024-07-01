package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.DocDto;

import java.util.List;

public interface DocService {
    List<DocDto> getDocuments(String userId);
}
