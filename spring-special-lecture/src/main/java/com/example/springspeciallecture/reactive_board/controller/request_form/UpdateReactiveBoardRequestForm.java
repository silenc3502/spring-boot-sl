package com.example.springspeciallecture.reactive_board.controller.request_form;

import com.example.springspeciallecture.reactive_board.service.request.UpdateReactiveBoardRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UpdateReactiveBoardRequestForm {
    final private String title;
    final private String content;

    public UpdateReactiveBoardRequest toUpdateReactiveBoardRequest() {
        return new UpdateReactiveBoardRequest(title, content);
    }
}
