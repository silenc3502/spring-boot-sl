package com.example.springspeciallecture.reactive_board.controller.request_form;

import com.example.springspeciallecture.reactive_board.service.request.ListReactiveBoardRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListReactiveBoardRequestForm {
    final private int page;
    final private int perPage;

    public ListReactiveBoardRequest toListReactiveBoardRequest() {
        return new ListReactiveBoardRequest(page, perPage);
    }
}
