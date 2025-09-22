package com.example.springspeciallecture.anonymous_post.controller;

import com.example.springspeciallecture.anonymous_post.controller.request_form.CreateAnonymousPostRequestForm;
import com.example.springspeciallecture.anonymous_post.controller.request_form.ListAnonymousPostRequestForm;
import com.example.springspeciallecture.anonymous_post.controller.request_form.UpdateAnonymousPostRequestForm;
import com.example.springspeciallecture.anonymous_post.controller.response_form.CreateAnonymousPostResponseForm;
import com.example.springspeciallecture.anonymous_post.controller.response_form.ListAnonymousPostResponseForm;
import com.example.springspeciallecture.anonymous_post.controller.response_form.ReadAnonymousPostResponseForm;
import com.example.springspeciallecture.anonymous_post.controller.response_form.UpdateAnonymousPostResponseForm;
import com.example.springspeciallecture.anonymous_post.service.AnonymousPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anonymous-post")
public class AnonymousPostController {

    final private AnonymousPostService anonymousPostService;

    @GetMapping("/list")
    public Mono<ListAnonymousPostResponseForm> boardList(@ModelAttribute ListAnonymousPostRequestForm requestForm) {
        return anonymousPostService.list(requestForm.toListAnonymousPostRequest())
                .map(ListAnonymousPostResponseForm::from);
    }

    @PostMapping("/register")
    public Mono<CreateAnonymousPostResponseForm> registerPost(
            @RequestBody CreateAnonymousPostRequestForm requestForm
    ) {
        log.info("registerPost() -> {}", requestForm);

        return anonymousPostService.register(requestForm.toCreateAnonymousPostRequest())
                .map(CreateAnonymousPostResponseForm::from);
    }

    @GetMapping("/read/{postId}")
    public Mono<ReadAnonymousPostResponseForm> readPost(@PathVariable("postId") Long postId) {
        return anonymousPostService.read(postId)
                .map(ReadAnonymousPostResponseForm::from);
    }

    @PutMapping("/update/{postId}")
    public Mono<UpdateAnonymousPostResponseForm> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody UpdateAnonymousPostRequestForm requestForm
    ) {
        log.info("updatePost(): {}, postId: {}", requestForm, postId);

        return anonymousPostService.update(postId, requestForm.toUpdateAnonymousPostRequest())
                .map(UpdateAnonymousPostResponseForm::from);
    }

    @DeleteMapping("/delete/{postId}")
    public Mono<Void> deletePost(@PathVariable("postId") Long postId) {
        log.info("deletePost(): {}", postId);

        return anonymousPostService.delete(postId);
    }
}
