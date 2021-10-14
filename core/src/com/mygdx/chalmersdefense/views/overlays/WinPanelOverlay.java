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
import com.mygdx.chalmersdefense.controllers.overlays.WinPanelOverlayController;
import com.mygdx.chalmersdefense.utilities.FontFactory;
import com.mygdx.chalmersdefense.views.overlays.AbstractOverlay;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player win the game
 */
public class WinPanelOverlay extends AbstractOverlay {
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final WinPanelOverlayController winPanelOverlayController; // Controller used for adding listeners

    private final TextureAtlas winButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/winGameButtonSkin/winGameButtonSkin.atlas")); // Load atlas file from skin
    private final Skin winButtonSkin = new Skin(Gdx.files.internal("buttons/winGameButtonSkin/winGameButtonSkin.json"), winButtonTexture); // Create skin object

    private final Group winPanelGroup = new Group(); // Group to att all actors to

    private final Image backgroundImage = new Image(new Texture("GameScreen/WinPanelBackgroundImage.png")); // Background image of overlay

    public WinPanelOverlay(WinPanelOverlayController winPanelOverlayController) {
        this.winPanelOverlayController = winPanelOverlayController;
    }

    @Override
    protected void initialize() {
        stage.addActor(winPanelGroup);
        winPanelGroup.addActor(backgroundImage);

        // Set position of background
        backgroundImage.setPosition(stage.getWidth() / 2 - WIDTH / 2, stage.getHeight() / 2 - HEIGHT / 2);

        // Create labels
        createLabels();

        // Create first button
        Button mainMenuButton = new Button(winButtonSkin);
        Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());
        createButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1);

        // Create second button
        Button continueButton = new Button(winButtonSkin);
        Label continueButtonText = new Label("Continue", FontFactory.getLabelStyle24BlackSemiBold());
        createButtons(backgroundImage, continueButton, continueButtonText, 2);

        winPanelGroup.setVisible(false);
    }

    private void createLabels() {
        Label title = new Label("YOU WON!", FontFactory.getLabelStyle36BlackBold());
        Label mainText = new Label("You have defended Chalmers from the evil Corona viruses. \n" +
                "You can now continue to play if you want to decimate the viruses more or return to the main menu ", FontFactory.getLabelStyle18Black());
        winPanelGroup.addActor(title);
        winPanelGroup.addActor(mainText);

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

        winPanelGroup.setVisible(true);
    }

   @Override
    public void hideOverlay() {
        winPanelGroup.setVisible(false);
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
            winPanelOverlayController.addMainMenuClickListener(button);
        } else {
            winPanelOverlayController.addWinPanelContinueClickListener(button);
        }

        button.setPosition(
                backgroundImage.getX() + (backgroundImage.getWidth() * offsetMulX) - button.getWidth() / 2,
                backgroundImage.getY() + 65);
        buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2 + 5);
        winPanelGroup.addActor(button);
        button.addActor(buttonLabel);
    }
}
