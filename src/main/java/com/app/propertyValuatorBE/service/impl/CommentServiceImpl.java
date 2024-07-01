package com.app.propertyValuatorBE.service.impl;

import com.app.propertyValuatorBE.db.entities.Comment;
import com.app.propertyValuatorBE.db.entities.User;
import com.app.propertyValuatorBE.db.repository.CommentRepository;
import com.app.propertyValuatorBE.db.repository.UserRepository;
import com.app.propertyValuatorBE.dto.CommentsDto;
import com.app.propertyValuatorBE.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentsDto> findCommentsByUserId(String userId) {
        Optional<User> optuser = userRepository.findById(userId);
        List<CommentsDto> commentDtoList = new ArrayList<>();
        optuser.ifPresent(user -> {
            Optional<List<Comment>> commentList = commentRepository.findByUserId(userId);
            commentList.ifPresent(comments -> {
                comments.forEach(comment -> {
                    CommentsDto commentDto = new CommentsDto();
                    BeanUtils.copyProperties(comment, commentDto);
                    commentDto.setCreatedDate(comment.getCreatedDate());
                    commentDto.setUsername(comment.getUser().getName());
                    commentDtoList.add(commentDto);
                });
            });
        });
        return commentDtoList;
    }




}