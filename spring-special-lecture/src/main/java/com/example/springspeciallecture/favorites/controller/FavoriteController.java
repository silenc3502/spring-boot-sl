package com.example.springspeciallecture.favorites.controller;

import com.example.springspeciallecture.favorites.controller.request_form.ClickFavoriteRequestForm;
import com.example.springspeciallecture.favorites.entity.Favorite;
import com.example.springspeciallecture.favorites.service.FavoriteService;
import com.example.springspeciallecture.favorites.service.request.ClickFavoriteRequest;
import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    final private FavoriteService favoriteService;
    final private RedisCacheService redisCacheService;

    @PostMapping("/click")
    public Boolean clickFavorite(@RequestHeader("Authorization") String authorizationHeader,
                                 @RequestBody ClickFavoriteRequestForm requestForm) {

        log.info("requestForm: {}", requestForm);

        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(token, Long.class);
        log.info("accountId -> {}", accountId);

        ClickFavoriteRequest request = requestForm.toClickFavoriteRequest(accountId);
        return favoriteService.clickFavorite(request);
    }
}
