package com.codecool.gameplay.controller;

import com.codecool.gameplay.model.GameResponse;
import com.codecool.gameplay.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GameController {

    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/get-next-round/{gameId}")
    public GameResponse nextRound(@PathVariable String gameId) {
        GameResponse response = service.handleRound(gameId);
        return response;
    }
}
