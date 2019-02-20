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
public class GamesHandlerService {

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
}
