package com.codecool.cardhandling.controller;

import com.codecool.cardhandling.model.Card;
import com.codecool.cardhandling.repository.CardRepository;
import com.codecool.cardhandling.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

@RestController
@Slf4j
public class WebController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    public WebController(FileStorageService fileStorageService, CardRepository cardRepository) {
        this.fileStorageService = fileStorageService;
        this.cardRepository = cardRepository;
    }

    @GetMapping(value = "/images/{fileName:.+}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        log.debug("Requested " + resource + " file");
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.error("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/card/createDeck")
    public Queue<Card> addCard() {
        List<Card> allCard = cardRepository.findAll();
        Random random = new Random();
        Queue<Card> deck = new LinkedList<>();
        while (deck.size() != 10) {
            Card card = allCard.get(random.nextInt(allCard.size()-1) + 1);
            if (!deck.contains(card)) {
                deck.add(card);
            }
        }
        log.info(deck.toString());
        return deck;
    }

}
