package com.example.springspeciallecture.reactive_board.controller.response_form;

import com.example.springspeciallecture.reactive_board.service.response.ListReactiveBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListReactiveBoardResponseForm {
    private final List<Map<String, Object>> boardList;  // 게시글 리스트
    private final int totalItems;  // 전체 아이템 수
    private final int totalPages;  // 전체 페이지 수

    public static ListReactiveBoardResponseForm from(ListReactiveBoardResponse response) {
        return new ListReactiveBoardResponseForm(
                response.getBoardList(),
                (int) response.getTotalItems(),
                response.getTotalPages()
        );
    }
}
