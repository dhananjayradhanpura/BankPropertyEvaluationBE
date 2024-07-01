package com.app.propertyValuatorBE.service.impl;

import com.app.propertyValuatorBE.db.entities.Doc;
import com.app.propertyValuatorBE.db.entities.User;
import com.app.propertyValuatorBE.db.repository.DocRepository;
import com.app.propertyValuatorBE.db.repository.UserRepository;
import com.app.propertyValuatorBE.dto.DocDto;
import com.app.propertyValuatorBE.service.DocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private DocRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DocDto> getDocuments(String userId) {
        Optional<User> optuser = userRepository.findById(userId);
        List<DocDto> documentDtoList = new ArrayList<>();
        optuser.ifPresent(user -> {
            Optional<List<Doc>> borrowerList = documentRepository.findByUserId(userId);
            borrowerList.ifPresent(borrowers -> {
                borrowers.forEach(doc -> {
                    DocDto documentdto = new DocDto();
                    BeanUtils.copyProperties(doc, documentdto);
                    documentdto.setCreatedDate(doc.getCreatedDate());
                    documentdto.setDocumentType(doc.getType());
                    documentdto.setUpdatedBy(doc.getUser().getName());
                    documentdto.setFileName(doc.getFileName());
                    documentdto.setFileSize(doc.getFileSize());
                    documentDtoList.add(documentdto);
                });
            });
        });
        return documentDtoList;
    }
}
