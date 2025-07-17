package com.example.springspeciallecture.favorites.service;

import com.example.springspeciallecture.favorites.entity.Favorite;
import com.example.springspeciallecture.favorites.repository.FavoriteRepository;
import com.example.springspeciallecture.favorites.service.request.ClickFavoriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    final private FavoriteRepository favoriteRepository;

    @Override
    public Boolean clickFavorite(ClickFavoriteRequest request) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByAccountIdAndTargetTypeAndTargetId(
                request.getFavoriteId(), request.getTargetType(), request.getFavoriteId());

        if (existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
            return true;
        }

        Favorite newFavorite = new Favorite(
                request.getFavoriteId(),
                request.getTargetType(),
                request.getFavoriteId());

        favoriteRepository.save(newFavorite);
        return true;
    }
}
