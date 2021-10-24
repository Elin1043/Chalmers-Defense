package com.mygdx.chalmersdefense.controllers.overlayControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

/**
 * @author Daniel Persson
 * A controller class for WinPanelOverlay
 */
public class WinPanelOverlayController {
    private final IControllModel model; // Model reference

    /**
     * Creates a controller for use by the WinPanelOverlay class
     *
     * @param model the model to control
     */
    public WinPanelOverlayController(IControllModel model) {
        this.model = model;
    }

    /**
     * Adds click listener to continue button in WinPanelOverlay
     *
     * @param button WinPanels continue button
     */
    public void addWinPanelContinueClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.setShowOverlay(ScreenOverlayEnum.NONE);
            }
        });
    }
}
