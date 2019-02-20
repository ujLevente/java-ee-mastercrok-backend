package com.codecool.gamestatus.model;

import lombok.Data;

import java.util.Queue;

@Data
public class Game {

    private String id;

    private Queue<CardServiceResult> playerOneCardList;
    private Queue<CardServiceResult> playerTwoCardList;

    private Player playerOne;
    private Player playerTwo;

    private int p1Score = 0;
    private int p2Score = 0;

    public Game(String id) {
        this.id = id;
    }
}
