package com.example.springspeciallecture.comment.controller.response_form;

import com.example.springspeciallecture.comment.service.response.ListChildCommentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ListChildCommentResponseForm {
    private final List<Map<String, Object>> commentList;
    private final int totalPages;
    private final long totalItems;

    public static ListChildCommentResponseForm from(ListChildCommentResponse response) {
        return new ListChildCommentResponseForm(
                response.toMapList(),
                response.getTotalPages(),
                response.getTotalItems()
        );
    }
}

