package com.example.springspeciallecture.anonymous_post.controller.request_form;

import com.example.springspeciallecture.anonymous_post.service.request.ListAnonymousPostRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListAnonymousPostRequestForm {
    final private int page;
    final private int perPage;

    public ListAnonymousPostRequest toListAnonymousPostRequest() {
        return new ListAnonymousPostRequest(page, perPage);
    }
}
