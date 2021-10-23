package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.overlays.AbstractOverlayController;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author Daniel Persson
 *
 * Class representing an AbstractOverlay
 */
public abstract class AbstractOverlay {
    final AbstractOverlayController abstractOverlayController;
    Stage stage;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final InputMultiplexer multiplexer = new InputMultiplexer();

    final String buttonsAssetsRoot = "buttons/overlayButtons/";

    public AbstractOverlay(AbstractOverlayController abstractOverlayController) {
        this.abstractOverlayController = abstractOverlayController;

        Gdx.input.setInputProcessor(multiplexer);
    }

    /**
     * Initialize the ScreenOverlay
     */
    abstract void initialize();

    /**
     * Renders overlay panel
     */
    public void render() {
        Gdx.input.setInputProcessor(multiplexer);
        drawTransparentBackground();

        stage.act();
        stage.draw();
    }

    /**
     * Hides overlay
     */
    public abstract void hideOverlay();

    /**
     * Sets overlay stage based on screen stage
     * @param stage the stage of a screen to add overlay to
     */
    public void setStage(Stage stage) {
        if (this.stage == null || !this.stage.equals(stage)) {
            this.stage = new Stage(stage.getViewport());
            multiplexer.addProcessor(this.stage);
            multiplexer.addProcessor(abstractOverlayController);
            initialize();
        }
    }

    /**
     * Generate gray transparent overlay background
     */
    void drawTransparentBackground() {
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(20 / 255F, 20 / 255F, 20 / 255F, 0.6F));
        shapeRenderer.rect(0, 0, stage.getWidth(), stage.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }

    Button createExitPauseMenuButton(Group group, Image backgroundImage) {
        TextureAtlas exitOverlayButtonAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "exitOverlayButtonSkin/ExitOverlayButtonSkin.atlas")); // Load atlas file from skin
        Skin exitOverlayButtonSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "exitOverlayButtonSkin/ExitOverlayButtonSkin.json"), exitOverlayButtonAtlas); // Create skin object
        Button exitButton = new Button(exitOverlayButtonSkin); //Set the button up
        group.addActor(exitButton);
        exitButton.setPosition(backgroundImage.getX() + backgroundImage.getWidth() - exitButton.getWidth() - 20, backgroundImage.getY() + backgroundImage.getHeight() - exitButton.getHeight() - 20);
        return exitButton;
    }

    /**
     * Common setup method for win and lost panel buttons
     * @param backgroundImage to set button position
     * @param button the button to be setup
     * @param buttonLabel of the new button
     * @param buttonNr of the new button (1 or 2)
     */
    void setupWinAndLostOverlayButtons(Image backgroundImage, Button button, Label buttonLabel, int buttonNr) {
        // Offset used to place button in center of left or right part.
        float offsetMulX = buttonNr == 1 ? 1 / 4f : 3/ 4f;

        button.setPosition(
                backgroundImage.getX() + (backgroundImage.getWidth() * offsetMulX) - button.getWidth() / 2,
                backgroundImage.getY() + 65);
        buttonLabel.setPosition(button.getWidth() / 2 - buttonLabel.getWidth() / 2, button.getHeight() / 2 - buttonLabel.getHeight() / 2 + 5);
        button.addActor(buttonLabel);
    }

    /**
     * Common setup method for win and lost panel labels
     * @param backgroundImage to position labels
     * @param title Label to set position on
     * @param mainText Label to set position on
     */
    void setupWinOrLoseOverlayLabels(Image backgroundImage, Label title, Label mainText) {
        title.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
                backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainText.setWrap(true);
        mainText.setWidth(720);
        mainText.setAlignment(Align.center);
        mainText.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - 720 / 2f,
                backgroundImage.getY() + backgroundImage.getHeight() - 170);
    }
}
