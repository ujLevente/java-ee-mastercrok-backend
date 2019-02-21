package com.codecool.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class GamePlayServiceHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gameplay.url}")
    private String gamePlayUrl;

    public void createFirstUser(String gameId, String username){
        HttpEntity<MultiValueMap<String, String>> request = createRequestForGamePlay(gameId, username);

        log.info("Calling: " + gamePlayUrl + "/create-game");
        ResponseEntity<String> response = restTemplate.postForEntity(gamePlayUrl + "/create-game", request , String.class);
        System.out.println(response.getBody());

    }

    public String joinsecondUser(String gameId, String username){
        HttpEntity<MultiValueMap<String, String>> request = createRequestForGamePlay(gameId, username);

        log.info("Calling: " + gamePlayUrl + "/get-next-round/{gameId}");

        //TODO fix to correct path
        ResponseEntity<String> response = restTemplate.postForEntity(gamePlayUrl + "/get-next-round/" + gameId, request , String.class);
        return response.getBody();
    }


    public String getNextRound(String gameId){
        log.info("Calling: " + gamePlayUrl + "/get-next-round/{gameId}");
        String response = restTemplate.getForEntity(gamePlayUrl + "/get-next-round/" + gameId, String.class).getBody();
        return response;
    }

//    private HttpEntity<MultiValueMap<String, String>> createRequestForGamePlay(String gameId, String username) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> gameData = new LinkedMultiValueMap<>();
//        gameData.add("gameId", gameId);
//        gameData.add("username", username);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(gameData, headers);
//        return request;
//    }
}
