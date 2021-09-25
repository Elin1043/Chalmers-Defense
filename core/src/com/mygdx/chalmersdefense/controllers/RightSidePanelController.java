package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.chalmersdefense.model.Model;

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
                model.getVirusSpawner().spawnRound(1);                  // Will need to change later more
            }
        });
    }

    /**
     * Listener for tower buttons
     * @param button the tower button that was dragged
     */
    public void addTowerButtonListener(Button button) {
        button.addListener(new DragListener(){
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                String towerName = event.getListenerActor().getName();
                ImageButton button = (ImageButton) event.getListenerActor();
                model.dragStart(towerName, (int)button.getX(), (int)button.getY());
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                ImageButton button = (ImageButton) event.getListenerActor();
                int inputX = Gdx.input.getX();
                int inputY = Gdx.input.getY();
                int windowHeight = Gdx.graphics.getHeight();
                int windowWidth = Gdx.graphics.getWidth();
                model.onDrag((int) (button.getImage().getWidth()/2),(int) (button.getImage().getHeight()/2) ,inputX,inputY, windowHeight, windowWidth);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                ImageButton button = (ImageButton) event.getListenerActor();
                int inputX = Gdx.input.getX();
                int inputY = Gdx.input.getY();
                int windowHeight = Gdx.graphics.getHeight();
                model.dragEnd((int) (button.getImage().getWidth()/2),(int) (button.getImage().getHeight()/2) ,inputX,inputY, windowHeight);
            }
        });

    }


}
