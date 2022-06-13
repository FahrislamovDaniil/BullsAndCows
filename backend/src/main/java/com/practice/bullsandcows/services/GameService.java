package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.GameDto;
import com.practice.bullsandcows.exceptions.GameNotFoundException;
import com.practice.bullsandcows.exceptions.PlayerNotFoundException;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.repos.GameRepo;
import com.practice.bullsandcows.repos.PlayerRepo;
import com.practice.bullsandcows.utils.DtoMappingUtils;
import com.practice.bullsandcows.utils.GameMechanicsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepo gameRepo;
    private final PlayerRepo playerRepo;
    private final DtoMappingUtils dtoMappingUtils;
    private final GameMechanicsUtils gameMechanicsUtils;

    @Autowired
    public GameService(GameRepo gameRepo, PlayerRepo playerRepo,
                       DtoMappingUtils dtoMappingUtils, GameMechanicsUtils gameMechanicsUtils) {
        this.gameRepo = gameRepo;
        this.playerRepo = playerRepo;
        this.dtoMappingUtils = dtoMappingUtils;
        this.gameMechanicsUtils = gameMechanicsUtils;
    }

    public GameDto addGame(String player) {
        Game game = new Game();
        game.setPlayer(playerRepo.findPlayerByNickname(player)
                .orElseThrow(() -> new PlayerNotFoundException(
                        "Player by nickname = " + player + " was not found")));
        return dtoMappingUtils.mapGameToDto(gameRepo.save(gameMechanicsUtils.startNewGame(game)));
    }

    public GameDto finishGameById(Long id) {
        Game game = gameRepo.findGameById(id).orElseThrow(() -> new GameNotFoundException(
                "Game by id = " + id + " was not found"));
        game.setFinished(true);
        return dtoMappingUtils.mapGameToDto(gameRepo.save(game));
    }
}
