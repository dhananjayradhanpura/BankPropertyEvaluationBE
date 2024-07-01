package com.app.propertyValuatorBE.apiManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.propertyValuatorBE.dto.CommentsDto;
import com.app.propertyValuatorBE.service.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentApi {

	@Autowired
	private CommentService commentService;
	
    @GetMapping("/comments/{userId}")
    public ResponseEntity<List<CommentsDto>> getcommentsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(commentService.getcommentsByUserId(userId), HttpStatus.OK);
    }
}
