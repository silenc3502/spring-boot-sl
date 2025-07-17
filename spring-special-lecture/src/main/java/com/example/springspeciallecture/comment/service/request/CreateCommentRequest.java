package com.example.springspeciallecture.comment.service.request;

import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {
    private final Long accountId;
    private final Long boardId;
    private final String content;
    private final Long parentId;

    public Comment toComment(Board board, AccountProfile accountProfile, Comment parent) {
        return new Comment(board, accountProfile, content, parent);
    }
}
