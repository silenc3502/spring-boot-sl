package com.example.springspeciallecture.favorites.repository;

import com.example.springspeciallecture.favorites.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
