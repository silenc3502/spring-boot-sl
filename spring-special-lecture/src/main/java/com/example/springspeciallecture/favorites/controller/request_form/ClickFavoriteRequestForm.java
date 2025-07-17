package com.example.springspeciallecture.favorites.controller.request_form;

import com.example.springspeciallecture.favorites.entity.TargetType;
import com.example.springspeciallecture.favorites.service.request.ClickFavoriteRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClickFavoriteRequestForm {
    final private Long favoriteId;
    final private TargetType targetType;

    public ClickFavoriteRequest toClickFavoriteRequest(Long accountId) {
        return new ClickFavoriteRequest(favoriteId, targetType, accountId);
    }
}
