package com.practice.bullsandcows.services;

import com.practice.bullsandcows.configs.GameRestrictConfig;
import com.practice.bullsandcows.dtos.GameDto;
import com.practice.bullsandcows.exceptions.GameNotFoundException;
import com.practice.bullsandcows.exceptions.PlayerNotFoundException;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Turn;
import com.practice.bullsandcows.repos.GameRepo;
import com.practice.bullsandcows.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private final GameRepo gameRepo;
    private final PlayerRepo playerRepo;
    private final GameRestrictConfig gameRestrictConfig;
    private final DtoMappingService dtoMappingService;

    @Autowired
    public GameService(GameRepo gameRepo, PlayerRepo playerRepo, GameRestrictConfig gameRestrictConfig,
                       DtoMappingService dtoMappingService) {
        this.gameRepo = gameRepo;
        this.playerRepo = playerRepo;
        this.gameRestrictConfig = gameRestrictConfig;
        this.dtoMappingService = dtoMappingService;
    }

    public GameDto addGame(String player) {
        Game game = new Game();
        game.setPlayer(playerRepo.findPlayerByNickname(player)
                .orElseThrow(() -> new PlayerNotFoundException(
                        "Player by nickname = " + player + " was not found")));
        return dtoMappingService.mapGameToDto(gameRepo.save(startNewGame(game)));
    }

    public GameDto finishGameById(Long id) {
        Game game = gameRepo.findGameById(id).orElseThrow(() -> new GameNotFoundException(
                "Game by id = " + id + " was not found"));
        game.setFinished(true);
        return dtoMappingService.mapGameToDto(gameRepo.save(game));
    }

    public Game startNewGame(Game game) {
        int seqLen = 4;
        StringBuilder answer = new StringBuilder();
        List<Integer> digits = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Random rnd = new Random();

        for (int i = 0; i < seqLen; i++) {
            answer.append(digits.remove(rnd.nextInt(digits.size())));
        }

        game.setAnswer(answer.toString());
        game.setUnrestricted(gameRestrictConfig.isUnrestricted());
        game.setTurnRestrict(gameRestrictConfig.getTurn());
        game.setTimeRestrict(gameRestrictConfig.getTime());
        game.setFinished(false);
        game.setTurns(new ArrayList<Turn>());
        return game;
    }
}