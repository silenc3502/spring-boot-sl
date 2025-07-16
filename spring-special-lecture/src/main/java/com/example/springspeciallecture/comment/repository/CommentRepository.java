package com.example.springspeciallecture.comment.repository;

import com.example.springspeciallecture.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard_BoardIdOrderByCreateDateAsc(Long boardId);
}
