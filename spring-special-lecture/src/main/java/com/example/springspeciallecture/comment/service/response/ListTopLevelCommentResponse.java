package com.example.springspeciallecture.comment.service.response;

import com.example.springspeciallecture.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListTopLevelCommentResponse {
    private final List<Comment> commentList;
    private final int totalPages;
    private final long totalItems;

    public static ListTopLevelCommentResponse from(List<Comment> commentList, int totalPages, long totalItems) {
        return new ListTopLevelCommentResponse(commentList, totalPages, totalItems);
    }

    public List<Map<String, Object>> toMapList() {
        return commentList.stream().map(comment -> {
            Map<String, Object> map = new HashMap<>();
            map.put("commentId", comment.getId());
            map.put("writerNickname", comment.getWriter().getNickname());
            map.put("content", comment.getContent());
            map.put("updateDate", comment.getUpdateDate());
            return map;
        }).collect(Collectors.toList());
    }
}
