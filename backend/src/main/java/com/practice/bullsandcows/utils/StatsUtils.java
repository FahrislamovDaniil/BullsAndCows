package com.practice.bullsandcows.utils;

import com.practice.bullsandcows.dtos.StatsDto;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Player;
import com.practice.bullsandcows.models.Turn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsUtils {

    public StatsDto createGameStats(Game game) {
        StatsDto statsDto = new StatsDto();
        statsDto.setGameId(game.getId());
        int turns = game.getTurns().size();
        statsDto.setTurns(turns);
        if(turns > 0) {
            Turn lastTurn = game.getTurns().get(turns - 1);

            if (game.isUnrestricted()) {
                statsDto.setTime(lastTurn.getTime());
            } else {
                if (lastTurn.getBulls() == 4 || game.getTurnRestrict() == turns) {
                    statsDto.setTime(lastTurn.getTime());
                } else {
                    statsDto.setTime(game.getTimeRestrict());
                }
            }
        } else {
            statsDto.setTime(game.getTimeRestrict());
        }
        return statsDto;
    }

    public List<StatsDto> createPlayerStats(Player player) {
        return player.getGames().stream().filter(Game::isFinished)
                .map(this::createGameStats).collect(Collectors.toList());
    }
}