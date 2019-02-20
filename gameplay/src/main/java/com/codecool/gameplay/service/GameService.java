package com.codecool.gameplay.service;

import com.codecool.gameplay.model.PlayerResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GameService {

    private RestTemplate restTemplate;

    @Autowired
    public GameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, PlayerResponseData> handleRound(String gameId) {
        // TODO
        return new HashMap<>();
    }
}
