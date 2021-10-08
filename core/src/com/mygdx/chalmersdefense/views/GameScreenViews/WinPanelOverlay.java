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
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player win the game
 */
public class WinPanelOverlay extends AbstractOverlay {
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final TextureAtlas winButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/winGameButtonSkin/winGameButtonSkin.atlas")); // Load atlas file from skin
    private final Skin winButtonSkin = new Skin(Gdx.files.internal("buttons/winGameButtonSkin/winGameButtonSkin.json"), winButtonTexture); // Create skin object

    private final Group winPanelGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/WinPanelBackgroundImage.png"));

    private final Label title = new Label("YOU WON!", FontFactory.getLabelStyle36BlackBold());
    private final Label mainText = new Label("You have defended Chalmers from the evil Corona viruses. \n" +
            "You can now continue to play if you want to decimate the viruses more or return to the main menu ", FontFactory.getLabelStyle18Black());

    private final Button mainMenuButton = new Button(winButtonSkin);
    private final Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());

    private final Button continueButton = new Button(winButtonSkin);
    private final Label continueButtonText = new Label("Continue", FontFactory.getLabelStyle24BlackSemiBold());

    public WinPanelOverlay(Stage stage, GameScreenController gameScreenController) {
        super(gameScreenController, stage);

        initialize();
    }

    @Override
    void initialize() {
        stage.addActor(winPanelGroup);
        initializeActors();

        // Set position of background
        backgroundImage.setPosition(stage.getWidth() / 2 - WIDTH / 2, stage.getHeight() / 2 - HEIGHT / 2);

        // Set position for main title.
        title.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
                backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainText.setWrap(true);
        mainText.setWidth(720);
        mainText.setAlignment(Align.center);
        mainText.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - 720 / 2f,
                backgroundImage.getY() + backgroundImage.getHeight() - 150);

        createButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1, "WinPanelOverlay");
        createButtons(backgroundImage, continueButton, continueButtonText, 2, "WinPanelOverlay");

        winPanelGroup.setVisible(false);
    }

    private void initializeActors() {
        winPanelGroup.addActor(backgroundImage);
        winPanelGroup.addActor(title);
        winPanelGroup.addActor(mainText);
        winPanelGroup.addActor(mainMenuButton);
        mainMenuButton.addActor(mainMenuButtonText);
        winPanelGroup.addActor(continueButton);
        continueButton.addActor(continueButtonText);
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        stage.act();
        stage.draw();

        winPanelGroup.setVisible(true);
    }

   @Override
    public void hideOverlay() {
        winPanelGroup.setVisible(false);
    }
}
