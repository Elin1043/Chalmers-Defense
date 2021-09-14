package com.mygdx.chalmersdefense.Model;

public class Rounds {
    private int currentRound;
    private int winningRound;

    public Rounds(int winningRound) {
        this.winningRound = winningRound;
        currentRound = 0;
    }

    protected void sendNextRound() {
        currentRound++;
        // call SpawnVirusClass
    }

    protected boolean gameWon() {
        return currentRound == (winningRound + 1);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getWinningRound() {
        return winningRound;
    }
}
