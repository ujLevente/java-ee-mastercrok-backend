package com.codecool.gamestatus.service;

import com.codecool.gamestatus.model.CardServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        ParameterizedTypeReference<Queue<CardServiceResult>> typeRef = new ParameterizedTypeReference<Queue<CardServiceResult>>() {
        };
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Queue<CardServiceResult>> responseEntity = restTemplate.exchange(baseUrl + "card/createDeck", HttpMethod.GET, new HttpEntity<>(headers), typeRef);
        Queue<CardServiceResult> body = responseEntity.getBody();
        log.info(body.toString());
        return body;
    }

}
