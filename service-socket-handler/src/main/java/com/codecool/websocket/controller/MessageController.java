package com.codecool.websocket.controller;

import java.util.*;

import com.codecool.websocket.repository.ChatHistoryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class MessageController {

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
    public void post(@Payload Map<String, String> message) {
        message.put("timestamp", Long.toString(System.currentTimeMillis()));
        System.out.println(message);
        chatHistoryDao.save(message);
        template.convertAndSend("/topic/"+message.get("authorId"), message);
    }


    @RequestMapping("/create-game/{gameId}")
    public HttpStatus create(@PathVariable String gameId) {
        log.info("starting game with gameId: " + gameId);
        gameIds.add(gameId);
        return HttpStatus.OK;
    }

    @RequestMapping("/join-game/{gameId}")
    public HttpStatus joinGame(@PathVariable String gameId) {
        log.info("joining game on gameId = " + gameId);

        if (!gameIds.contains(gameId)) {
            log.info("failed to join game: game id not exists");
            return HttpStatus.NOT_FOUND;
        }

        // TODO ask data for game, snad data to specific websocket
        template.convertAndSend("/topic/" + gameId, "TODO data to start game");
        return HttpStatus.OK;
    }
}
