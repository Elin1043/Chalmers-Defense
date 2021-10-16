package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.views.overlays.AbstractOverlay;
import com.mygdx.chalmersdefense.views.overlays.OverlayManager;

/**
 * @author Daniel Persson
 * A class for rendering the main screen in the game
 */
public class MainScreen extends AbstractScreen {
    Image img;

    private ImageButton playButton;
    private Button settingsButton;

    private final TextureAtlas quitButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/quitButtonSkin/QuitButtonSkin.atlas")); // Load atlas file from skin
    private final Skin quitButtonSkin = new Skin(Gdx.files.internal("buttons/quitButtonSkin/QuitButtonSkin.json"), quitButtonAtlas); // Create skin object
    private final Button quitButton = new Button(quitButtonSkin);

    private final MainScreenController mainScreenController;
    private final IViewModel model;

    public MainScreen(IViewModel model, MainScreenController mainScreenController) {
        super();
        this.model = model;
        this.mainScreenController = mainScreenController;
        img = new Image(new Texture("HomeScreen.png"));

        createPlayButton();
        createSettingsButton();
        addActor(img);
        addActor(playButton);
        addActor(quitButton);
        addActor(settingsButton);
    }

    private void createPlayButton() {
        Texture playButtonTexture = new Texture(Gdx.files.internal("playButton.png"));
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(832, 22);

        quitButton.setPosition(5, 13);
        mainScreenController.addQuitButtonClickListener(quitButton);

        //Add the button to the stage to perform rendering and take input. (WILL BE MOVED)
        mainScreenController.addPlayButtonListener(playButton);
    }

    private void createSettingsButton() {
        TextureAtlas settingsButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/settingsButtonSkin/SettingsButtonSkin.atlas")); // Load atlas file from skin
        Skin settingsButtonSkin = new Skin(Gdx.files.internal("buttons/settingsButtonSkin/SettingsButtonSkin.json"), settingsButtonAtlas); // Create skin object
        settingsButton = new Button(settingsButtonSkin);
        mainScreenController.addSettingsButtonClickListener(settingsButton);
        settingsButton.setPosition(430,110);
    }

    /**
     * Renders GameScreen to screen
     *
     * @param delta the timeframe from previous frame to current frame
     */
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());
        Gdx.input.setInputProcessor(this);

        OverlayManager.getInstance().showOverlay(model.showOverlay());
        AbstractOverlay abstractOverlay = OverlayManager.getInstance().getCurrentOverlay();
        if (abstractOverlay != null) {
            abstractOverlay.render();
        }
    }
}
