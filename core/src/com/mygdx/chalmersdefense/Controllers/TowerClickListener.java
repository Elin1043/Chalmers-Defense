package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.Model.Model;

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
        model.towerClicked();
    }
}
