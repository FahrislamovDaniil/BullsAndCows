package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.PlayerDto;
import com.practice.bullsandcows.dtos.StatsDto;
import com.practice.bullsandcows.exceptions.PlayerNotFoundException;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Player;
import com.practice.bullsandcows.repos.PlayerRepo;
import com.practice.bullsandcows.utils.DtoMappingUtils;
import com.practice.bullsandcows.utils.StatsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final DtoMappingUtils dtoMappingUtils;
    private final StatsUtils statsUtils;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, DtoMappingUtils dtoMappingUtils, StatsUtils statsUtils) {
        this.playerRepo = playerRepo;
        this.dtoMappingUtils = dtoMappingUtils;
        this.statsUtils = statsUtils;
    }

    public PlayerDto addPlayer(String nickname) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setGames(new ArrayList<Game>());
        return dtoMappingUtils.mapPlayerToDto(playerRepo.save(player));
    }

    public PlayerDto findPlayerByNickname(String nickname) {
        return dtoMappingUtils.mapPlayerToDto(playerRepo.findPlayerByNickname(nickname)
                .orElse(new Player()));
    }

    public List<StatsDto> createPlayerStats(String nickname) {
        Player player = playerRepo.findPlayerByNickname(nickname).orElseThrow(() -> new PlayerNotFoundException(
                "Player by nickname = " + nickname + " was not found"));
        return statsUtils.createPlayerStats(player);
    }
}
