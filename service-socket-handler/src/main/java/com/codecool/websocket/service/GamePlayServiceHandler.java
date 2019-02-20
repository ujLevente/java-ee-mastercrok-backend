package com.codecool.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GamePlayServiceHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gameplay.url}")
    private String gamePlayUrl;

    public void createFirstUser(String gameId, String username){
        Map<String, String> gameData = new HashMap<>();
        gameData.put("gameId", gameId);
        gameData.put("username", username);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(gameData);
        ResponseEntity<String> response = restTemplate.exchange(gamePlayUrl + "/creation", HttpMethod.POST, request, String.class);
    }


    public String joinsecondUser(String gameId, String username){
        Map<String, String> gameData = new HashMap<>();
        gameData.put("gameId", gameId);
        gameData.put("username", username);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(gameData);
        ResponseEntity<String> response = restTemplate.exchange(gamePlayUrl + "/get-next-round/" + gameId, HttpMethod.POST ,request,String.class);
        return response.getBody();
    }
}
