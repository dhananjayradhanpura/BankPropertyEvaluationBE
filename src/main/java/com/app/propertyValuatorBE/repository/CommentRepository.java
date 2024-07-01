package com.app.propertyValuatorBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.propertyValuatorBE.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	Optional<List<Comment>> findByUserId(Long userId);

}
