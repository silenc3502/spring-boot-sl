package com.example.springspeciallecture.anonymous_post.controller.response_form;

import com.example.springspeciallecture.anonymous_post.service.response.ListAnonymousPostResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListAnonymousPostResponseForm {

    private final List<AnonymousPostItemForm> posts;
    private final int currentPage;
    private final int perPage;
    private final long totalItems;
    private final long totalPages;

    public static ListAnonymousPostResponseForm from(ListAnonymousPostResponse response) {
        List<AnonymousPostItemForm> postForms = response.getPosts().stream()
                .map(p -> new AnonymousPostItemForm(p.getId(), p.getTitle(), p.getContent()))
                .collect(Collectors.toList());

        return new ListAnonymousPostResponseForm(
                postForms,
                response.getCurrentPage(),
                response.getPerPage(),
                response.getTotalItems(),
                response.getTotalPages()
        );
    }
}
