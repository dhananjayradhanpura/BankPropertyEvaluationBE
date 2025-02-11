package com.app.propertyValuatorBE.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.propertyValuatorBE.dto.BorrowerDto;
import com.app.propertyValuatorBE.service.BorrowerService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(com.app.propertyValuatorBE.apiManager.BorrowerApi.class)
public class BorrowerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private com.app.propertyValuatorBE.apiManager.BorrowerApi borrowerApi;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(borrowerApi).build();
    }

    @Test
    void testGetBorrowerData() throws Exception {
        BorrowerDto borrower1 = new BorrowerDto();  // Populate with sample data
        BorrowerDto borrower2 = new BorrowerDto();  // Populate with sample data
        List<BorrowerDto> borrowerList = Arrays.asList(borrower1, borrower2);

        Mockito.when(borrowerService.getBorrowerData(anyLong())).thenReturn(borrowerList);

        mockMvc.perform(get("/api/v1/borrower/borrower/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));  // Adjust the JSON as per the actual structure of BorrowerDto
    }
}