package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Game;

/**
 * @author Daniel Persson
 * A singleton class for mangaging the dirrefent screens.
 */
final public class ScreenManager {
    private AbstractScreen mainScreen;
    private AbstractScreen gameScreen;

    private AbstractScreen currentScreen;
    private ScreenEnum currentScreenEnum;

    private static ScreenManager instance;

    private Game game;

    private ScreenManager() {
        super();

    }

    /**
     * Returns this instance
     *
     * @return the only ScreenManager instance
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Initialize the different screens
     *
     * @param game       the game object to switch screen with
     * @param mainScreen mainScreen instance
     * @param gameScreen gameScreen instance
     */
    public void initialize(Game game, AbstractScreen mainScreen, AbstractScreen gameScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
    }

    /**
     * Shows the screen based on inputted ScreenEnum
     *
     * @param screenEnum which screen to switch to
     */
    public void showScreen(ScreenEnum screenEnum) {
        currentScreen = getScreen(screenEnum);
        currentScreenEnum = screenEnum;
        if (currentScreen != null) {
            game.setScreen(currentScreen);
        }
    }

    private AbstractScreen getScreen(ScreenEnum screenEnum) {
        return switch (screenEnum) {
            case MAIN_MENU -> mainScreen;
            case GAME -> gameScreen;
        };
    }

    /**
     * A getter for which screen is currently showing
     * @return current screen
     */
    public AbstractScreen getCurrentScreen() {
        return currentScreen;
    }

    /**
     * A getter for which screen enum is currently showing
     * @return an enum for current screen
     */
    public ScreenEnum getCurrentScreenEnum() {
        return currentScreenEnum;
    }
}