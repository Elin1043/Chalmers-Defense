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

    private Button playButton;
    private Button settingsButton;
    private Button infoButton;

    private final String buttonsAssetsRoot = "buttons/mainScreenButtons/";

    private final TextureAtlas quitButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "quitButtonSkin/QuitButtonSkin.atlas")); // Load atlas file from skin
    private final Skin quitButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "quitButtonSkin/QuitButtonSkin.json"), quitButtonAtlas); // Create skin object
    private final Button quitButton = new Button(quitButtonSkin);

    private final MainScreenController mainScreenController;
    private final IViewModel model;

    MainScreen(IViewModel model, MainScreenController mainScreenController) {
        super();
        this.model = model;
        this.mainScreenController = mainScreenController;

        addToMultiplexer(mainScreenController);

        createPlayButton();
        createSettingsButton();
        createInfoButton();
        addActor(playButton);
        addActor(quitButton);
        addActor(settingsButton);
        addActor(infoButton);
    }

    private void createPlayButton() {
        TextureAtlas playButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "playButtonSkin/PlayButtonSkin.atlas")); // Load atlas file from skin
        Skin playButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "playButtonSkin/PlayButtonSkin.json"), playButtonAtlas); // Create skin object
        playButton = new Button(playButtonSkin); //Set the button up
        playButton.setPosition(832, 22);

        quitButton.setPosition(5, 13);
        mainScreenController.addQuitButtonClickListener(quitButton);

        //Add the button to the stage to perform rendering and take input. (WILL BE MOVED)
        mainScreenController.addPlayButtonListener(playButton);
    }

    private void createSettingsButton() {
        TextureAtlas settingsButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "settingsButtonSkin/SettingsButtonSkin.atlas")); // Load atlas file from skin
        Skin settingsButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "settingsButtonSkin/SettingsButtonSkin.json"), settingsButtonAtlas); // Create skin object
        settingsButton = new Button(settingsButtonSkin);
        mainScreenController.addSettingsButtonClickListener(settingsButton);
        settingsButton.setPosition(430,110);
    }

    @Override
    protected void setBackgroundImage() {
        Image img = new Image(new Texture("HomeScreen.png"));
        getActors().insert(0, img);
    }

    private void createInfoButton() {
        TextureAtlas infoButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "infoButtonSkin/InfoButtonSkin.atlas")); // Load atlas file from skin
        Skin infoButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "infoButtonSkin/InfoButtonSkin.json"), infoButtonAtlas); // Create skin object
        infoButton = new Button(infoButtonSkin);
        mainScreenController.addInfoButtonClickListener(infoButton);
        infoButton.setPosition(1140,110);
    }

    /**
     * Renders GameScreen to screen
     *
     * @param delta the timeframe from previous frame to current frame
     */
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

        OverlayManager.getInstance().showOverlay(model.getCurrentOverlay());
        AbstractOverlay abstractOverlay = OverlayManager.getInstance().getCurrentOverlay();
        if (abstractOverlay != null) {
            abstractOverlay.render();
        }
    }
}
