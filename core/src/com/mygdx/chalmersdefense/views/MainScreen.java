package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.MainScreenController;


public class MainScreen extends AbstractScreen {
    Image img;

    private ImageButton playButton;

    private final MainScreenController mainScreenController;

    public MainScreen(MainScreenController mainScreenController){
        super();
        this.mainScreenController = mainScreenController;
        img = new Image(new Texture("HomeScreen.png"));
        createPlayButton();
    }

    private void createPlayButton(){
        Texture playButtonTexture = new Texture(Gdx.files.internal("playButton.png"));
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(832, 20);

        //Add the button to the stage to perform rendering and take input. (WILL BE MOVED)
        mainScreenController.addPlayButtonListener(playButton);

    }

    @Override
    public void buildStage() {
        addActor(img);
        addActor(playButton);
        super.render(Gdx.graphics.getDeltaTime());
    }
}
