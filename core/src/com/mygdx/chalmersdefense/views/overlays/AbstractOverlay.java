package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

    public AbstractOverlay(AbstractOverlayController abstractOverlayController) {
        this.abstractOverlayController = abstractOverlayController;

        Gdx.input.setInputProcessor(multiplexer);
    }

    /**
     * Initialize the ScreenOverlay
     */
    protected abstract void initialize();

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
    protected void drawTransparentBackground() {
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(20 / 255F, 20 / 255F, 20 / 255F, 0.6F));
        shapeRenderer.rect(0, 0, stage.getWidth(), stage.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }

    protected ImageButton createExitPauseMenuButton(Group group, Image backgroundImage) {
        TextureRegion exitButtonTextureRegion = new TextureRegion(new Texture("GameScreen/overlays/ExitCrossButton.png"));
        TextureRegionDrawable exitButtonRegDrawable = new TextureRegionDrawable(exitButtonTextureRegion);
        ImageButton exitButton = new ImageButton(exitButtonRegDrawable); //Set the button up
        group.addActor(exitButton);
        exitButton.setPosition(backgroundImage.getX() + backgroundImage.getWidth() - exitButton.getWidth() - 20, backgroundImage.getY() + backgroundImage.getHeight() - exitButton.getHeight() - 20);
        return exitButton;
    }
}
