package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.chalmersdefense.Model.Model;

/**
 * @author
 *
 *
 * @Modified by Elin Forsberg
 *  Added listener for tower buttons
 */

public class RightSidePanelController {
    private Model model;


    public RightSidePanelController(Model model) {
        this.model = model;
    }

    public void addStartButtonListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Add action here
            }
        });
    }

    public void addTowerButtonListener(Button button) {
        button.addListener(new DragListener(){
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
        });

    }


}
