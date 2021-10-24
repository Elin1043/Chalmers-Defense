package com.mygdx.chalmersdefense.controllers.overlayControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
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
    private final IControllModel model;     // Model reference
    private final Preferences preferences;  // Eventbus to publish events to

    /**
     * Creates a controller for use by the SettingsOverlay class
     *
     * @param model       the model to control
     * @param preferences to get game preferences from
     */
    public SettingsOverlayController(IControllModel model, Preferences preferences) {
        this.model = model;
        this.preferences = preferences;
    }

    /**
     * Add change listener to music volume slider
     *
     * @param slider to add listener to
     */
    public void addMusicVolumeSliderListener(Slider slider) {
        slider.setValue(preferences.getFloat("musicVolume") * 100);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putFloat("musicVolume", slider.getValue() / 100f);
            }
        });
    }

    /**
     * Add change listener to sound effects volume slider
     *
     * @param slider to add listener to
     */
    public void addSoundEffectsVolumeSliderListener(Slider slider) {
        slider.setValue(preferences.getFloat("soundEffectsVolume") * 100);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putFloat("soundEffectsVolume", slider.getValue() / 100f);
            }
        });
    }

    /**
     * Add click listener to back button in settings
     *
     * @param button to add listener to
     */
    public void addGoBackButton(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.setShowOverlay(ScreenOverlayEnum.PAUSE_MENU);
            }
        });
    }

    /**
     * Add change listener to music volume slider
     *
     * @param checkBox to add listener to
     */
    public void addMuteMusicClickListener(CheckBox checkBox) {
        checkBox.setChecked(preferences.getBoolean("muteMusic"));
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putBoolean("muteMusic", checkBox.isChecked());
            }
        });
    }

    /**
     * Add change listener to sound effects mute checkbox
     *
     * @param checkBox to add listener to
     */
    public void addMuteSoundEffectsClickListener(CheckBox checkBox) {
        checkBox.setChecked(preferences.getBoolean("muteSoundEffects"));
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putBoolean("muteSoundEffects", checkBox.isChecked());
            }
        });
    }

    /**
     * Add change listener to autoplay checkbox
     *
     * @param checkBox to add listener to
     */
    public void addAutoplayClickListener(CheckBox checkBox) {
        checkBox.setChecked(preferences.getBoolean("autoplay"));
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putBoolean("autoplay", checkBox.isChecked());
            }
        });
    }

    /**
     * Add change listener to fullscreen checkbox
     *
     * @param checkBox to add listener to
     */
    public void addFullscreenClickListener(CheckBox checkBox) {
        checkBox.setChecked(Gdx.app.getGraphics().isFullscreen());
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (checkBox.isChecked()) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                } else {
                    Gdx.graphics.setWindowedMode(1920, 1080);
                }
            }
        });
    }

    /**
     * Add change listener to refresh rate checkbox
     *
     * @param checkBox    to add listener to
     * @param refreshRate what refresh rate to change to
     */
    public void addRefreshRateClickListener(CheckBox checkBox, int refreshRate) {
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putInteger("refreshRate", refreshRate);
                Gdx.graphics.setForegroundFPS(refreshRate);
            }
        });
    }

}
