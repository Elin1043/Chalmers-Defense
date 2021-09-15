package com.mygdx.chalmersdefense.Model;

public class Rounds {
    private int currentRound;
    private int winningRound;

    Rounds(int winningRound) {
        this.winningRound = winningRound;
        currentRound = 0;
    }

    void sendNextRound() {
        currentRound++;
        // call SpawnVirusClass
    }

    boolean gameWon() {
        return currentRound == (winningRound + 1);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getWinningRound() {
        return winningRound;
    }
}
