package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

public class SettingsOverlay extends AbstractOverlay {

    private final Group settingsMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/SettingsBackgroundImage.png"));

    public SettingsOverlay(Stage stage, GameScreenController gameScreenController) {
        super(gameScreenController, stage);

        initialize();
    }

    @Override
    void initialize() {
        stage.addActor(settingsMenuGroup);
        settingsMenuGroup.addActor(backgroundImage);
        backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
        createExitPauseMenuButton();
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

    private void createExitPauseMenuButton() {
        TextureRegion exitButtonTextureRegion = new TextureRegion(new Texture("GameScreen/overlays/ExitPauseMenuButton.png"));
        TextureRegionDrawable exitButtonRegDrawable = new TextureRegionDrawable(exitButtonTextureRegion);
        ImageButton exitButton = new ImageButton(exitButtonRegDrawable); //Set the button up
        settingsMenuGroup.addActor(exitButton);
        exitButton.setPosition(backgroundImage.getX() + backgroundImage.getWidth() - exitButton.getWidth() - 20, backgroundImage.getY() + backgroundImage.getHeight() - exitButton.getHeight() - 20);
        gameScreenController.addExitPauseMenuButtonClickListener(exitButton);
    }
}
