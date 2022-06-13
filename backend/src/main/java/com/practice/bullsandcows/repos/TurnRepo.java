package com.practice.bullsandcows.repos;

import com.practice.bullsandcows.models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepo extends JpaRepository<Turn, Long> {
}