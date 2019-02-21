package com.codecool.websocket.controller;

import com.codecool.websocket.service.GameServiceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@Service
@Slf4j
public class MessageController {

    @Autowired
    private GameServiceHandler gameServiceHandler;

    @Autowired
    private SimpMessagingTemplate template;

    private Set<String> gameIds = new HashSet<>();

    /*
     * This MessageMapping annotated method will be handled by
     * SimpAnnotationMethodMessageHandler and after that the Message will be
     * forwarded to Broker channel to be forwarded to the client via WebSocket
     */
    @MessageMapping("/all")
    public void post(Map<String, String> gameData) {
        String nextRound = gameServiceHandler.getNextRound(gameData.get("gameId"));
        template.convertAndSend("/topic/" + gameData.get("gameId"), nextRound);
    }


    @RequestMapping("/create-game/{gameId}/{username}")
    public HttpStatus create(@PathVariable String gameId, @PathVariable String username) {
        log.info(username + " starting game with gameId: " + gameId);
        gameIds.add(gameId);
        gameServiceHandler.createFirstUser(gameId, username);
        return HttpStatus.OK;
    }

    @RequestMapping("/join-game/{gameId}/{username}")
    public Map<String, Boolean> joinGame(@PathVariable String gameId, @PathVariable String username) {
        log.info(username +" joining game on gameId = " + gameId);
        HashMap<String, Boolean> response = new HashMap<>();

        if (!gameIds.contains(gameId)) {
            log.info("failed to join game: game id not exists");
            response.put("status", false);
            return response;
        }

        response.put("status", true);
        String gameData = gameServiceHandler.joinsecondUser(gameId, username);
        template.convertAndSend("/topic/" + gameId, gameData);
        System.out.println(gameData);
        return response;
    }
}
