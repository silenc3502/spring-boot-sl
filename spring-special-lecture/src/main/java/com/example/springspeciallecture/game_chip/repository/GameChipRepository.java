package com.example.springspeciallecture.game_chip.repository;

import com.example.springspeciallecture.game_chip.entity.GameChip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameChipRepository extends JpaRepository<GameChip, Long> {
}
