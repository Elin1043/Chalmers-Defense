package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Views.GameScreen;

public class RightSidePanelController {
    private Stage stage;

    public RightSidePanelController(Stage stage) {
        this.stage = stage;
        createPlayButton(stage);
    }

    private void createPlayButton(Stage stage) {
        Texture playButtonTexture = new Texture(Gdx.files.internal("playButton.png"));
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        ImageButton playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(1500, 20);

        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.

        playButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Call model.
            }
        });
    }
}
