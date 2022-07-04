package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.PlayerDto;
import com.practice.bullsandcows.dtos.StatsDto;
import com.practice.bullsandcows.exceptions.PlayerNotFoundException;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Player;
import com.practice.bullsandcows.models.Turn;
import com.practice.bullsandcows.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final DtoMappingService dtoMappingService;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, DtoMappingService dtoMappingService) {
        this.playerRepo = playerRepo;
        this.dtoMappingService = dtoMappingService;
    }

    public PlayerDto addPlayer(String nickname) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setGames(new ArrayList<Game>());
        return dtoMappingService.mapPlayerToDto(playerRepo.save(player));
    }

    public PlayerDto findPlayerByNickname(String nickname) {
        return dtoMappingService.mapPlayerToDto(playerRepo.findPlayerByNickname(nickname)
                .orElse(new Player()));
    }

    public List<StatsDto> createPlayerStats(String nickname) {
        Player player = playerRepo.findPlayerByNickname(nickname).orElseThrow(() -> new PlayerNotFoundException(
                "Player by nickname = " + nickname + " was not found"));
        return player.getGames().stream().filter(Game::isFinished)
                .map(this::createGameStats).collect(Collectors.toList());
    }

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
}
