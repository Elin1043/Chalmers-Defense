package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.chalmersdefense.model.IControllModel;

/**
 * @author Daniel Persson
 * Controller class for RightSidePanel in GameScreen
 *
 * 2021-09-17 Modified by Elin Forsberg: Added listener for tower buttons <br>
 * 2021-10-04 Modified by Joel Båtsman Hilmersson: Changed to use IControllModel interface instead of Model <br>
 * 2021-10-11 Modified by Elin Forsberg: Added listener for powerUp buttons <br>
 */

public class RightSidePanelController {
    private IControllModel model;


    public RightSidePanelController(IControllModel model) {
        this.model = model;
    }

    /**
     * Listener for startButton
     * @param button the startbutton
     */
    public void addStartButtonListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.startRoundPressed();                  // Will need to change later more
            }
        });
    }

    /**
     * Listener for powerUpButtons
     * @param button
     */
    public void addPowerUpButtonListener(Button button) {
        String powerUpName = button.getName();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(powerUpName + "button Clicked");
            }
        });
    }
    /**
     * Listener for tower buttons
     *
     * @param button the tower button that was dragged
     */
    public void addTowerButtonListener(Button button) {
        button.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                String towerName = event.getListenerActor().getName();
                ImageButton button = (ImageButton) event.getListenerActor();
                model.dragStart(towerName, button.getX(), button.getY());
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                ImageButton button = (ImageButton) event.getListenerActor();
                float inputX = event.getStageX();
                float inputY = event.getStageY();
                int windowHeight = Gdx.graphics.getHeight();
                int windowWidth = Gdx.graphics.getWidth();
                model.onDrag((button.getImage().getWidth() / 2), (button.getImage().getHeight() / 2), inputX, inputY, windowHeight, windowWidth);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                ImageButton button = (ImageButton) event.getListenerActor();
                float inputX = event.getStageX();
                float inputY = event.getStageY();
                model.dragEnd((button.getImage().getWidth() / 2), (button.getImage().getHeight() / 2), inputX, inputY);
            }
        });

    }


}
