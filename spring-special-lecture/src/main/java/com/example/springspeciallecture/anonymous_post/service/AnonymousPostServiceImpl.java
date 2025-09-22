package com.example.springspeciallecture.anonymous_post.service;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import com.example.springspeciallecture.anonymous_post.repository.AnonymousPostRepository;
import com.example.springspeciallecture.anonymous_post.service.request.CreateAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.request.ListAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.request.UpdateAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.response.CreateAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.ListAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.ReadAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.UpdateAnonymousPostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnonymousPostServiceImpl implements AnonymousPostService {

    private final AnonymousPostRepository reactivePostRepository;

    @Override
    public Mono<ListAnonymousPostResponse> list(ListAnonymousPostRequest request) {
        int offset = (request.getPage() - 1) * request.getSize();

        Mono<Long> totalCountMono = reactivePostRepository.countAll();

        return reactivePostRepository.findByPage(offset, request.getSize())
                .collectList()
                .zipWith(totalCountMono, (posts, totalCount) ->
                        ListAnonymousPostResponse.from(posts, request.getPage(), request.getSize(), totalCount)
                );
    }

    @Override
    public Mono<CreateAnonymousPostResponse> register(CreateAnonymousPostRequest request) {
        log.info("Creating anonymous post with title: {}", request.getTitle());

        AnonymousPost post = request.toAnonymousPost();

        return reactivePostRepository.save(post)
                .map(savedPost -> CreateAnonymousPostResponse.from(savedPost));
    }

    @Override
    public Mono<ReadAnonymousPostResponse> read(Long postId) {
        return reactivePostRepository.findById(postId)
                .map(ReadAnonymousPostResponse::from);
    }

    @Override
    public Mono<UpdateAnonymousPostResponse> update(Long postId, UpdateAnonymousPostRequest request) {
        return reactivePostRepository.findById(postId)
                .switchIfEmpty(Mono.error(new RuntimeException("Post not found")))
                .flatMap(post -> {
                    // 값 갱신
                    post.changeTitle(request.getTitle());
                    post.changeContent(request.getContent());

                    // 저장
                    return reactivePostRepository.save(post)
                            .map(UpdateAnonymousPostResponse::from);
                });
    }

    @Override
    public Mono<Void> delete(Long postId) {
        return reactivePostRepository.findById(postId)
                .switchIfEmpty(Mono.error(new RuntimeException("Post not found")))
                .flatMap(post -> reactivePostRepository.delete(post));
    }
}
