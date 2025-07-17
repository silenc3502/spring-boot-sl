package com.example.springspeciallecture.favorites.service;

import com.example.springspeciallecture.favorites.service.request.ClickFavoriteRequest;

public interface FavoriteService {
    Boolean clickFavorite(ClickFavoriteRequest request);
}
