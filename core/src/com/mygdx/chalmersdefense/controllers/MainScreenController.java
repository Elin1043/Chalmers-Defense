package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.event.EventBus;
import com.mygdx.chalmersdefense.utilities.event.concreteEvents.ViewControllerEvents;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;


/**
 * @author Daniel Persson
 * @author Elin Forsberg
 * @author Joel Båtsman Hilmersson
 * @author Jenny Carlsson
 * <p>
 * Controller class for MainScreen
 */
public class MainScreenController extends InputAdapter {
    private final IControllModel model;     // Model reference
    private final EventBus viewEventBus;    // Eventbus to publish events to

    /**
     * Creates a controller for use by the MainScreen class
     *
     * @param model        the model to control
     * @param viewEventBus eventbus to add events to
     */
    public MainScreenController(IControllModel model, EventBus viewEventBus) {
        this.model = model;
        this.viewEventBus = viewEventBus;
    }

    /**
     * Listener for playButoon
     *
     * @param button the play button
     */
    public void addPlayButtonListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewEventBus.emit(new ViewControllerEvents(ViewControllerEvents.EventType.SHOWGAME_SCREEN));
            }
        });
    }

    /**
     * Listener for QuitButton
     *
     * @param button the quit button
     */
    public void addQuitButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Listener for settings button
     *
     * @param button the settings button
     */
    public void addSettingsButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.setShowOverlay(ScreenOverlayEnum.SETTINGS);
            }
        });
    }

    /**
     * Listener for info button
     *
     * @param button the info button
     */
    public void addInfoButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.setShowOverlay(ScreenOverlayEnum.INFO);
            }
        });
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case (Input.Keys.F11) -> {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(1920, 1080);
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                }
                return true;
            }
            default -> {
                return false;
            }
        }

    }


}
