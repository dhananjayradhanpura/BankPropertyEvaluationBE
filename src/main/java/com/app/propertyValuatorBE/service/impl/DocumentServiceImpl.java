package com.app.propertyValuatorBE.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import com.app.propertyValuatorBE.dto.DocumentDto;
import com.app.propertyValuatorBE.dto.DocumentTypeDto;
import com.app.propertyValuatorBE.entity.Document;
import com.app.propertyValuatorBE.entity.DocumentType;
import com.app.propertyValuatorBE.entity.User;
import com.app.propertyValuatorBE.repository.DocumentRepository;
import com.app.propertyValuatorBE.repository.DocumentTypeRepository;
import com.app.propertyValuatorBE.repository.UserRepository;
import com.app.propertyValuatorBE.service.DocumentService;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
    private DocumentTypeRepository documentTypeRepository;
	
	@Autowired
    private DocumentRepository documentRepository;

	@Autowired
	private UserRepository userRepository;
	
    @Override
    public List<DocumentTypeDto> getDocumentTypes() {
        List<DocumentTypeDto> documentTypeResponseList = new ArrayList<>();
        try {
            List<DocumentType> documentTypeList = documentTypeRepository.findAll();
            documentTypeList.forEach(documentType -> {
                DocumentTypeDto documentTypeResponse = new DocumentTypeDto();
                documentTypeResponse.setId(documentType.getId());
                documentTypeResponse.setName(documentType.getName());
                documentTypeResponseList.add(documentTypeResponse);
            });
        } catch (RuntimeException execption) {
        	log.info("RuntimeException cought : {}",execption.getMessage());
        } catch (Exception execption) {
        	log.info("Genric Exception cought : {}",execption.getMessage());
        }
        return documentTypeResponseList;
    }

	@Override
	public List<DocumentDto> getDocuments(Long userId) {
		Optional<User> optuser = userRepository.findById(userId);
		if (optuser.isPresent()) {
			List<DocumentDto> documentDtoList;
			Optional<List<Document>> borrowerList = documentRepository.findByUserId(userId);
			if (borrowerList.isPresent() && !CollectionUtils.isEmpty(borrowerList.get())) {
				documentDtoList = new ArrayList<>();
				borrowerList.get().forEach(doc -> {
					DocumentDto documentdto = new DocumentDto();
					BeanUtils.copyProperties(doc, documentdto);
					documentdto.setCreatedDate(doc.getCreatedDate());
					documentdto.setDocumentType(doc.getType().getName());
					documentdto.setUpdatedBy(doc.getUser().getName());
					documentdto.setFileName(doc.getFileName());
					documentdto.setFileSize(doc.getFileSize());
					documentDtoList.add(documentdto);
				});
			} else {
				documentDtoList = null;
			}

			return documentDtoList;
		}
		return null;
	}

}
