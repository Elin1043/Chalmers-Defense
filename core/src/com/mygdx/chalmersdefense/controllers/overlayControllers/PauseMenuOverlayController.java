package com.mygdx.chalmersdefense.controllers.overlayControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

/**
 * @author Daniel Persson
 * A controller class for PauseMenyOverlay
 */
public class PauseMenuOverlayController {
    private final IControllModel model;

    public PauseMenuOverlayController(IControllModel model) {
        this.model = model;
    }

    /**
     * Add click listener for pause menu buttons
     * @param button pause menu button
     * @param buttonName type of button
     */
    public void addPauseMenuClickListeners(Button button, String buttonName) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            switch (buttonName) {
                case "Continue" -> {
                    model.startGameUpdate();
                    model.setShowOverlay(ScreenOverlayEnum.NONE);
                }
                case "Settings" -> model.setShowOverlay(ScreenOverlayEnum.SETTINGS);
                case "Quit" -> {
                    model.stopGameUpdate();
                    model.resetModel();
                    model.setShowOverlay(ScreenOverlayEnum.NONE);
                    ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                }
            }
            }
        });
    }
}
