package com.codecool.gamestatus.service;

import com.codecool.gamestatus.model.CardServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cardhandler.url}")
    private String baseUrl;

    public CardServiceResult getCard() {
        return restTemplate.getForEntity(baseUrl + "/card/create", CardServiceResult.class).getBody();
    }

}
