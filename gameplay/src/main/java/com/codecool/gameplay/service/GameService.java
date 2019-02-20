package com.codecool.gameplay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GameService {

    private RestTemplate restTemplate;

    @Autowired
    public GameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
