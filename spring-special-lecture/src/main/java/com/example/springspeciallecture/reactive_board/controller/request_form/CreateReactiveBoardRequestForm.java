package com.example.springspeciallecture.reactive_board.controller.request_form;

import com.example.springspeciallecture.reactive_board.service.request.CreateReactiveBoardRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateReactiveBoardRequestForm {
    final private String title;
    final private String content;

    public CreateReactiveBoardRequest toCreateReactiveBoardRequest(Long accountId) {
        return new CreateReactiveBoardRequest(title, accountId, content);
    }
}
