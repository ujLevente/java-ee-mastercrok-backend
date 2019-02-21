package com.codecool.gamestatus.controller;


import com.codecool.gamestatus.model.CardServiceResult;
import com.codecool.gamestatus.model.Game;
import com.codecool.gamestatus.model.Player;
import com.codecool.gamestatus.service.CardServiceCaller;
import com.codecool.gamestatus.service.GamesHandlerService;
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
    private GamesHandlerService gamesHandlerService;

//    "gameId"
//    "playerName"
//
    @GetMapping("/create-game/{id}/{playerName}")
    public void initGame(@PathVariable(value = "id") String gameId,
                         @PathVariable(value = "playerName") String playerName) {
//        cardServiceCaller.getPlayerDeck();
        Player player1 = new Player(playerName);
        Queue<CardServiceResult> p1Deck = cardServiceCaller.getPlayerDeck();
        Queue<CardServiceResult> p2Deck = cardServiceCaller.getPlayerDeck();
        Game game = new Game(gameId, p1Deck, p2Deck);
        game.setPlayerOne(player1);
        gamesHandlerService.getActiveGames().add(game);
    }

    @GetMapping("/join-game/{gameId}/{playerName}")
    public Game joinGame(@PathVariable(value = "gameId") String gameId,
                         @PathVariable(value = "playerName") String playerName) {
        Game game = gamesHandlerService.getGameById(gameId);
        Player player2 = new Player(playerName);
        if (game != null && game.getPlayerTwo() == null) {
            game.setPlayerTwo(player2);
        }
        return game;
    }


//    //TODO fix to correct path
    @GetMapping("/get-next-round/{gameId}")
    public Game nextRound(@PathVariable String gameId) {
        Game game = gamesHandlerService.getGameById(gameId);
        log.info(game.toString());

        return game;
    }


//    @PostMapping(value = "/creation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String gameCreation(@RequestBody MultiValueMap<String, String> gameData){
//        log.info(gameData.get("username").get(0) + " is registering to the game with id: " + gameData.get("gameId").get(0));
//        System.out.println(gameData.get("username").get(0));
//        return "LOL";
//    }

}
