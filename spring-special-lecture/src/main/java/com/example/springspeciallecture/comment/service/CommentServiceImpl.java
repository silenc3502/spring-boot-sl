package com.example.springspeciallecture.comment.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.repository.AccountRepository;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.repository.BoardRepository;
import com.example.springspeciallecture.comment.entity.Comment;
import com.example.springspeciallecture.comment.repository.CommentRepository;
import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public CreateCommentResponse register(CreateCommentRequest request) {
        // 게시글 조회
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다. id=" + request.getBoardId()));

        // 계정 조회
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("계정이 존재하지 않습니다. id=" + request.getAccountId()));

        // 부모 댓글 (대댓글) 조회 (parentId가 0이면 null 처리)
        Comment parent = null;
        if (request.getParentId() != null && request.getParentId() > 0) {
            parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("부모 댓글이 존재하지 않습니다. id=" + request.getParentId()));
        }

        // 엔티티 생성 및 저장
        Comment comment = new Comment(board, account, request.getContent(), parent);
        Comment saved = commentRepository.save(comment);

        // 응답 DTO 생성
        return new CreateCommentResponse(saved.getId(), saved.getContent(), saved.getCreateDate());
    }
}
