package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.CommentsDto;

import java.util.List;

public interface CommentService {
    List<CommentsDto> findCommentsByUserId(String userId);
}
