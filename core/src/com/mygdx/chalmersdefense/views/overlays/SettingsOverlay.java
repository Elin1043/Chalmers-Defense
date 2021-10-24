package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlayControllers.AbstractOverlayController;
import com.mygdx.chalmersdefense.controllers.overlayControllers.SettingsOverlayController;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 * @author Daniel Persson
 * A class to display settings overlay
 */
final class SettingsOverlay extends AbstractOverlay {
    private final SettingsOverlayController settingsOverlayController; // Controller used for adding listeners
    private final Preferences preferences;  // Reference to preferences of game

    private final Group settingsMenuGroup = new Group(); // Group to add all actors to
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png")); // Background image for overlay
    private Button goBackButton;  // Button for going back to pause menu

    private Slider musicSlider;  // Music slider for settings overlay
    private final Label musicPercentLabel = new Label("100%", FontFactory.getLabelStyle18Black()); // Label for music volume in %

    private Slider soundEffectsSlider;  // Sound effects slider for settings overlay
    private final Label soundEffectsPercentLabel = new Label("100%", FontFactory.getLabelStyle18Black()); // Label for sound effects volume in %

    private CheckBox isFullscreenCheckBox; // Checkbox for fullscreen toggle

    private CheckBox refreshRateCheckbox60;   // Checkbox for 60hz
    private CheckBox refreshRateCheckbox144;  // Checkbox for 144hz
    private CheckBox refreshRateCheckbox165;  // Checkbox for 165hz

    /**
     * Sets up class and passes abstractOverlayController to super constructor
     * @param abstractOverlayController reference to common controller
     * @param settingsOverlayController reference to controller for settings overlay
     * @param preferences reference to the games preferences
     */
    SettingsOverlay(AbstractOverlayController abstractOverlayController, SettingsOverlayController settingsOverlayController, Preferences preferences) {
        super(abstractOverlayController);
        this.settingsOverlayController = settingsOverlayController;
        this.preferences = preferences;
    }

    @Override
    void initialize() {
        stage.addActor(settingsMenuGroup);
        if (!settingsMenuGroup.hasChildren()) {
            settingsMenuGroup.addActor(backgroundImage);
            backgroundImage.setPosition(stage.getWidth() / 2 - backgroundImage.getWidth() / 2, stage.getHeight() / 2 - backgroundImage.getHeight() / 2);

            goBackButton = createGoBackButton();

            // Create and add click listener to exit button
            abstractOverlayController.addExitOverlayButtonClickListener(createExitPauseMenuButton(settingsMenuGroup, backgroundImage));

            // Creates title
            createSettingTitle();

            // Creates music slider and peripheral components
            createMusicSetting();

            // Creates sound effects slider and peripheral components
            createSoundEffectsSetting();

            // Creates autoplay label and checkbox
            createAutoplaySetting();

            // Creates fullscreen label and checkbox
            createFullscreenSetting();

            // Creates refresh rate label and checkboxes
            createLabels("Refresh rate:", backgroundImage.getX() + 250, backgroundImage.getY() + 50);
            createRefreshRateButtons();
        }
    }

    @Override
    public void render() {
        goBackButton.setVisible(ScreenManager.getInstance().getCurrentScreenEnum() != ScreenEnum.MAIN_MENU);

        updateValueLabels();
        isFullscreenCheckBox.setChecked(Gdx.graphics.isFullscreen());
        updateRefreshRateCheckBoxes();

        super.render();
        settingsMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        settingsMenuGroup.setVisible(false);
    }



   //Creates title
    private void createSettingTitle() {
        Label settingsTitleLabel = new Label("Settings", FontFactory.getLabelStyle36BlackBold());
        settingsMenuGroup.addActor(settingsTitleLabel);
        settingsTitleLabel.setPosition(backgroundImage.getX() + (backgroundImage.getWidth() / 2 - settingsTitleLabel.getWidth() / 2), backgroundImage.getY() + 320);
    }


    //Creates music slider and peripheral components
    private void createMusicSetting() {
        createLabels("Music:", backgroundImage.getX() + 250, backgroundImage.getY() + 275);
        musicSlider = createSlider(270, 275);
        settingsOverlayController.addMusicVolumeSliderListener(musicSlider);
        settingsOverlayController.addMuteMusicClickListener(createCheckBox(" Mute sound", 270, 245));
        settingsMenuGroup.addActor(musicPercentLabel);
        musicPercentLabel.setPosition(backgroundImage.getX() + 270 + 320,backgroundImage.getY() + 245);
        musicPercentLabel.setAlignment(Align.right);
    }


    //Creates sound effects slider and peripheral components
    private void createSoundEffectsSetting() {
        createLabels("Sound effects:", backgroundImage.getX() + 250, backgroundImage.getY() + 200);
        soundEffectsSlider = createSlider(270, 200);
        settingsOverlayController.addSoundEffectsVolumeSliderListener(soundEffectsSlider);
        settingsOverlayController.addMuteSoundEffectsClickListener(createCheckBox(" Mute sound effects", 270, 170));
        settingsMenuGroup.addActor(soundEffectsPercentLabel);
        soundEffectsPercentLabel.setPosition(backgroundImage.getX() + 270 + 320,backgroundImage.getY() + 170);
        soundEffectsPercentLabel.setAlignment(Align.right);
    }


    //Creates autoplay label and checkbox
    private void createAutoplaySetting() {
        createLabels("Autoplay:", backgroundImage.getX() + 250, backgroundImage.getY() + 125);
        settingsOverlayController.addAutoplayClickListener(createCheckBox("", 270, 129));
    }


    //Creates fullscreen label and checkbox
    private void createFullscreenSetting() {
        createLabels("Fullscreen:", backgroundImage.getX() + 590, backgroundImage.getY() + 125);
        isFullscreenCheckBox = createCheckBox("", 610, 129);
        settingsOverlayController.addFullscreenClickListener(isFullscreenCheckBox);
    }


    //Creates refresh rate buttons for different refresh rates
    private void createRefreshRateButtons() {
        TextureAtlas checkBoxTexture = new TextureAtlas(Gdx.files.internal("checkbox/CheckboxSkin.atlas")); // Load atlas file from skin
        Skin checkBoxSkin = new Skin(Gdx.files.internal("checkbox/CheckboxSkin.json"), checkBoxTexture); // Create skin object

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        settingsMenuGroup.addActor(horizontalGroup);
        horizontalGroup.space(20);
        horizontalGroup.setPosition(backgroundImage.getX() + 270, backgroundImage.getY() + 64);

        // Create 60 hz checkbox
        refreshRateCheckbox60 = new CheckBox(" 60", checkBoxSkin);
        horizontalGroup.addActor(refreshRateCheckbox60);
        settingsOverlayController.addRefreshRateClickListener(refreshRateCheckbox60, 60);
        refreshRateCheckbox60.setChecked(preferences.getInteger("refreshRate") == 60);

        // Create 144 hz checkbox
        refreshRateCheckbox144 = new CheckBox(" 144", checkBoxSkin);
        horizontalGroup.addActor(refreshRateCheckbox144);
        settingsOverlayController.addRefreshRateClickListener(refreshRateCheckbox144, 144);
        refreshRateCheckbox144.setChecked(preferences.getInteger("refreshRate") == 144);

        // Create 165 hz checkbox
        refreshRateCheckbox165 = new CheckBox(" 165", checkBoxSkin);
        horizontalGroup.addActor(refreshRateCheckbox165);
        settingsOverlayController.addRefreshRateClickListener(refreshRateCheckbox165, 165);
        refreshRateCheckbox165.setChecked(preferences.getInteger("refreshRate") == 165);
    }


    //Returns a new checkbox
    private CheckBox createCheckBox(String string, float x, float y) {
        TextureAtlas checkBoxTexture = new TextureAtlas(Gdx.files.internal("checkbox/CheckboxSkin.atlas")); // Load atlas file from skin
        Skin checkBoxSkin = new Skin(Gdx.files.internal("checkbox/CheckboxSkin.json"), checkBoxTexture); // Create skin object

        CheckBox checkBox = new CheckBox(string, checkBoxSkin);
        settingsMenuGroup.addActor(checkBox);
        checkBox.setPosition(backgroundImage.getX() + x, backgroundImage.getY() + y);
        return checkBox;
    }


    //Returns a new slider
    private Slider createSlider(float x, float y) {
        TextureAtlas settingsSliderTexture = new TextureAtlas(Gdx.files.internal("settingsSlider/SettingsSliderSkin.atlas")); // Load atlas file from skin
        Skin settingsSliderSkin = new Skin(Gdx.files.internal("settingsSlider/SettingsSliderSkin.json"), settingsSliderTexture); // Create skin object

        Slider slider = new Slider(0, 100, 1, false, settingsSliderSkin);
        settingsMenuGroup.addActor(slider);
        slider.setPosition(backgroundImage.getX() + x,backgroundImage.getY() + y);
        slider.setSize(364, 25);
        return slider;
    }


    //Creates labels
    private void createLabels(String text, float x, float y){
        Label label = new Label(text, FontFactory.getLabelStyle26Black());
        settingsMenuGroup.addActor(label);
        label.setPosition(x - label.getWidth(), y);
        label.setAlignment(Align.right);
    }


   //Returns a new go back button
   private Button createGoBackButton() {
        TextureAtlas GoBackButtonTexture = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "goBackButtonSkin/GoBackButtonSkin.atlas")); // Load atlas file from skin
        Skin goBackButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "goBackButtonSkin/GoBackButtonSkin.json"), GoBackButtonTexture); // Create skin object

        Button goBackButton = new Button(goBackButtonSkin);
        settingsMenuGroup.addActor(goBackButton);
        goBackButton.setPosition(backgroundImage.getX() + 15, backgroundImage.getY() + 340);
        settingsOverlayController.addGoBackButton(goBackButton);
        return goBackButton;
    }


    //Updates labels
    private void updateValueLabels() {
        musicPercentLabel.setText("" + (int) musicSlider.getValue() + "%");
        soundEffectsPercentLabel.setText("" + (int) soundEffectsSlider.getValue() + "%");
    }


    //Updates checkboxes
    private void updateRefreshRateCheckBoxes() {
        refreshRateCheckbox60.setChecked(preferences.getInteger("refreshRate") == 60);
        refreshRateCheckbox144.setChecked(preferences.getInteger("refreshRate") == 144);
        refreshRateCheckbox165.setChecked(preferences.getInteger("refreshRate") == 165);
    }

}
