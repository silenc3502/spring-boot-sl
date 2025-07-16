package com.example.springspeciallecture.board.service.request;

import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateBoardRequest {
    final private String title;
    final private Long accountId;
    final private String content;

    public Board toBoard(AccountProfile writer) {
        return new Board(title, writer, content);
    }
}
