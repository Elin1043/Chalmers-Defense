package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.kotcrab.vis.ui.widget.Draggable;
import com.mygdx.chalmersdefense.Model.Model;

public class TowerButtonListener extends DragListener {
    Model model;
    public TowerButtonListener(Model model){
        this.model = model;
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        model.dragStart(event);
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        model.onDrag(event);
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        model.dragEnd(event);
    }
}
