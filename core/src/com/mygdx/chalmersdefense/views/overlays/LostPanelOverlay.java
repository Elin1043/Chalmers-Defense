package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlays.LostPanelOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;
import com.mygdx.chalmersdefense.views.overlays.AbstractOverlay;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player lose the game
 */
public class LostPanelOverlay extends AbstractOverlay {
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final LostPanelOverlayController lostPanelOverlayController; // Controller used for adding listeners

    private final Group lostPanelGroup = new Group(); // Group to add all actors to

    private final Image backgroundImage = new Image(new Texture("GameScreen/LostPanelBackgroundImage.png")); // Background image of overlay

    public LostPanelOverlay(LostPanelOverlayController lostPanelOverlayController) {
        this.lostPanelOverlayController = lostPanelOverlayController;
    }

    @Override
    protected void initialize() {
        stage.addActor(lostPanelGroup);
        lostPanelGroup.addActor(backgroundImage);

        // Set position of background
        backgroundImage.setPosition(stage.getWidth() / 2 - WIDTH / 2, stage.getHeight() / 2 - HEIGHT / 2);

        createLabels();

        // Button skin
        TextureAtlas lostButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.atlas")); // Load atlas file from skin
        Skin lostButtonSkin = new Skin(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.json"), lostButtonTexture); // Create skin object

        // Create first button
        Button mainMenuButton = new Button(lostButtonSkin);
        Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());
        createButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1);

        // Create second button
        Button tryAgainButton = new Button(lostButtonSkin);
        Label tryAgainButtonText = new Label("Try again", FontFactory.getLabelStyle24BlackSemiBold());
        createButtons(backgroundImage, tryAgainButton, tryAgainButtonText, 2);

        lostPanelGroup.setVisible(false);
    }

    private void createLabels() {
        Label title = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
        Label mainText = new Label("The evil Corona viruses have won. Chalmers have fallen and will now be the center of the new wave of even more powerful virus variants.", FontFactory.getLabelStyle18Black());
        lostPanelGroup.addActor(title);
        lostPanelGroup.addActor(mainText);

        title.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
                backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainText.setWrap(true);
        mainText.setWidth(720);
        mainText.setAlignment(Align.center);
        mainText.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - 720 / 2f,
                backgroundImage.getY() + backgroundImage.getHeight() - 150);
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
        lostPanelGroup.addActor(button);
        button.addActor(buttonLabel);
    }
}
