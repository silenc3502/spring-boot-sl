package com.example.springspeciallecture.comment.controller.response_form;

import com.example.springspeciallecture.comment.service.response.ListTopLevelCommentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ListTopLevelCommentResponseForm {
    private final List<Map<String, Object>> commentList;
    private final int totalPages;
    private final long totalItems;

    public static ListTopLevelCommentResponseForm from(ListTopLevelCommentResponse response) {
        return new ListTopLevelCommentResponseForm(
                response.toMapList(),
                response.getTotalPages(),
                response.getTotalItems()
        );
    }
}
