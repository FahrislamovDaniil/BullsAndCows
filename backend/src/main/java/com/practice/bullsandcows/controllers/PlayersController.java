package com.practice.bullsandcows.controllers;

import com.practice.bullsandcows.dtos.PlayerDto;
import com.practice.bullsandcows.dtos.StatsDto;
import com.practice.bullsandcows.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayersController {
    private final PlayerService playerService;

    @Autowired
    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<PlayerDto> getPlayerByNickname(@PathVariable("nickname") String nickname) {
        PlayerDto playerDto = playerService.findPlayerByNickname(nickname);
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @GetMapping("/{nickname}/stats")
    public ResponseEntity<List<StatsDto>> getPlayerStats(@PathVariable("nickname") String nickname) {
        List<StatsDto> playerStats = playerService.createPlayerStats(nickname);
        return new ResponseEntity<>(playerStats, HttpStatus.OK);
    }

    @PostMapping("/new/{nickname}")
    public ResponseEntity<PlayerDto> addPlayer(@PathVariable("nickname") String nickname) {
        PlayerDto playerDto = playerService.addPlayer(nickname);
        return new ResponseEntity<>(playerDto, HttpStatus.CREATED);
    }
}
