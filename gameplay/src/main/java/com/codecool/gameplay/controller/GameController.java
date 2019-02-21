package com.codecool.gameplay.controller;

import com.codecool.gameplay.model.PlayerResponseData;
import com.codecool.gameplay.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class GameController {

    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    //TODO fix to correct path
    @GetMapping("/get-next-round/{gameId}")
    public Map<String, PlayerResponseData> nextRound(@PathVariable String gameId) {
        log.info("CALLEDDDDDDDDDD");
        Map<String, PlayerResponseData> response = service.handleRound(gameId);
        return response;
    }


    @PostMapping(value = "/creation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String gameCreation(@RequestBody MultiValueMap<String, String> gameData){
        log.info(gameData.get("username").get(0) + " is registering to the game with id: " + gameData.get("gameId").get(0));
        System.out.println(gameData.get("username").get(0));
        return "LOL";
    }
}
