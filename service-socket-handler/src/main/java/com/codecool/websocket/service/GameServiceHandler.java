package com.codecool.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class GameServiceHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${game.url}")
    private String gameUrl;

    public void createFirstUser(String gameId, String username){
        log.info("Calling: " + gameUrl + "/creat-game/{gameId}/{username}");

        ResponseEntity<String> response = restTemplate.getForEntity(gameUrl + "/create-game/" + gameId + "/" + username, String.class);

        System.out.println(response.getBody());

    }

    public String joinsecondUser(String gameId, String username){
        log.info("Calling: " + gameUrl + "/join-game/{gameId}/{username}");

        ResponseEntity<String> response = restTemplate.getForEntity(gameUrl + "/join-game/" + gameId + "/" + username, String.class);

        return response.getBody();
    }


    public String getNextRound(String gameId){
        log.info("Calling: " + gameUrl + "/get-next-round/{gameId}");

        ResponseEntity<String> response = restTemplate.getForEntity(gameUrl + "/get-next-round/" + gameId, String.class);

        return response.getBody();
    }
}
