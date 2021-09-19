package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Controllers.MainScreenController;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;


public class MainScreen extends AbstractScreen {
    Image img;

    private ImageButton playButton;

    private final Vector2 rotHelper = new Vector2();
    private MainScreenController mainScreenController;

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

    //rotToX/Y is the coordinates to be rotated to, orgX/Y is the location to be rotated around
    private float getAngle(int rotToX, int rotToY, int orgX, int orgY){
        rotHelper.set(rotToX - orgX, rotToY - orgY);
        return -rotHelper.angleDeg();	// Negative because it just works then :)
    }
}
