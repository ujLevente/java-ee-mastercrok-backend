package com.codecool.gameplay.model;

import lombok.Data;

@Data
public class PlayerResponseData {

    private String playerName;
    private boolean isAttacker = false;
    private Card card;
    private int wins = 0;
}
