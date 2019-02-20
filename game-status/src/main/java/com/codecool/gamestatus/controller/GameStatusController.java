package com.codecool.gamestatus.controller;


import com.codecool.gamestatus.service.CardServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GameStatusController {

    @Autowired
    private CardServiceCaller cardServiceCaller;

    @GetMapping("/create-game/{id}/{playerName}")
    public void initGame(@PathVariable(value = "id") String gameId,
                         @PathVariable(value = "playerName") String playerName) {
//        cardServiceCaller.getPlayerDeck();

    }

}
