package com.codecool.gameplay.model;

public class GameResponse {

    private String attacker;
    private String gameId;

    private int leftSideWins = 0;
    private int rightSideWins = 0;

    public void incrementLeftWins() { leftSideWins++; }
    public void incrementRightWins() { rightSideWins++; }

    public String getGameId() { return gameId; }

    public void setAttacker(String attacker) { this.attacker = attacker; }
}
