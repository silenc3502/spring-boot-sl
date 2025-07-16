package com.example.springspeciallecture.comment.service;

import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListTopLevelCommentRequest;
import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;
import com.example.springspeciallecture.comment.service.response.ListTopLevelCommentResponse;

public interface CommentService {
    CreateCommentResponse register(CreateCommentRequest request);
    ListTopLevelCommentResponse getTopLevelComments(ListTopLevelCommentRequest request);
}
