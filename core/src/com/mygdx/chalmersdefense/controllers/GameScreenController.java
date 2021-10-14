package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.views.overlays.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.model.IControllModel;

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
 */
public class GameScreenController {
    private IControllModel model;

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

}
