package com.practice.bullsandcows.repos;

import com.practice.bullsandcows.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    Optional<Game> findGameById(Long id);
}
