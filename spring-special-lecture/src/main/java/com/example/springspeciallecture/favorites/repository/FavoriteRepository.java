package com.example.springspeciallecture.favorites.repository;

import com.example.springspeciallecture.favorites.entity.Favorite;
import com.example.springspeciallecture.favorites.entity.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByAccountIdAndTargetTypeAndTargetId(Long favoriteId, TargetType targetType, Long favoriteId1);
}
