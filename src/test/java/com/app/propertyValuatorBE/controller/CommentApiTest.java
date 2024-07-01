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

import com.app.propertyValuatorBE.dto.CommentsDto;
import com.app.propertyValuatorBE.service.CommentService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(com.app.propertyValuatorBE.apiManager.CommentApi.class)
public class CommentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private com.app.propertyValuatorBE.apiManager.CommentApi commentApi;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentApi).build();
    }

    @Test
    void testGetCommentsByUserId() throws Exception {
        CommentsDto comment1 = new CommentsDto();  // Populate with sample data
        CommentsDto comment2 = new CommentsDto();  // Populate with sample data
        List<CommentsDto> commentList = Arrays.asList(comment1, comment2);

        Mockito.when(commentService.getcommentsByUserId(anyLong())).thenReturn(commentList);

        mockMvc.perform(get("/api/v1/comment/comments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));  // Adjust the JSON as per the actual structure of CommentsDto
    }
}