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

public class PauseMenuOverlay extends AbstractOverlay {

    private final Group pauseMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/PauseMenuBackgroundImage.png"));

    private final TextureAtlas pauseMenuButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/pauseMenuButtonSkin/PauseMenuButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseMenuButtonSkin = new Skin(Gdx.files.internal("buttons/pauseMenuButtonSkin/PauseMenuButtonSkin.json"), pauseMenuButtonTexture); // Create skin object

    public PauseMenuOverlay(GameScreenController gameScreenController, Stage stage) {
        super(gameScreenController, stage);

        initialize();
    }

    @Override
    void initialize() {
        stage.addActor(pauseMenuGroup);
        pauseMenuGroup.addActor(backgroundImage);
        createButtons();
    }

    @Override
    public void render() {
        pauseMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        pauseMenuGroup.setVisible(false);
    }

    private void createButtons() {
        String[] buttonTexts = {"Continue", "Settings", "Quit"};

        for (String buttonText : buttonTexts) {
            Button button = new Button(pauseMenuButtonSkin);
            pauseMenuGroup.addActor(button);

            Label buttonLabel = new Label(buttonText, FontFactory.getLabelStyle20Black());
            pauseMenuGroup.addActor(buttonLabel);
            buttonLabel.setPosition(button.getX() + button.getWidth()/2 - buttonLabel.getWidth()/2, button.getY() + button.getHeight()/2 - buttonLabel.getHeight()/2);
        }
    }
}
