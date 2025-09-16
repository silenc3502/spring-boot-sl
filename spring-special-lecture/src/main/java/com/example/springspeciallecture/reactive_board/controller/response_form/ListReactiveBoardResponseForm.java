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

    // from 메서드를 추가하여 ListBoardResponseForm을 생성
    public static ListReactiveBoardResponseForm from(List<ListReactiveBoardResponse> reactiveBoardListResponses, int totalItems, int totalPages) {
        // 모든 ListBoardResponse 객체의 boardListWithNicknames 값을 하나로 결합
        List<Map<String, Object>> combinedReactiveBoardList = reactiveBoardListResponses.stream() // [0]
                .flatMap(response -> response.getReactiveBoardListWithNicknames().stream())  // 각 ListBoardResponse의 getBoardListWithNicknames 호출
                .collect(Collectors.toList());

        return new ListReactiveBoardResponseForm(combinedReactiveBoardList, totalItems, totalPages);
    }
}
