package com.mygdx.chalmersdefense.model;

public class Rounds {
    private int currentRound;
    private int winningRound;

    Rounds(int winningRound) {
        this.winningRound = winningRound;
        currentRound = 1;
    }

    void sendNextRound() {
        currentRound++;
        // call SpawnVirusClass
    }

    public boolean gameWon() {
        return currentRound == (winningRound + 1);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getWinningRound() {
        return winningRound;
    }
}
