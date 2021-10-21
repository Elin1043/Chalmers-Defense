package com.mygdx.chalmersdefense.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
 * 2021-10-19 Modified by Joel Båtsman Hilmersson: The class now extends InputAdapter to override methods to listen for keyboard input <br>
 * 2021-10-19 Modified by Jenny Carlsson: Added short keys for power ups <br>
 */

public class RightSidePanelController extends InputAdapter {
    private final IControllModel model;


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
     * @param button the button to add the listener to
     */
    public void addPowerUpButtonListener(Button button) {
        String powerUpName = button.getName();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.powerUpClicked(powerUpName);
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
                Button button = (Button) event.getListenerActor();
                model.dragStart(towerName, button.getX(), button.getY());
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                Button button = (Button) event.getListenerActor();
                float inputX = event.getStageX();
                float inputY = event.getStageY();
                int windowHeight = Gdx.graphics.getHeight();
                int windowWidth = Gdx.graphics.getWidth();
                model.onDrag((button.getWidth() / 2), (button.getHeight() / 2), inputX, inputY, windowHeight, windowWidth);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                Button button = (Button) event.getListenerActor();
                float inputX = event.getStageX();
                float inputY = event.getStageY();
                model.dragEnd((button.getWidth() / 2), (button.getHeight() / 2), inputX, inputY);
            }
        });
    }


    @Override
    public boolean keyDown (int keycode) {
        switch (keycode) {
            case(Input.Keys.SPACE) -> {
                model.startRoundPressed();
                return true;
            }
            case(Input.Keys.C) -> {
                model.powerUpClicked("cleanHands");
                return true;
            }
            case(Input.Keys.V) -> {
                model.powerUpClicked("vaccinated");
                return true;
            }
            case(Input.Keys.M) -> {
                model.powerUpClicked("maskedUp");
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
