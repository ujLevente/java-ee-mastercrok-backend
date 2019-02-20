package com.codecool.websocket.controller;

import java.util.*;

import com.codecool.websocket.repository.ChatHistoryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@Slf4j
public class MessageController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gameplay.url}")
    private String gamePlayUrl;

    @Autowired
    private ChatHistoryDao chatHistoryDao;

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


        template.convertAndSend("/topic/" + gameData.get("gameId"), "working");
    }


    @RequestMapping("/create-game/{gameId}/{username}")
    public HttpStatus create(@PathVariable String gameId, @PathVariable String username) {
        log.info("starting game with gameId: " + gameId);
        gameIds.add(gameId);
        Map<String, String> gameData = new HashMap<>();
        gameData.put("gameId", gameId);
        gameData.put("username", username);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, String>> request = new HttpEntity<>(gameData);
        ResponseEntity<String> response = restTemplate.exchange(gamePlayUrl + "/creation", HttpMethod.POST, request, String.class);
        return HttpStatus.OK;
    }

    @RequestMapping("/join-game/{gameId}")
    public Map<String, Boolean> joinGame(@PathVariable String gameId) {
        log.info("joining game on gameId = " + gameId);
        HashMap<String, Boolean> response = new HashMap<>();

        if (!gameIds.contains(gameId)) {
            log.info("failed to join game: game id not exists");
            response.put("status", false);
            return response;
        }

        response.put("status", true);
        // TODO ask data for game, snad data to specific websocket
        String gameData = restTemplate.getForEntity(gamePlayUrl + "/get-next-round/" + gameId, String.class).getBody();
        template.convertAndSend("/topic/" + gameId, gameData);
        return response;
    }
}
