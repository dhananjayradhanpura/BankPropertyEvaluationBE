package com.app.propertyValuatorBE.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.propertyValuatorBE.dto.CommentsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.propertyValuatorBE.entity.Comment;
import com.app.propertyValuatorBE.entity.User;
import com.app.propertyValuatorBE.repository.CommentRepository;
import com.app.propertyValuatorBE.repository.UserRepository;
import com.app.propertyValuatorBE.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<CommentsDto> getcommentsByUserId(Long userId) {
		Optional<User> optuser = userRepository.findById(userId);
		if (optuser.isPresent()) {
			List<CommentsDto> commentDtoList;
			Optional<List<Comment>> commentList = commentRepository.findByUserId(userId);
			if (commentList.isPresent() && !CollectionUtils.isEmpty(commentList.get())) {
				commentDtoList = new ArrayList<>();
				commentList.get().forEach(comment -> {
					CommentsDto commentDto = new CommentsDto();
					BeanUtils.copyProperties(comment, commentDto);
					commentDto.setCreatedDate(comment.getCreatedDate());
					commentDto.setUserName(comment.getUser().getName());
					commentDtoList.add(commentDto);
				});
			} else {
				commentDtoList = null;
			}

			return commentDtoList;
		}
		return null;
	}
	
	

	
}
