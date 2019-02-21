package com.codecool.gamestatus.model;

import lombok.Data;

import java.util.Queue;

@Data
public class Game {

    private String id;

    private Queue<CardServiceResult> playerOneCardList;
    private Queue<CardServiceResult> playerTwoCardList;

    private CardServiceResult p1FirstCard;
    private CardServiceResult p2FirstCard;

    private Player playerOne;
    private Player playerTwo;

    private int p1Score = 0;
    private int p2Score = 0;

    private Player attacker;

    public Game(String id, Queue<CardServiceResult> p1Deck, Queue<CardServiceResult> p2Deck) {
        this.id = id;
        playerOneCardList = p1Deck;
        playerTwoCardList = p2Deck;

    }
}
