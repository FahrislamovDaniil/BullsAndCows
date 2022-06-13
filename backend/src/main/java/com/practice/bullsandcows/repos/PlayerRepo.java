package com.practice.bullsandcows.repos;

import com.practice.bullsandcows.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    @Query(value = "SELECT * FROM PLAYER WHERE NICKNAME = ?1", nativeQuery = true)
    Optional<Player> findPlayerByNickname(String nickname);
}