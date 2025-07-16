package com.example.springspeciallecture.comment.controller;

import com.example.springspeciallecture.comment.controller.request_form.CreateCommentRequestForm;
import com.example.springspeciallecture.comment.controller.request_form.ListChildCommentRequestForm;
import com.example.springspeciallecture.comment.controller.request_form.ListTopLevelCommentRequestForm;
import com.example.springspeciallecture.comment.controller.request_form.UpdateCommentRequestForm;
import com.example.springspeciallecture.comment.controller.response_form.CreateCommentResponseForm;
import com.example.springspeciallecture.comment.controller.response_form.ListChildCommentResponseForm;
import com.example.springspeciallecture.comment.controller.response_form.ListTopLevelCommentResponseForm;
import com.example.springspeciallecture.comment.controller.response_form.UpdateCommentResponseForm;
import com.example.springspeciallecture.comment.service.CommentService;
import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListChildCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListTopLevelCommentRequest;
import com.example.springspeciallecture.comment.service.request.UpdateCommentRequest;
import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;
import com.example.springspeciallecture.comment.service.response.ListChildCommentResponse;
import com.example.springspeciallecture.comment.service.response.ListTopLevelCommentResponse;
import com.example.springspeciallecture.comment.service.response.UpdateCommentResponse;
import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final RedisCacheService redisCacheService;

    @PostMapping("/register/{boardId}")
    public CreateCommentResponseForm registerComment(
            @PathVariable Long boardId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreateCommentRequestForm requestForm
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(token, Long.class);

        CreateCommentRequest request = requestForm.toCreateCommentRequest(accountId, boardId);
        CreateCommentResponse response = commentService.register(request);
        return CreateCommentResponseForm.from(response);
    }

    @GetMapping("/top-level-list")
    public ListTopLevelCommentResponseForm getTopLevelComments(@ModelAttribute ListTopLevelCommentRequestForm requestForm) {
        ListTopLevelCommentRequest request = requestForm.toListTopLevelCommentRequest();
        ListTopLevelCommentResponse response = commentService.getTopLevelComments(request);
        return ListTopLevelCommentResponseForm.from(response);
    }

    @GetMapping("/child-list")
    public ListChildCommentResponseForm getChildComments(@ModelAttribute ListChildCommentRequestForm requestForm) {
        ListChildCommentRequest request = requestForm.toListChildCommentRequest();
        ListChildCommentResponse response = commentService.getChildComments(request);
        return ListChildCommentResponseForm.from(response);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(token, Long.class);
        commentService.deleteComment(commentId, accountId);
    }

    @PutMapping("/update/{commentId}")
    public UpdateCommentResponseForm updateComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdateCommentRequestForm requestForm) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(token, Long.class);

        UpdateCommentRequest request = requestForm.toUpdateCommentRequest(commentId, accountId);
        UpdateCommentResponse response = commentService.updateComment(request);

        return UpdateCommentResponseForm.from(response);
    }
}
