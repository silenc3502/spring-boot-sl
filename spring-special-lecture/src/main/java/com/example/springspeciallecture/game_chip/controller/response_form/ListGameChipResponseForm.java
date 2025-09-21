package com.example.springspeciallecture.game_chip.controller.response_form;

import com.example.springspeciallecture.game_chip.service.response.ListGameChipResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ListGameChipResponseForm {
    private final List<Map<String, Object>> gameChipList;
    private final int currentPage;
    private final int totalPages;
    private final long totalItems;

    public static ListGameChipResponseForm from(ListGameChipResponse response) {
        return new ListGameChipResponseForm(
                response.toMapList(),
                response.getCurrentPage(),
                response.getTotalPages(),
                response.getTotalItems()
        );
    }
}
