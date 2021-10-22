package com.mygdx.chalmersdefense.controllers.overlays;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 * @author Daniel Persson
 * A controller class for LostPanelOverlay
 */
public class LostPanelOverlayController {
    private final IControllModel model;
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
