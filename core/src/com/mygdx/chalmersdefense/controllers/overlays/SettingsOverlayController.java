package com.mygdx.chalmersdefense.controllers.overlays;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.model.IControllModel;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

/**
 * @author Daniel Persson
 * A controller class for SettingsOverlay
 */
public class SettingsOverlayController {
    private final IControllModel model;
    private final Preferences preferences;

    public SettingsOverlayController(IControllModel model, Preferences preferences) {
        this.model = model;
        this.preferences = preferences;
    }

    /**
     * Added click listener for exit pause menu button
     * @param button exit button
     */
    public void addExitPauseMenuButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.startGameUpdate();
                model.setShowOverlay(ScreenOverlayEnum.NONE);
            }
        });
    }

    /**
     * Add change listener to music volume slider
     * @param slider to add listener to
     */
    public void addMusicVolumeSliderListener(Slider slider) {
        slider.setValue(preferences.getFloat("musicVolume") * 100);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Slider: " + slider.getValue());
                preferences.putFloat("musicVolume", slider.getValue() / 100f);
            }
        });
    }

    public void addGoBackButton(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.setShowOverlay(ScreenOverlayEnum.PAUSE_MENU);
            }
        });
    }


}
