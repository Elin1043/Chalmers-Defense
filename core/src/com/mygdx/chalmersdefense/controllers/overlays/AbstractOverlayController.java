package com.mygdx.chalmersdefense.controllers.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;


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
