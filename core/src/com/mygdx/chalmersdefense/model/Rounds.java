package com.mygdx.chalmersdefense.model;

/**
 *
 */
public class Rounds {
    private int currentRound = 0;
    private int winningRound;

    Rounds(int winningRound) {
        this.winningRound = winningRound;
    }

    void incrementToNextRound() {
        currentRound++;
    }

    public boolean gameWon() {
        return currentRound == (winningRound + 1);
    }

    public int getCurrentRound() {
        return Math.max(currentRound, 1);
    }

    public int getWinningRound() {
        return winningRound;
    }
}
