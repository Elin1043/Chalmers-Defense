package com.mygdx.chalmersdefense.Model;

import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Views.GameScreen;
import com.mygdx.chalmersdefense.Views.MainScreen;

public class Model {
    MainScreen mainScreen;
    GameScreen gameScreen;
    ChalmersDefense game;

    public Model(ChalmersDefense game, MainScreen mainScreen, GameScreen gameScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
    }

    public void playButtonAction() {
        // Do something
        game.setScreen(gameScreen);
    }

    public void startRoundButtonAction() {
        // Do something
    }
}
