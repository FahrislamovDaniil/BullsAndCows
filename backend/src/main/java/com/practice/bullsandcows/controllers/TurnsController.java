package com.practice.bullsandcows.controllers;

import com.practice.bullsandcows.dtos.TurnDto;
import com.practice.bullsandcows.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turn")
@CrossOrigin
public class TurnsController {
    private final TurnService turnService;

    @Autowired
    public TurnsController(TurnService turnService) {
        this.turnService = turnService;
    }

    @PostMapping("/new")
    public ResponseEntity<TurnDto> addTurn(@RequestBody TurnDto turnDto) {
        TurnDto newTurnDto = turnService.addTurn(turnDto);
        return new ResponseEntity<>(newTurnDto, HttpStatus.CREATED);
    }
}
