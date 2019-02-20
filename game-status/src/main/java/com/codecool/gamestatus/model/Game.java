package com.codecool.gamestatus.model;

import lombok.Data;

import java.util.Queue;

@Data
public class Game {

    private Queue<CardServiceResult> playerOneCardList;
    private Queue<CardServiceResult> playerTwoCardList;



}
