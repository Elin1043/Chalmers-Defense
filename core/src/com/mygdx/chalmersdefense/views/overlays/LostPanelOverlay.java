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
import com.mygdx.chalmersdefense.controllers.overlayControllers.LostPanelOverlayController;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;

/**
 * @author Daniel Persson
 * A class used to render an overlay when a player lose the game
 */
final class LostPanelOverlay extends AbstractOverlay {
    private final LostPanelOverlayController lostPanelOverlayController; // Controller used for adding listeners

    private final Group lostPanelGroup = new Group(); // Group to add all actors to

    private final Image backgroundImage = new Image(new Texture("GameScreen/LostPanelBackgroundImage.png")); // Background image of overlay

    /**
     * Sets up class and passes abstractOverlayController to super constructor
     * @param abstractOverlayController reference to common controller
     * @param lostPanelOverlayController reference to controller for lost panel
     */
    LostPanelOverlay(AbstractOverlayController abstractOverlayController, LostPanelOverlayController lostPanelOverlayController) {
        super(abstractOverlayController);
        this.lostPanelOverlayController = lostPanelOverlayController;
    }

    @Override
    void initialize() {
        stage.addActor(lostPanelGroup);
        lostPanelGroup.addActor(backgroundImage);

        // Set position of background
        backgroundImage.setPosition(stage.getWidth() / 2 - backgroundImage.getWidth() / 2, stage.getHeight() / 2 - backgroundImage.getHeight() / 2);

        // Creates labels
        Label title = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
        Label mainText = new Label("The evil Corona viruses have won. Chalmers have fallen and will now be the center of the new wave of even more powerful virus variants.", FontFactory.getLabelStyle18Black());
        setupWinOrLoseOverlayLabels(backgroundImage, title, mainText);
        lostPanelGroup.addActor(title);
        lostPanelGroup.addActor(mainText);

        // Button skin
        TextureAtlas lostButtonTexture = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "lostGameButtonSkin/lostGameButtonSkin.atlas")); // Load atlas file from skin
        Skin lostButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "lostGameButtonSkin/lostGameButtonSkin.json"), lostButtonTexture); // Create skin object

        // Create first button
        createMainMenuButton(lostButtonSkin);

        // Create second button
        createTryAgainButton(lostButtonSkin);

        lostPanelGroup.setVisible(false);
    }


    @Override
    public void render() {
        super.render();
        lostPanelGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        lostPanelGroup.setVisible(false);
    }

    /**
     * Create main menu button
     */
    private void createMainMenuButton(Skin lostButtonSkin) {
        Button mainMenuButton = new Button(lostButtonSkin);
        Label mainMenuButtonText = new Label(" Main menu ", FontFactory.getLabelStyle24BlackSemiBold());
        setupWinAndLostOverlayButtons(backgroundImage, mainMenuButton, mainMenuButtonText, 1);
        lostPanelGroup.addActor(mainMenuButton);
        abstractOverlayController.addMainMenuClickListener(mainMenuButton);
    }

    /**
     * Create try again button
     */
    private void createTryAgainButton(Skin lostButtonSkin) {
        Button tryAgainButton = new Button(lostButtonSkin);
        Label tryAgainButtonText = new Label("Try again", FontFactory.getLabelStyle24BlackSemiBold());
        setupWinAndLostOverlayButtons(backgroundImage, tryAgainButton, tryAgainButtonText, 2);
        lostPanelGroup.addActor(tryAgainButton);
        lostPanelOverlayController.addLostPanelTryAgainClickListener(tryAgainButton);
    }
}
