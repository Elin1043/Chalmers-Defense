package com.mygdx.chalmersdefense.model;

/**
 *
 */
class Rounds {
    private int currentRound = 0;
    private int winningRound;

    Rounds(int winningRound) {
        this.winningRound = winningRound;
    }

    void incrementToNextRound() {
        currentRound++;
    }

    boolean gameWon() {
        return currentRound == (winningRound + 1);
    }

    int getCurrentRound() {
        return Math.max(currentRound, 1);
    }

    int getWinningRound() {
        return winningRound;
    }
}
