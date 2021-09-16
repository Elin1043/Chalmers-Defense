package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Model.Model;

public class MainScreenController {
    private ImageButton playButton;
    private Model model;
    private Stage stage;

    public MainScreenController(Model model) {
        this.model = model;
    }

    public void addStage(Stage stage) {
        this.stage = stage;
        createPlayButton();
    }

    private void createPlayButton(){
        Texture playButtonTexture = new Texture(Gdx.files.internal("playButton.png"));
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(832, 20);

        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.playButtonAction();
            }
        });
    }
}
