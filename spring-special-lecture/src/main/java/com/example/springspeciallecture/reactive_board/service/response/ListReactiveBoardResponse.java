package com.example.springspeciallecture.reactive_board.service.response;

import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListReactiveBoardResponse {
    private final List<Map<String, Object>> boardList;
    private final long totalItems;
    private final int totalPages;

    public static ListReactiveBoardResponse from(List<ReactiveBoard> boards,
                                                 Map<Long, AccountProfile> profileMap,
                                                 int perPage) {
        List<Map<String, Object>> boardList = boards.stream()
                .map(board -> Map.<String, Object>of(
                        "boardId", board.getBoardId(),
                        "title", board.getTitle(),
                        "content", board.getContent(),
                        "nickname", profileMap.get(board.getWriterId()) != null
                                ? profileMap.get(board.getWriterId()).getNickname()
                                : "Unknown",
                        "createDate", formatDate(board.getCreateDate())
                ))
                .collect(Collectors.toList());

        long totalItems = boardList.size();
        int totalPages = (int) Math.ceil((double) totalItems / perPage);

        return new ListReactiveBoardResponse(boardList, totalItems, totalPages);
    }

    private static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}
