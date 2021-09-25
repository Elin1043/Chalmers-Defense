package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.Model;

/*
* @author Daniel Persson
* @date 2021-09-22
*
* 2021-09-22 created by Daniel Persson: A class for handling listener setup for GameScreen.
* */
public class GameScreenController {
    private Model model;

    public GameScreenController(Model model) {
        this.model = model;
    }

    /*
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
}
