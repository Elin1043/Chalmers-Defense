package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.Model.Model;

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

}
