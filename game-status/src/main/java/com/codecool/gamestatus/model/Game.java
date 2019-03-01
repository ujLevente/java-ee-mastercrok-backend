package com.codecool.gamestatus.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Queue;

@Data
public class Game {

    private String id;

    private ArrayList<CardServiceResult> playerOneCardList;
    private ArrayList<CardServiceResult> playerTwoCardList;

    private CardServiceResult p1FirstCard;
    private CardServiceResult p2FirstCard;

    private Player playerOne;
    private Player playerTwo;

    private int p1Score = 0;
    private int p2Score = 0;

    private Player attacker;

    private Integer round = 0;

    public Game(String id, ArrayList<CardServiceResult> p1Deck, ArrayList<CardServiceResult> p2Deck) {
        this.id = id;
        playerOneCardList = p1Deck;
        playerTwoCardList = p2Deck;
    }

    public void setP1FirstCard(Integer round) {
        p1FirstCard = playerOneCardList.get(round);
    }

    public void setP2FirstCard(Integer round) {
        p2FirstCard = playerTwoCardList.get(round);
    }

}
