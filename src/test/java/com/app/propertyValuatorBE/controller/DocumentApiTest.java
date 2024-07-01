package com.app.propertyValuatorBE.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.propertyValuatorBE.dto.DocumentTypeDto;
import com.app.propertyValuatorBE.service.DocumentService;

@WebMvcTest(com.app.propertyValuatorBE.apiManager.DocumentApi.class)
public class DocumentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @InjectMocks
    private com.app.propertyValuatorBE.apiManager.DocumentApi documentApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDocumentTypes() throws Exception {
        DocumentTypeDto docType1 = new DocumentTypeDto(1L, "Type1");
        DocumentTypeDto docType2 = new DocumentTypeDto(2L, "Type2");
        List<DocumentTypeDto> documentTypes = Arrays.asList(docType1, docType2);

        when(documentService.getDocumentTypes()).thenReturn(documentTypes);

        mockMvc.perform(get("/api/v1/document/documenttypes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Type1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Type2"));
    }
}