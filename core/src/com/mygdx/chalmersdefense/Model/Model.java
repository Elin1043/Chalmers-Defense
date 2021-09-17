package com.mygdx.chalmersdefense.Model;

import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Views.GameScreen;
import com.mygdx.chalmersdefense.Views.MainScreen;

public class Model {
    private MainScreen mainScreen;
    private GameScreen gameScreen;
    private ChalmersDefense game;
    private Rounds rounds;

    public Model(ChalmersDefense game, MainScreen mainScreen, GameScreen gameScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
        this.rounds = new Rounds(20);
    }

    public void playButtonAction() {
        // Do something
        game.setScreen(gameScreen);
    }

    public void startRoundButtonAction() {
        // Do something
        rounds.sendNextRound();
    }
}
