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
import com.mygdx.chalmersdefense.controllers.overlays.LostPanelOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player lose the game
 */
public class LostPanelOverlay extends AbstractOverlay {
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final LostPanelOverlayController lostPanelOverlayController;

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

    public LostPanelOverlay(LostPanelOverlayController lostPanelOverlayController) {
        this.lostPanelOverlayController = lostPanelOverlayController;
    }

    @Override
    void initialize() {
        stage.addActor(lostPanelGroup);
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

        createButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1);
        createButtons(backgroundImage, tryAgainButton, tryAgainButtonText, 2);

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

    @Override
    public void render() {
        Gdx.input.setInputProcessor(stage);

        drawTransparentBackground();

        stage.act();
        stage.draw();

        lostPanelGroup.setVisible(true);

    }

    @Override
    public void hideOverlay() {
        lostPanelGroup.setVisible(false);
    }

    /**
     * Setup a new button
     * @param backgroundImage of the new button
     * @param button the button to be setup
     * @param buttonLabel of the new button
     * @param buttonNr of the new button
     */
    private void createButtons(Image backgroundImage, Button button, Label buttonLabel, int buttonNr) {
        // Offset used to place button in center of left or right part.
        float offsetMulX = buttonNr == 1 ? 1 / 4f : 3/ 4f;
        if (buttonNr == 1) {
            lostPanelOverlayController.addMainMenuClickListener(button);
        } else {
            lostPanelOverlayController.addLostPanelTryAgainClickListener(button);
        }

        button.setPosition(
                backgroundImage.getX() + (backgroundImage.getWidth() * offsetMulX) - button.getWidth() / 2,
                backgroundImage.getY() + 65);
        buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2 + 5);
    }
}
