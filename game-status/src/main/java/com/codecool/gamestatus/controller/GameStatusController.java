package com.codecool.gamestatus.controller;


import com.codecool.gamestatus.model.CardServiceResult;
import com.codecool.gamestatus.model.Game;
import com.codecool.gamestatus.model.Player;
import com.codecool.gamestatus.service.CardServiceCaller;
import com.codecool.gamestatus.service.GamesHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Queue;

@RestController
@CrossOrigin
public class GameStatusController {

    @Autowired
    private CardServiceCaller cardServiceCaller;

    @Autowired
    private GamesHandlerService gamesHandlerService;

//    "gameId"
//    "playerName"
//
//    @GetMapping("/create-game/{id}/{playerName}")
//    public void initGame(@PathVariable(value = "id") String gameId,
//                         @PathVariable(value = "playerName") String playerName) {
////        cardServiceCaller.getPlayerDeck();
//        Player player1 = new Player(playerName);
//        Game game = new Game(gameId);
//        game.setPlayerOne(player1);
//        Queue<CardServiceResult> p1Deck = cardServiceCaller.getPlayerDeck();
//        Queue<CardServiceResult> p2Deck = cardServiceCaller.getPlayerDeck();
//        game.setPlayerOneCardList(p1Deck);
//        game.setPlayerTwoCardList(p2Deck);
//        gamesHandlerService.getActiveGames().add(game);
//    }

    @RequestMapping(value = "/create-game", headers = "Accept=application/json")
    public void initGame(@RequestBody Map<String, String> data) {
        Player player1 = new Player(data.get("playerName"));
        Game game = new Game(data.get("gameId"));
        game.setPlayerOne(player1);
        Queue<CardServiceResult> p1Deck = cardServiceCaller.getPlayerDeck();
        Queue<CardServiceResult> p2Deck = cardServiceCaller.getPlayerDeck();
        game.setPlayerOneCardList(p1Deck);
        game.setPlayerTwoCardList(p2Deck);
        gamesHandlerService.getActiveGames().add(game);
    }

}
