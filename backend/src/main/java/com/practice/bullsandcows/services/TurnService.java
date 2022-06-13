package com.practice.bullsandcows.services;

import com.practice.bullsandcows.dtos.TurnDto;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Turn;
import com.practice.bullsandcows.repos.TurnRepo;
import com.practice.bullsandcows.utils.DtoMappingUtils;
import com.practice.bullsandcows.utils.GameMechanicsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {
    private final TurnRepo turnRepo;
    private final DtoMappingUtils dtoMappingUtils;
    private final GameMechanicsUtils gameMechanicsUtils;

    @Autowired
    public TurnService(TurnRepo turnRepo, DtoMappingUtils dtoMappingUtils, GameMechanicsUtils gameMechanicsUtils) {
        this.turnRepo = turnRepo;
        this.dtoMappingUtils = dtoMappingUtils;
        this.gameMechanicsUtils = gameMechanicsUtils;
    }

    public TurnDto addTurn(TurnDto turnDto) {
        Turn turn = dtoMappingUtils.mapDtoToTurn(turnDto);
        Game game = turn.getGame();
        return dtoMappingUtils.mapTurnToDto(turnRepo.save(gameMechanicsUtils.nextTurn(game, turn)));
    }
}
