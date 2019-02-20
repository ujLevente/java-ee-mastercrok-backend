package com.codecool.gameplay.controller;

import com.codecool.gameplay.model.PlayerResponseData;
import com.codecool.gameplay.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class GameController {

    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/get-next-round/{gameId}")
    public Map<String, PlayerResponseData> nextRound(@PathVariable String gameId) {
        log.info("CALLEDDDDDDDDDD");
        Map<String, PlayerResponseData> response = service.handleRound(gameId);
        return response;
    }
}
