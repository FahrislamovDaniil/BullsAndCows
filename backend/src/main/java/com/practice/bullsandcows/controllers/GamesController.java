package com.practice.bullsandcows.controllers;

import com.practice.bullsandcows.dtos.GameDto;
import com.practice.bullsandcows.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin
public class GamesController {
    private final GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new/{player}")
    public ResponseEntity<GameDto> addGame(@PathVariable("player") String player) {
        GameDto gameDto = gameService.addGame(player);
        return new ResponseEntity<>(gameDto, HttpStatus.CREATED);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<GameDto> finishGameById(@PathVariable("id") Long id) {
        GameDto gameDto = gameService.finishGameById(id);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }
}
