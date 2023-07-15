package com.sideproject.fikabackend.domain.game.repository;

import com.sideproject.fikabackend.domain.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
