package com.mygdx.chalmersdefense.controllers.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;


/**
 * @author Jenny Carlsson
 * @author Daniel Persson
 * A controller class for keys <br>
 */
public class AbstractOverlayController extends InputAdapter {
    private final IControllModel model;

    public AbstractOverlayController(IControllModel model) {
        this.model = model;
    }

    /**
     * Added click listener for exit pause menu button
     * @param button exit button
     */
    public void addExitOverlayButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.startGameUpdate();
                model.setShowOverlay(ScreenOverlayEnum.NONE);
            }
        });
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

    @Override
    public boolean keyDown (int keycode) {
        switch(keycode) {
            case (Input.Keys.ESCAPE) -> {
                model.startGameUpdate();
                model.setShowOverlay(ScreenOverlayEnum.NONE);
                return true;
            }
            case (Input.Keys.F11) -> {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(1920, 1080);
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                }
                return true;
            }
            default -> {
                return false;
            }
        }

    }
}
