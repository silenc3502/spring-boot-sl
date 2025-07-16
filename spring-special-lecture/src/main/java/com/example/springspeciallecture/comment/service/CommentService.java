package com.example.springspeciallecture.comment.service;

import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListChildCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListTopLevelCommentRequest;
import com.example.springspeciallecture.comment.service.request.UpdateCommentRequest;
import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;
import com.example.springspeciallecture.comment.service.response.ListChildCommentResponse;
import com.example.springspeciallecture.comment.service.response.ListTopLevelCommentResponse;
import com.example.springspeciallecture.comment.service.response.UpdateCommentResponse;

public interface CommentService {
    CreateCommentResponse register(CreateCommentRequest request);
    ListTopLevelCommentResponse getTopLevelComments(ListTopLevelCommentRequest request);
    ListChildCommentResponse getChildComments(ListChildCommentRequest request);
    void deleteComment(Long commentId, Long accountId);
    UpdateCommentResponse updateComment(UpdateCommentRequest request);
}
