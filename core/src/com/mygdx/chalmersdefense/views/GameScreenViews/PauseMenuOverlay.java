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

/**
 * @author Jenny Carlsson
 * @author Daniel Persson
 *
 * A class for representing the pause menu in GameScreen
 *
 * 2021-10-12 Modified by Jenny Carlsson and Daniel Persson: Added pause menu exit button
 */
public class PauseMenuOverlay extends AbstractOverlay {

    private final Group pauseMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/PauseMenuBackgroundImage.png"));


    private final TextureAtlas pauseMenuButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/pauseMenuButtonSkin/PauseMenuButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseMenuButtonSkin = new Skin(Gdx.files.internal("buttons/pauseMenuButtonSkin/PauseMenuButtonSkin.json"), pauseMenuButtonTexture); // Create skin object

    public PauseMenuOverlay(Stage stage, GameScreenController gameScreenController) {
        super(gameScreenController, stage);

        initialize();
    }

    @Override
    void initialize() {
        stage.addActor(pauseMenuGroup);
        pauseMenuGroup.addActor(backgroundImage);
        backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
        createButtons();
        createExitPauseMenuButton();
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        stage.act();
        stage.draw();
        pauseMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        pauseMenuGroup.setVisible(false);
    }

    private void createButtons() {
        String[] buttonTexts = {"Continue", "Settings", "Quit"};

        for (int i = 0; i < buttonTexts.length; i++) {
            Button button = new Button(pauseMenuButtonSkin);
            pauseMenuGroup.addActor(button);
            button.setPosition(
                    backgroundImage.getX() + backgroundImage.getWidth() / 2 - button.getWidth() / 2,
                    backgroundImage.getY() + backgroundImage.getHeight() -38 - (button.getHeight() + 10) * (i + 1));

            Label buttonLabel = new Label(buttonTexts[i], FontFactory.getLabelStyle20Black());
            button.addActor(buttonLabel);
            buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2);
            gameScreenController.addPauseMenuClickListeners(button, buttonTexts[i]);
        }
    }

    private void createExitPauseMenuButton() {
        TextureRegion exitButtonTextureRegion = new TextureRegion(new Texture("GameScreen/overlays/ExitCrossButton.png"));
        TextureRegionDrawable exitButtonRegDrawable = new TextureRegionDrawable(exitButtonTextureRegion);
        ImageButton exitButton = new ImageButton(exitButtonRegDrawable); //Set the button up
        pauseMenuGroup.addActor(exitButton);
        exitButton.setPosition(backgroundImage.getX() + backgroundImage.getWidth() - exitButton.getWidth() - 20, backgroundImage.getY() + backgroundImage.getHeight() - exitButton.getHeight() - 20);
        gameScreenController.addExitPauseMenuButtonClickListener(exitButton);
    }

}
