package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlayControllers.AbstractOverlayController;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;

/**
 * @author Jenny Carlsson
 * A class to display info overlay
 */
final class InfoOverlay extends AbstractOverlay {

    private final Group infoGroup = new Group(); // Group to add all actors to

    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/InfoBackgroundImage.png")); // Background image for overlay

    /**
     * Sets up class and passes abstractOverlayController to super constructor
     * @param abstractOverlayController reference to common controller
     */
    public InfoOverlay(AbstractOverlayController abstractOverlayController) {
        super(abstractOverlayController);
    }

    @Override
    protected void initialize() {
        stage.addActor(infoGroup);
        if (!infoGroup.hasChildren()) {
            infoGroup.addActor(backgroundImage);

            backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
            Button exitButton = createExitPauseMenuButton(infoGroup, backgroundImage);
            abstractOverlayController.addExitOverlayButtonClickListener(exitButton);

            createLabels();
        }
    }

    /**
     * Method for creating labels
     */
    private void createLabels() {
        Label infoTitleLabel = new Label("Game info", FontFactory.getLabelStyle36BlackBold());
        infoGroup.addActor(infoTitleLabel);
        infoTitleLabel.setPosition(backgroundImage.getX() + (backgroundImage.getWidth() / 2 - infoTitleLabel.getWidth() / 2), backgroundImage.getY() + 525);

        Label gameTipsLabel = new Label("Game Tips:", FontFactory.getLabelStyle24BlackSemiBold());
        infoGroup.addActor(gameTipsLabel);
        gameTipsLabel.setPosition(backgroundImage.getX() + 60, backgroundImage.getY() + 355);

        Label shortcutsLabel = new Label("Shortcuts:", FontFactory.getLabelStyle24BlackSemiBold());
        infoGroup.addActor(shortcutsLabel);
        shortcutsLabel.setPosition(backgroundImage.getX() + 405, backgroundImage.getY() + 355);

        Label createdByLabel = new Label("Created by: Jenny Carlsson, Daniel Persson, Elin Forsberg and Joel Båtsman Hilmersson", FontFactory.getLabelStyle15Black());
        infoGroup.addActor(createdByLabel);
        createdByLabel.setPosition(backgroundImage.getX() + 80, backgroundImage.getY() + 15);

        Label textLabel = new Label("Chalmers Defence is a game of strategy, with a graphical user interface inspired by Chalmers University of Technology. Gather your forces and defend the campus!!!  ", FontFactory.getLabelStyle24Black());
        infoGroup.addActor(textLabel);
        textLabel.setPosition(backgroundImage.getX() + 40 , backgroundImage.getY() + 450);
        textLabel.setAlignment(Align.center);
        textLabel.setWrap(true);
        textLabel.setWidth(700);

        Label tipListLabel = new Label("Place the towers thoughtfully and develop strategies to defeat the enemies.\n\nUse the economy tower to increase money income.\n\nUpgrade your towers to make them even stronger.\n\nUse power ups to momentarily boost your game.", FontFactory.getLabelStyle18Black());
        infoGroup.addActor(tipListLabel);
        tipListLabel.setPosition(backgroundImage.getX() + 60, backgroundImage.getY() + 140);
        tipListLabel.setWrap(true);
        tipListLabel.setWidth(300);

        Label shortkeyListLabel = new Label("F11: Toggle fullscreen \n\nESC:  Pause game \nSPACE:  Start round/speed up \n\nM: Activate \"Masked up\" power up \nV: Activate \"Vaccinated\" power up \nC: Activate \"Clean hands\" power up \n\nNumbers 1-6: Place tower where the mouse is (1 is top left, 6 is bottom right) " , FontFactory.getLabelStyle18Black());
        infoGroup.addActor(shortkeyListLabel);
        shortkeyListLabel.setPosition(backgroundImage.getX() + 405, backgroundImage.getY() + 120);
        shortkeyListLabel.setWrap(true);
        shortkeyListLabel.setWidth(350);
    }

    @Override
    public void render() {
        super.render();
        infoGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        infoGroup.setVisible(false);
    }

}
