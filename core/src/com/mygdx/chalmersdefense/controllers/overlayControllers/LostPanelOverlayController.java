package com.mygdx.chalmersdefense.controllers.overlayControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;

/**
 * @author Daniel Persson
 * A controller class for LostPanelOverlay
 */
public class LostPanelOverlayController {
    private final IControllModel model; // Model reference

    /**
     * Creates a controller for use by the LostPanelOverlay class
     *
     * @param model the model to control
     */
    public LostPanelOverlayController(IControllModel model) {
        this.model = model;
    }

    /**
     * Adds click listener to try again button in LostPanelOverlay
     *
     * @param button LostPanels try again button
     */
    public void addLostPanelTryAgainClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.resetModel();
            }
        });
    }
}
