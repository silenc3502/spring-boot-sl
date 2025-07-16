package com.example.springspeciallecture.comment.repository;

import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByBoardAndParentIsNull(Board board, Pageable pageable);

    List<Comment> findByBoard_BoardIdAndParentIsNullOrderByCreateDateAsc(Long boardId);
    List<Comment> findByBoard_BoardIdOrderByCreateDateAsc(Long boardId);
}
