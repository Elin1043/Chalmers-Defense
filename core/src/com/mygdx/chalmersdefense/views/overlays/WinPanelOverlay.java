package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.overlayControllers.AbstractOverlayController;
import com.mygdx.chalmersdefense.controllers.overlayControllers.WinPanelOverlayController;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player win the game
 */
final class WinPanelOverlay extends AbstractOverlay {
    private final WinPanelOverlayController winPanelOverlayController; // Controller used for adding listeners

    private final Group winPanelGroup = new Group(); // Group to add all actors to

    private final Image backgroundImage = new Image(new Texture("GameScreen/WinPanelBackgroundImage.png")); // Background image of overlay

    /**
     * Sets up class and passes abstractOverlayController to super constructor
     * @param abstractOverlayController reference to common controller
     * @param winPanelOverlayController reference to controller for win panel
     */
    WinPanelOverlay(AbstractOverlayController abstractOverlayController, WinPanelOverlayController winPanelOverlayController) {
        super(abstractOverlayController);
        this.winPanelOverlayController = winPanelOverlayController;
    }

    @Override
    void initialize() {
        stage.addActor(winPanelGroup);
        winPanelGroup.addActor(backgroundImage);

        // Set position of background
        backgroundImage.setPosition(stage.getWidth() / 2 - backgroundImage.getWidth() / 2, stage.getHeight() / 2 - backgroundImage.getHeight() / 2);

        // Create labels
        Label title = new Label("YOU WON!", FontFactory.getLabelStyle36BlackBold());
        Label mainText = new Label("You have defended Chalmers from the evil Corona viruses. \n" +
                "You can now continue to play if you want to decimate the viruses more or return to the main menu ", FontFactory.getLabelStyle18Black());
        setupWinOrLoseOverlayLabels(backgroundImage, title, mainText);
        winPanelGroup.addActor(title);
        winPanelGroup.addActor(mainText);

        TextureAtlas winButtonTexture = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "winGameButtonSkin/winGameButtonSkin.atlas")); // Load atlas file from skin
        Skin winButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "winGameButtonSkin/winGameButtonSkin.json"), winButtonTexture); // Create skin object

        // Create main menu button
        createMainMenuButton(winButtonSkin);

        // Create continue button
        createContinueButton(winButtonSkin);

        winPanelGroup.setVisible(false);
    }

    @Override
    public void render() {
        super.render();
        winPanelGroup.setVisible(true);
    }

   @Override
    public void hideOverlay() {
        winPanelGroup.setVisible(false);
    }


    /**
     * Create continue button
     */
    private void createMainMenuButton(Skin winButtonSkin) {
        Button mainMenuButton = new Button(winButtonSkin);
        Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());
        setupWinAndLostOverlayButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1);
        winPanelGroup.addActor(mainMenuButton);
        abstractOverlayController.addMainMenuClickListener(mainMenuButton);
    }

    /**
     * Create continue button
     */
    private void createContinueButton(Skin winButtonSkin) {
        Button continueButton = new Button(winButtonSkin);
        Label continueButtonText = new Label("Continue", FontFactory.getLabelStyle24BlackSemiBold());
        setupWinAndLostOverlayButtons(backgroundImage, continueButton, continueButtonText, 2);
        winPanelGroup.addActor(continueButton);
        winPanelOverlayController.addWinPanelContinueClickListener(continueButton);
    }
}
