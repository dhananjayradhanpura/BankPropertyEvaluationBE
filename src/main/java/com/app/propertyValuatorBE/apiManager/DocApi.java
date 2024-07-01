package com.app.propertyValuatorBE.apiManager;

import java.util.List;

import com.app.propertyValuatorBE.dto.DocDto;
import com.app.propertyValuatorBE.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/document")
public class DocApi {

    @Autowired
    private DocService documentService;

    @GetMapping("/documents/{userId}")
    public ResponseEntity<List<DocDto>> getDocumentTypes(@PathVariable String userId) {
        return new ResponseEntity<>(documentService.getDocuments(userId), HttpStatus.OK);
    }
}