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

    /**
     * Constructor for TowerClickListener class
     * @param model current model
     */
    public TowerClickListener(Model model){
        this.model = model;
    }

    /**
     * Listener if a tower was clicked
     * @param event the InputEvent
     * @param x The X-position of where it was clicked
     * @param y The Y-position of where it was clicked
     */
    @Override
    public void clicked(InputEvent event, float x, float y) {
        model.towerClicked();
    }
}
