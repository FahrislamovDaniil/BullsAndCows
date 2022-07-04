package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.GameDto;
import com.practice.bullsandcows.dtos.PlayerDto;
import com.practice.bullsandcows.dtos.TurnDto;
import com.practice.bullsandcows.exceptions.GameNotFoundException;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Player;
import com.practice.bullsandcows.models.Turn;
import com.practice.bullsandcows.repos.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoMappingService {
    private final GameRepo gameRepo;

    @Autowired
    public DtoMappingService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public TurnDto mapTurnToDto(Turn turn) {
        TurnDto turnDto = new TurnDto();
        turnDto.setGame(turn.getGame().getId());
        turnDto.setGuess(turn.getGuess());
        turnDto.setTime(turn.getTime());
        turnDto.setBulls(turn.getBulls());
        turnDto.setCows(turn.getCows());
        return turnDto;
    }

    public Turn mapDtoToTurn(TurnDto turnDto) {
        Turn turn = new Turn();
        turn.setGame(gameRepo.findGameById(turnDto.getGame())
                .orElseThrow(() -> new GameNotFoundException(
                        "Game by id = " + turnDto.getGame() + " was not found")));
        turn.setGuess(turnDto.getGuess());
        turn.setTime(turnDto.getTime());
        turn.setBulls(turnDto.getBulls());
        turn.setCows(turnDto.getCows());
        return turn;
    }

    public GameDto mapGameToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setPlayer(game.getPlayer().getNickname());
        gameDto.setUnrestricted(game.isUnrestricted());
        gameDto.setTurnRestrict(game.getTurnRestrict());
        gameDto.setTimeRestrict(game.getTimeRestrict());
        return gameDto;
    }

    public PlayerDto mapPlayerToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setNickname(player.getNickname());
        return playerDto;
    }
}
