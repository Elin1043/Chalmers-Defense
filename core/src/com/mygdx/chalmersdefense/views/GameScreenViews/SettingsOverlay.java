package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.chalmersdefense.controllers.overlays.SettingsOverlayController;

public class SettingsOverlay extends AbstractOverlay {
    private final SettingsOverlayController settingsOverlayController;

    private final Group settingsMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png"));

    public SettingsOverlay(SettingsOverlayController settingsOverlayController) {
        this.settingsOverlayController = settingsOverlayController;
    }

    @Override
    void initialize() {
        stage.addActor(settingsMenuGroup);
        if (!settingsMenuGroup.hasChildren()) {

            settingsMenuGroup.addActor(backgroundImage);
            backgroundImage.setPosition(stage.getWidth() / 2 - backgroundImage.getWidth() / 2, stage.getHeight() / 2 - backgroundImage.getHeight() / 2);

            ImageButton exitButton = createExitPauseMenuButton(settingsMenuGroup, backgroundImage);
            settingsOverlayController.addExitPauseMenuButtonClickListener(exitButton);
            createMusicSlider();
        }
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        stage.act();
        stage.draw();
        settingsMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        settingsMenuGroup.setVisible(false);
    }

    private void createMusicSlider() {
        TextureAtlas settingsSliderTexture = new TextureAtlas(Gdx.files.internal("settingsSlider/SettingsSliderSkin.atlas")); // Load atlas file from skin
        Skin settingsSliderSkin = new Skin(Gdx.files.internal("settingsSlider/SettingsSliderSkin.json"), settingsSliderTexture); // Create skin object

        Slider musicSlider = new Slider(0, 100, 1, false, settingsSliderSkin);
        settingsMenuGroup.addActor(musicSlider);
        musicSlider.setPosition(834, 707);
        musicSlider.setSize(364, 25);
        settingsOverlayController.addMusicVolumeSliderListener(musicSlider);
    }
}
