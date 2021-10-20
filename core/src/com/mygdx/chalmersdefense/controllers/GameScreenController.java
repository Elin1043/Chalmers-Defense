package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 * @author Daniel Persson
 * Controller class for GameScreen (excluded panels)
 *
 * 2021-09-22 Created by Daniel Persson: A class for handling listener setup for GameScreen. <br>
 * 2021-10-03 Modified by Daniel Persson: Added click listener for main menu and try again buttons. <br>
 * 2021-10-04 Modified by Joel Båtsman Hilmersson: Changed to use IControllModel interface instead of Model <br>
 * 2021-10-05 Modified by Daniel Persson: Added click listener for continue button in WinPanelOverlay <br>
 * 2021-10-11 Modified by Jenny Carlsson and Daniel Persson: added click listener for pause meny buttons <br>
 * 2021-10-12 Modified by Jenny Carlsson and Daniel Persson: added click listener for pause menu exit button <br>
 * 2021-10-19 Modified by Joel Båtsman Hilmersson: The class now extends InputAdapter to override methods to listen for keyboard input <br>
 * 2021-10-19 Modified by Jenny Carlsson: Added short keys for pause menu <br>
 */
public class GameScreenController extends InputAdapter {
    private final IControllModel model;

    public GameScreenController(IControllModel model) {
        this.model = model;
    }

    /**
     * Adds listener to map in GamerScreen
     *
     * @param image GameScreens mapImage
     */
    public void addMapClickListener(Image image) {
        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.checkIfTowerClicked(event.getStageX(), event.getStageY());
            }
        });
    }

    /**
     * Adds click listener to pause button in GameScreen
     *
     * @param button GameScreens pause button
     */
    public void addPauseButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.stopGameUpdate();
                model.setShowOverlay(ScreenOverlayEnum.PAUSE_MENU);
            }
        });
    }

    @Override
    public boolean keyDown (int keycode) {
        switch (keycode) {
            case (Input.Keys.ESCAPE) -> {
                model.stopGameUpdate();
                model.setShowOverlay(ScreenOverlayEnum.PAUSE_MENU);
                return true;
            }
            case (Input.Keys.F11) -> {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(1920, 1080);
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                }
                return true;
            }
            case (Input.Keys.NUM_1) -> {
                // Don't work ATM
                Matrix4 matrix = ScreenManager.getInstance().getCurrentScreen().getCamera().combined;
                Vector2 v = ScreenManager.getInstance().getCurrentScreen().toScreenCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()), matrix);
                model.dragStart("smurf", v.x, v.y);
                model.onDrag(2, 2, v.x, v.y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                model.dragEnd(2, 2, v.x, v.y);
                return true;
            }
            default -> {
                return false;
            }
        }

    }
}
