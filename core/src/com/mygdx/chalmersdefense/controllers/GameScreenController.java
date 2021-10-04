package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 * @author Daniel Persson
 *
 * 2021-09-22 created by Daniel Persson: A class for handling listener setup for GameScreen.
 * 2021-10-03 Modified by Daniel Persson: Added click listener for main menu and try again buttons.
 */
public class GameScreenController {
    private Model model;

    public GameScreenController(Model model) {
        this.model = model;
    }

    /**
    * Adds listener to map in GamerScreen
    * @param image  GameScreens mapImage
    * */
    public void addMapClickListener(Image image) {
        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.towerNotClicked();
            }
        });
    }

    /**
     * Adds click listener to main menu button in LostPanel
     * @param button LostPanels main menu button
     */
    public void addLostPanelMainMenuClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                model.resetModel();
            }
        });
    }

    /**
     * Adds click listener to try again button in LostPanel
     * @param button LostPanels try again button
     */
    public void addLostPanelTryAgainClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.resetModel();
            }
        });
    }
}
