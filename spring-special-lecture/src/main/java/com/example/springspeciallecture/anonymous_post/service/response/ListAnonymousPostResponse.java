package com.example.springspeciallecture.anonymous_post.service.response;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListAnonymousPostResponse {

    private final List<AnonymousePostItem> posts;
    private final int currentPage;
    private final int perPage;
    private final long totalItems;
    private final long totalPages;

    public static ListAnonymousPostResponse from(
            List<AnonymousPost> posts,
            int page,
            int perPage,
            long totalItems
    ) {
        long totalPages = (long) Math.ceil((double) totalItems / perPage);

        List<AnonymousePostItem> postItems = posts.stream()
                .map(p -> new AnonymousePostItem(p.getId(), p.getTitle(), p.getContent()))
                .collect(Collectors.toList());

        return new ListAnonymousPostResponse(postItems, page, perPage, totalItems, totalPages);
    }
}

