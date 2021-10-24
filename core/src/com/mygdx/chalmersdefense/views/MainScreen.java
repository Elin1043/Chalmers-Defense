package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.views.overlays.AbstractOverlay;
import com.mygdx.chalmersdefense.views.overlays.OverlayManager;

/**
 * @author Daniel Persson
 * A class for rendering the main screen in the game
 */
final class MainScreen extends AbstractScreen {
    private final String buttonsAssetsRoot = "buttons/mainScreenButtons/"; // Root path for button skins

    private final MainScreenController mainScreenController; // Controller for MainScreen
    private final IViewModel model;                          // Reference to models IView methods

    /**
     * Creates the main screen of the game
     * @param model the model to display information from
     * @param mainScreenController the controller class to use for adding listeners to this class
     */
    MainScreen(IViewModel model, MainScreenController mainScreenController) {
        super();
        this.model = model;
        this.mainScreenController = mainScreenController;

        addToMultiplexer(mainScreenController);

        createPlayButton();
        createSettingsButton();
        createInfoButton();
        createQuitButton();
    }

    /**
     * Creates play button
     */
    private void createPlayButton() {
        TextureAtlas playButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "playButtonSkin/PlayButtonSkin.atlas")); // Load atlas file from skin
        Skin playButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "playButtonSkin/PlayButtonSkin.json"), playButtonAtlas); // Create skin object
        Button playButton = new Button(playButtonSkin); //Set the button up
        playButton.setPosition(832, 22);

        mainScreenController.addPlayButtonListener(playButton);
        addActor(playButton);
    }

    /**
     * Creates settings button
     */
    private void createSettingsButton() {
        TextureAtlas settingsButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "settingsButtonSkin/SettingsButtonSkin.atlas")); // Load atlas file from skin
        Skin settingsButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "settingsButtonSkin/SettingsButtonSkin.json"), settingsButtonAtlas); // Create skin object
        Button settingsButton = new Button(settingsButtonSkin);
        settingsButton.setPosition(430, 110);

        mainScreenController.addSettingsButtonClickListener(settingsButton);
        addActor(settingsButton);
    }

    /**
     * Creates info button
     */
    private void createInfoButton() {
        TextureAtlas infoButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "infoButtonSkin/InfoButtonSkin.atlas")); // Load atlas file from skin
        Skin infoButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "infoButtonSkin/InfoButtonSkin.json"), infoButtonAtlas); // Create skin object
        Button infoButton = new Button(infoButtonSkin);
        infoButton.setPosition(1140,110);

        mainScreenController.addInfoButtonClickListener(infoButton);
        addActor(infoButton);
    }

    /**
     * Creates quit button
     */
    private void createQuitButton() {
        TextureAtlas quitButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "quitButtonSkin/QuitButtonSkin.atlas")); // Load atlas file from skin
        Skin quitButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "quitButtonSkin/QuitButtonSkin.json"), quitButtonAtlas); // Create skin object
        Button quitButton = new Button(quitButtonSkin);
        quitButton.setPosition(5, 13);

        mainScreenController.addQuitButtonClickListener(quitButton);
        addActor(quitButton);
    }

    @Override
    void setBackgroundImage() {
        Image img = new Image(new Texture("HomeScreen.png"));
        getActors().insert(0, img);
    }

    /**
     * Renders GameScreen to screen
     *
     * @param delta the timeframe from previous frame to current frame
     */
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

        // Render open overlay on MainScreen stage
        OverlayManager.getInstance().showOverlay(model.getCurrentOverlay(), this);
        AbstractOverlay abstractOverlay = OverlayManager.getInstance().getCurrentOverlay();
        if (abstractOverlay != null) {
            abstractOverlay.render();
        }
    }
}
