package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlays.SettingsOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

import javax.swing.*;

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

            createLabels("Music:", backgroundImage.getX() + 250, backgroundImage.getY() + 275);
            createLabels("Sound effects:", backgroundImage.getX() + 250, backgroundImage.getY() + 200);
            createLabels("Autoplay:", backgroundImage.getX() + 250, backgroundImage.getY() + 125);
            createLabels("Resolution:", backgroundImage.getX() + 250, backgroundImage.getY() + 50);
            Label settingsTitleLabel = new Label("Settings", FontFactory.getLabelStyle36BlackBold());
            settingsMenuGroup.addActor(settingsTitleLabel);
            settingsTitleLabel.setPosition(backgroundImage.getX() + (backgroundImage.getWidth() / 2 - settingsTitleLabel.getWidth() / 2), backgroundImage.getY() + 320);

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

    private void createLabels(String text, float x, float y){
        Label label = new Label(text, FontFactory.getLabelStyle26Black());
        settingsMenuGroup.addActor(label);
        label.setPosition(x - label.getWidth(), y);
        label.setAlignment(Align.right);
    }

    private void createGoBackButton() {
        TextureAtlas GoBackButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/goBackButtonSkin/GoBackButtonSkin.atlas")); // Load atlas file from skin
        Skin goBackButtonSkin = new Skin(Gdx.files.internal("buttons/goBackButtonSkin/GoBackButtonSkin.json"), GoBackButtonTexture); // Create skin object

        Button goBackButton = new Button(goBackButtonSkin);
        settingsMenuGroup.addActor(goBackButton);
        goBackButton.setPosition(backgroundImage.getX(), backgroundImage.getY());


    }



}
