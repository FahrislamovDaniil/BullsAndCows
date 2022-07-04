package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.TurnDto;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Turn;
import com.practice.bullsandcows.repos.TurnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {
    private final TurnRepo turnRepo;
    private final DtoMappingService dtoMappingService;

    @Autowired
    public TurnService(TurnRepo turnRepo, DtoMappingService dtoMappingService) {
        this.turnRepo = turnRepo;
        this.dtoMappingService = dtoMappingService;
    }

    public TurnDto addTurn(TurnDto turnDto) {
        Turn turn = dtoMappingService.mapDtoToTurn(turnDto);
        Game game = turn.getGame();
        return dtoMappingService.mapTurnToDto(turnRepo.save(nextTurn(turn, game.getAnswer())));
    }

    public Turn nextTurn(Turn turn, String answer) {
        String guess = turn.getGuess();
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < answer.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (answer.charAt(i) == guess.charAt(j)) {
                    if (i == j)
                        bulls++;
                    else
                        cows++;
                    break;
                }
            }
        }

        turn.setBulls(bulls);
        turn.setCows(cows);
        return turn;
    }
}