package com.codecool.gamestatus.controller;


import com.codecool.gamestatus.model.CardServiceResult;
import com.codecool.gamestatus.model.Game;
import com.codecool.gamestatus.model.Player;
import com.codecool.gamestatus.service.CardServiceCaller;
import com.codecool.gamestatus.service.GameHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@CrossOrigin
@Slf4j
public class GameStatusController {

    @Autowired
    private CardServiceCaller cardServiceCaller;

    @Autowired
    private GameHandlerService gameHandlerService;

    @GetMapping("/create-game/{id}/{playerName}")
    public void initGame(@PathVariable(value = "id") String gameId,
                         @PathVariable(value = "playerName") String playerName) {
        Player player1 = new Player(playerName);
        Queue<CardServiceResult> p1Deck = cardServiceCaller.getPlayerDeck();
        Queue<CardServiceResult> p2Deck = cardServiceCaller.getPlayerDeck();
        Game game = new Game(gameId, p1Deck, p2Deck);
        game.setPlayerOne(player1);
        game.setP1FirstCard();
        game.setP2FirstCard();
        gameHandlerService.getActiveGames().add(game);
        log.info("Game created with: " + gameId + " game id");
    }

    @GetMapping("/join-game/{gameId}/{playerName}")
    public Game joinGame(@PathVariable(value = "gameId") String gameId,
                         @PathVariable(value = "playerName") String playerName) {
        Game game = gameHandlerService.getGameById(gameId);
        Player player2 = new Player(playerName);
        if (game != null && game.getPlayerTwo() == null) {
            game.setPlayerTwo(player2);
        }
        log.info("Game id: " + game.getId() + " Player1: " + game.getPlayerOne() + " Player2: " + game.getPlayerTwo());
        return game;
    }


    //TODO fix to correct path
    @GetMapping("/get-next-round/{gameId}/{stat}")
    public Game nextRound(@PathVariable String gameId, @PathVariable String stat) {
        Game game = gameHandlerService.getGameById(gameId);
        gameHandlerService.playOneRound(game, stat);
        log.info(game.toString());
        return game;
    }


    @GetMapping("/current-round/{gameId}")
    public Game getCurrentRound(@PathVariable String gameId){
        Game game = gameHandlerService.getGameById(gameId);
        log.info("Current game is " + game);
        return game;
    }
}
