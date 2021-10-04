package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager {
    private MainScreen mainScreen;
    private GameScreen gameScreen;

    private static ScreenManager instance;

    private Game game;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game, MainScreen mainScreen, GameScreen gameScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
    }

    // Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum) {

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();

        // Show new screen
        AbstractScreen newScreen = getScreen(screenEnum);
        if (newScreen != null) {
            newScreen.buildStage();
            game.setScreen(newScreen);
        }
    }

    private AbstractScreen getScreen(ScreenEnum screenEnum) {
        switch (screenEnum) {
            case MAIN_MENU:
                return mainScreen;
            case GAME:
                return gameScreen;
            default:
                return null;
        }
    }
}