package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;

/**
 * @author Daniel Persson
 * Controller class for bottom bar element in GameScreen
 *
 * 2021-10-04 Modified by Joel Båtsman Hilmersson: Changed to use IControllModel interface instead of Model <br>
 * 2021-10-07 Modified by Elin Forsberg: Added listener for SellButton. <br>
 */
public class BottomBarPanelController {
    private final IControllModel model; // Model reference

    /**
     * Creates controller instance
     * @param model to use IControllModel methods with
     */
    public BottomBarPanelController(IControllModel model) {
        this.model = model;
    }

    /**
     * Method for adding click listener to upgrade button
     * @param button to add click listener to
     */
    public void addClickListenerUpgradeButton(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.upgradeClickedTower();
            }
        });
    }

    /**
     * Method for adding click listener to upgrade button
     * @param button to add click listener to
     */
    public void addClickListenerSellButton(ImageButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.sellClickedTower();
            }
        });
    }

    /**
     * Method for adding click listener to changeTargetMode button
     * @param button to add click listener to
     */
    public void addClickListenerTargetModeButton(ImageButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.changeTargetMode(event.getListenerActor().getName().equals("right"));
            }
        });
    }
}
