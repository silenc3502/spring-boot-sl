package com.example.springspeciallecture.comment.service;

import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;

public interface CommentService {
    CreateCommentResponse register(CreateCommentRequest request);
}
