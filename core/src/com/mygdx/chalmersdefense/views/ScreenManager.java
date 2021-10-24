package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Game;
import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.model.event.IEventListener;
import com.mygdx.chalmersdefense.model.event.ModelEvents;
import com.mygdx.chalmersdefense.model.event.ViewEvents;

/**
 * @author Daniel Persson
 * A singleton class for mangaging the dirrefent screens.
 */
final public class ScreenManager implements IEventListener<ViewEvents> {
    private AbstractScreen mainScreen;
    private AbstractScreen gameScreen;

    private AbstractScreen currentScreen;
    private ScreenEnum currentScreenEnum;

    private static ScreenManager instance;

    private Game game;
    private EventBus eventbus;    // The eventbus to call when money should be added


    private ScreenManager() {

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
    public void initialize(Game game, AbstractScreen mainScreen, AbstractScreen gameScreen, EventBus eventbus) {
        this.game = game;
        this.mainScreen = mainScreen;
        this.gameScreen = gameScreen;
        this.eventbus = eventbus;
        this.eventbus.listenFor(ViewEvents.class, this);
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
            currentScreen.setBackgroundImage();
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

    @Override
    public void handle(ViewEvents event) {
        switch (event.getEventType()) {
            case SHOWMAIN_SCREEN -> showScreen(ScreenEnum.MAIN_MENU);
            case SHOWGAME_SCREEN -> showScreen(ScreenEnum.GAME);
        }
    }
}