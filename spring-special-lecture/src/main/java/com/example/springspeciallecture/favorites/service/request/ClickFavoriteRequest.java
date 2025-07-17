package com.example.springspeciallecture.favorites.service.request;

import com.example.springspeciallecture.favorites.entity.TargetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClickFavoriteRequest {
    final private Long favoriteId;
    final private TargetType targetType;
    final private Long accountId;
}
