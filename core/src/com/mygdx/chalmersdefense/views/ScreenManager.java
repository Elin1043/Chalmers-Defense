package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Game;
import com.mygdx.chalmersdefense.utilities.event.EventBus;
import com.mygdx.chalmersdefense.utilities.event.IEventListener;
import com.mygdx.chalmersdefense.utilities.event.concreteEvents.ViewControllerEvents;

/**
 * @author Daniel Persson
 * A singleton class for mangaging the dirrefent screens.
 */
final public class ScreenManager implements IEventListener<ViewControllerEvents> {
    private AbstractScreen mainScreen; // The main screen of the game
    private AbstractScreen gameScreen; // The game screen in the game

    private static ScreenManager instance;

    private Game game;             // The current game
    private EventBus eventbus;    // The eventbus to call when money should be added


    private ScreenManager() {}

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
    public void initialize(Game game, AbstractScreen mainScreen, AbstractScreen gameScreen, EventBus eventbus) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
        this.eventbus = eventbus;
        this.eventbus.listenFor(ViewControllerEvents.class, this);
    }

    /**
     * Shows the screen based on inputted ScreenEnum
     *
     * @param screenEnum which screen to switch to
     */
    public void showScreen(ScreenEnum screenEnum) {
        AbstractScreen currentScreen = getScreen(screenEnum);
        if (currentScreen != null) {
            currentScreen.setBackgroundImage();
            game.setScreen(currentScreen);
        }
    }

    //Get the current screen
    private AbstractScreen getScreen(ScreenEnum screenEnum) {
        return switch (screenEnum) {
            case MAIN_MENU -> mainScreen;
            case GAME -> gameScreen;
        };
    }

    @Override
    public void handle(ViewControllerEvents event) {
        switch (event.getEventType()) {
            case SHOWMAIN_SCREEN -> showScreen(ScreenEnum.MAIN_MENU);
            case SHOWGAME_SCREEN -> showScreen(ScreenEnum.GAME);
        }
    }
}