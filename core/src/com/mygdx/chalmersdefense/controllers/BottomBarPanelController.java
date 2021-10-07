package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;

/**
 * @author Daniel Persson
 * Controller class for bottom bar element in GameScreen
 *
 * 2021-10-04 Modified by Joel Båtsman Hilmersson: Changed to use IControllModel interface instead of Model
 */
public class BottomBarPanelController {
    private final IControllModel model;

    public BottomBarPanelController(IControllModel model) {
        this.model = model;
    }

    /**
     * @author Daniel Persson
     *
     * Method for adding click listener to upgrade button
     */
    public void addClickListenerUpgradeButton(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.upgradeClickedTower();
            }
        });
    }
}
