package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player lose the game
 */
public class LostPanel extends GameScreenOverlay {
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final TextureAtlas lostButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.atlas")); // Load atlas file from skin
    private final Skin lostButtonSkin = new Skin(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.json"), lostButtonTexture); // Create skin object

    private final Group lostPanelGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/LostPanelBackgroundImage.png"));

    private final Label title = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
    private final Label mainText = new Label("The evil Corona viruses have won. Chalmers have fallen and will now be the center of the new wave of even more powerful virus variants.", FontFactory.getLabelStyle18Black());

    private final Button mainMenuButton = new Button(lostButtonSkin);
    private final Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());

    private final Button tryAgainButton = new Button(lostButtonSkin);
    private final Label tryAgainButtonText = new Label("Try again", FontFactory.getLabelStyle24BlackSemiBold());

    public LostPanel(Stage stage, GameScreenController gameScreenController) {
        super(gameScreenController, stage);

        initialize();
    }

    /**
     * Method used to setup LostPanel GUI components
     */
    void initialize() {
        stage.addActor(lostPanelGroup);
        initializeActors();

        // Set position of background
        backgroundImage.setPosition(stage.getWidth()/2 - WIDTH/2, stage.getHeight()/2 - HEIGHT/2);

        // Set position for main title.
        title.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
            backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainText.setWrap(true);
        mainText.setWidth(720);
        mainText.setAlignment(Align.center);
        mainText.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - 720/2f,
                backgroundImage.getY() + backgroundImage.getHeight() - 150);

        createButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1, "LostPanel");
        createButtons(backgroundImage, tryAgainButton, tryAgainButtonText, 2, "LostPanel");

        lostPanelGroup.setVisible(false);
    }

    private void initializeActors() {
        lostPanelGroup.addActor(backgroundImage);
        lostPanelGroup.addActor(title);
        lostPanelGroup.addActor(mainText);
        lostPanelGroup.addActor(mainMenuButton);
        mainMenuButton.addActor(mainMenuButtonText);
        lostPanelGroup.addActor(tryAgainButton);
        tryAgainButton.addActor(tryAgainButtonText);
    }

    /**
     * Renders lost panel overlay
     */
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        stage.act();
        stage.draw();

        lostPanelGroup.setVisible(true);

    }

    public void hideOverlay() {
        lostPanelGroup.setVisible(false);
    }
}
