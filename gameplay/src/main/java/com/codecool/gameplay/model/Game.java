package com.codecool.gameplay.model;

import java.util.Queue;

public class Game {

    private String attacker;
    private String gameId;

    private String player1;
    private String player2;

    private Queue<Card> player1Deck;
    private Queue<Card> player2Deck;

    private int player1Wins = 0;
    private int player2Wins = 0;
}
