package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.chalmersdefense.controllers.overlays.AbstractOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.PauseMenuOverlayController;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;

/**
 * @author Jenny Carlsson
 * @author Daniel Persson
 *
 * A class for representing the pause menu in GameScreen
 *
 * 2021-10-12 Modified by Jenny Carlsson and Daniel Persson: Added pause menu exit button
 */
final class PauseMenuOverlay extends AbstractOverlay {
    private final PauseMenuOverlayController pauseMenuOverlayController;

    private final Group pauseMenuGroup = new Group();
    private final Image backgroundImage = new Image(new Texture("GameScreen/overlays/PauseMenuBackgroundImage.png"));

    private final TextureAtlas pauseMenuButtonTexture = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "pauseMenuButtonSkin/PauseMenuButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseMenuButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "pauseMenuButtonSkin/PauseMenuButtonSkin.json"), pauseMenuButtonTexture); // Create skin object

    /**
     * Sets up class and passes abstractOverlayController to super constructor
     * @param abstractOverlayController reference to common controller
     * @param pauseMenuOverlayController reference to controller for pause menu overlay
     */
    public PauseMenuOverlay(AbstractOverlayController abstractOverlayController, PauseMenuOverlayController pauseMenuOverlayController) {
        super(abstractOverlayController);
        this.pauseMenuOverlayController = pauseMenuOverlayController;
    }

    @Override
    protected void initialize() {
        stage.addActor(pauseMenuGroup);
        if (!pauseMenuGroup.hasChildren()) {
            pauseMenuGroup.addActor(backgroundImage);

            backgroundImage.setPosition(stage.getWidth()/2 - backgroundImage.getWidth()/2, stage.getHeight()/2 - backgroundImage.getHeight()/2);
            createButtons();
            Button exitButton = createExitPauseMenuButton(pauseMenuGroup, backgroundImage);
            abstractOverlayController.addExitOverlayButtonClickListener(exitButton);
        }
    }

    @Override
    public void render() {
        super.render();
        pauseMenuGroup.setVisible(true);
    }

    @Override
    public void hideOverlay() {
        pauseMenuGroup.setVisible(false);
    }

    /**
     * Creates buttons
     */
    private void createButtons() {
        String[] buttonTexts = {"Continue", "Settings", "Quit"};

        for (int i = 0; i < buttonTexts.length; i++) {
            Button button = new Button(pauseMenuButtonSkin);
            pauseMenuGroup.addActor(button);
            button.setPosition(
                    backgroundImage.getX() + backgroundImage.getWidth() / 2 - button.getWidth() / 2,
                    backgroundImage.getY() + backgroundImage.getHeight() -38 - (button.getHeight() + 10) * (i + 1));

            Label buttonLabel = new Label(buttonTexts[i], FontFactory.getLabelStyle20Black());
            button.addActor(buttonLabel);
            buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2);
            pauseMenuOverlayController.addPauseMenuClickListeners(button, buttonTexts[i]);
        }
    }

}
