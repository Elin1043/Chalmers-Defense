package com.mygdx.chalmersdefense.controllers.overlays;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 * @author Daniel Persson
 * A controller class for WinPanelOverlay
 */
public class WinPanelOverlayController {
    private IControllModel model;

    public WinPanelOverlayController(IControllModel model) {
        this.model = model;
    }

    /**
     * Adds click listener to main menu button in LostPanelOverlay
     *
     * @param button LostPanels main menu button
     */
    public void addMainMenuClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                model.resetModel();
            }
        });
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
