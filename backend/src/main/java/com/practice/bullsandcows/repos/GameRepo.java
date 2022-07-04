package com.practice.bullsandcows.repos;

import com.practice.bullsandcows.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    @Query(value = "SELECT * FROM GAME WHERE ID = ?1", nativeQuery = true)
    Optional<Game> findGameById(Long id);
}
