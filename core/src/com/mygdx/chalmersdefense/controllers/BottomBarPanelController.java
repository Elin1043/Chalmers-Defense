package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.Model;

/*
* @author Daniel Persson
*
* */
public class BottomBarPanelController {
    private Model model;

    public BottomBarPanelController(Model model) {
        this.model = model;
    }

    /*
    * @author Daniel Persson
    *
    * Method for adding click listener to upgrade button
    * */
    public void addClickListenerUpgradeButton(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Do something
            }
        });
    }
}
