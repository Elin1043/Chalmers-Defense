package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlays.SettingsOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

public class SettingsOverlay extends AbstractOverlay {
    private final SettingsOverlayController settingsOverlayController;

    private final Group settingsMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png"));
    private Button goBackButton;

    public SettingsOverlay(SettingsOverlayController settingsOverlayController) {
        this.settingsOverlayController = settingsOverlayController;
    }

    @Override
    protected void initialize() {
        stage.addActor(settingsMenuGroup);
        if (!settingsMenuGroup.hasChildren()) {
            settingsMenuGroup.addActor(backgroundImage);
            backgroundImage.setPosition(stage.getWidth() / 2 - backgroundImage.getWidth() / 2, stage.getHeight() / 2 - backgroundImage.getHeight() / 2);

            goBackButton = createGoBackButton();

            ImageButton exitButton = createExitPauseMenuButton(settingsMenuGroup, backgroundImage);
            settingsOverlayController.addExitPauseMenuButtonClickListener(exitButton);

            createLabels("Music:", backgroundImage.getX() + 250, backgroundImage.getY() + 275);
            settingsOverlayController.addMusicVolumeSliderListener(createSlider(270, 275));
            settingsOverlayController.addMuteMusicClickListener(createCheckBox(" Mute sound", 270, 245));

            createLabels("Sound effects:", backgroundImage.getX() + 250, backgroundImage.getY() + 200);
            settingsOverlayController.addSoundEffectsVolumeSliderListener(createSlider(270, 200));
            settingsOverlayController.addMuteSoundEffectsClickListener(createCheckBox(" Mute sound effects", 270, 170));

            createLabels("Autoplay:", backgroundImage.getX() + 250, backgroundImage.getY() + 125);
            settingsOverlayController.addAutoplayClickListener(createCheckBox("", 270, 127));

            createLabels("Resolution:", backgroundImage.getX() + 250, backgroundImage.getY() + 50);
            Label settingsTitleLabel = new Label("Settings", FontFactory.getLabelStyle36BlackBold());
            settingsMenuGroup.addActor(settingsTitleLabel);
            settingsTitleLabel.setPosition(backgroundImage.getX() + (backgroundImage.getWidth() / 2 - settingsTitleLabel.getWidth() / 2), backgroundImage.getY() + 320);

        }
    }

    private CheckBox createCheckBox(String string, float x, float y) {
        TextureAtlas checkBoxTexture = new TextureAtlas(Gdx.files.internal("checkbox/CheckboxSkin.atlas")); // Load atlas file from skin
        Skin checkBoxSkin = new Skin(Gdx.files.internal("checkbox/CheckboxSkin.json"), checkBoxTexture); // Create skin object

        CheckBox checkBox = new CheckBox(string, checkBoxSkin);
        settingsMenuGroup.addActor(checkBox);
        checkBox.setPosition(backgroundImage.getX() + x, backgroundImage.getY() + y);
        return checkBox;
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        goBackButton.setVisible(ScreenManager.getInstance().getCurrentScreenEnum() != ScreenEnum.MAIN_MENU);

        stage.act();
        stage.draw();
        settingsMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        settingsMenuGroup.setVisible(false);
    }

    private Slider createSlider(float x, float y) {
        TextureAtlas settingsSliderTexture = new TextureAtlas(Gdx.files.internal("settingsSlider/SettingsSliderSkin.atlas")); // Load atlas file from skin
        Skin settingsSliderSkin = new Skin(Gdx.files.internal("settingsSlider/SettingsSliderSkin.json"), settingsSliderTexture); // Create skin object

        Slider slider = new Slider(0, 100, 1, false, settingsSliderSkin);
        settingsMenuGroup.addActor(slider);
        slider.setPosition(backgroundImage.getX() + x,backgroundImage.getY() + y);
        slider.setSize(364, 25);
        return slider;
    }

    private void createLabels(String text, float x, float y){
        Label label = new Label(text, FontFactory.getLabelStyle26Black());
        settingsMenuGroup.addActor(label);
        label.setPosition(x - label.getWidth(), y);
        label.setAlignment(Align.right);
    }

    private Button createGoBackButton() {
        TextureAtlas GoBackButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/goBackButtonSkin/GoBackButtonSkin.atlas")); // Load atlas file from skin
        Skin goBackButtonSkin = new Skin(Gdx.files.internal("buttons/goBackButtonSkin/GoBackButtonSkin.json"), GoBackButtonTexture); // Create skin object

        Button goBackButton = new Button(goBackButtonSkin);
        settingsMenuGroup.addActor(goBackButton);
        goBackButton.setPosition(backgroundImage.getX() + 15, backgroundImage.getY() + 340);
        settingsOverlayController.addGoBackButton(goBackButton);
        return goBackButton;
    }


}
