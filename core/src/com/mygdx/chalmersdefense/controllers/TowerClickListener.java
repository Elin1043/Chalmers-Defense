package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.Model;

/**
 * @author Elin Forsberg
 * A listener for the towers placed on map
 */
public class TowerClickListener extends ClickListener {
    Model model;
    public TowerClickListener(Model model){
        this.model = model;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        model.towerClicked(event.getStageX(), event.getStageY());
    }
}
