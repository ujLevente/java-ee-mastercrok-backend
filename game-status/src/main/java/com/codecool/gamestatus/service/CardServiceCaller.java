package com.codecool.gamestatus.service;

import com.codecool.gamestatus.model.CardServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Queue;

@Service
@Slf4j
public class CardServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cardhandler.url}")
    private String baseUrl;

    public Queue<CardServiceResult> getPlayerDeck() {
        Queue<CardServiceResult> body = restTemplate.getForEntity(baseUrl + "/card/createDeck", Queue.class).getBody();
        log.info(body.toString());
        return body;
    }

}
