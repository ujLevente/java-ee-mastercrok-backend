package com.codecool.gamestatus.service;


import com.codecool.gamestatus.model.Game;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Slf4j
public class GameHandlerService {

    private List<Game> activeGames = new ArrayList<>();


    public Game getGameById(String id) {
        for (Game game : activeGames) {
            if (game.getId().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public void removeGameById(String id) {
        for (Game game : activeGames) {
            if (game.getId().equals(id)) {
                activeGames.remove(game);
            }
        }
    }

    public void playOneRound(Game game, String attack) {
        int p1Stat = 0;
        int p2Stat = 0;
        switch (attack) {
            case "power":
                p1Stat = game.getP1FirstCard().getPower();
                p2Stat = game.getP2FirstCard().getPower();
                break;
            case "intelligence":
                p1Stat = game.getP1FirstCard().getIntelligence();
                p2Stat = game.getP2FirstCard().getIntelligence();
                break;
            case "reflex":
                p1Stat = game.getP1FirstCard().getReflex();
                p2Stat = game.getP2FirstCard().getReflex();
                break;
        }
        if (p1Stat > p2Stat) {
            game.setP1Score(game.getP1Score() + 1);
            game.setAttacker(game.getPlayerOne());
        } else {
            game.setP2Score(game.getP2Score() + 1);
            game.setAttacker(game.getPlayerTwo());
        }
    }
}
