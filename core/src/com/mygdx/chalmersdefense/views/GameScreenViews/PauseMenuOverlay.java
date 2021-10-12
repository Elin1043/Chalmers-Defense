package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

/**
 * @author Jenny Carlsson
 * @author Daniel Persson
 *
 * A class for representing the pause menu in GameScreen
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
                    backgroundImage.getY() + backgroundImage.getHeight() - (button.getHeight() + 10) * (i + 1));

            Label buttonLabel = new Label(buttonTexts[i], FontFactory.getLabelStyle20Black());
            button.addActor(buttonLabel);
            buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2);
            gameScreenController.addPauseMenuClickListeners(button, buttonTexts[i]);
        }
    }
}
