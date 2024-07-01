package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.DocumentDto;
import com.app.propertyValuatorBE.dto.DocumentTypeDto;

import java.util.List;


public interface DocumentService {

    List<DocumentTypeDto> getDocumentTypes();

	List<DocumentDto> getDocuments(Long userId);
}
