package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Controllers.MainScreenController;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;


public class MainScreen implements Screen {


    Texture img;
    Sprite virus;
    Sprite virus2;
    Sprite virus3;
    Sprite virus4;


    ChalmersDefense game;
    Viewport viewport;
    Batch batch;

    private Stage stage;
    private Texture playButtonTexture;
    private TextureRegion playButtonTextureRegion;
    private TextureRegionDrawable playTexRegDrawable;
    private ImageButton playButton;

    private final Vector2 rotHelper= new Vector2();

    public MainScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;

        stage = new Stage(viewport); //Set up a stage for the ui

        img = new Texture("HomeScreen.png");

        virus = new Sprite(new Texture("corona_virus_low.png"));
        virus2 = new Sprite(new Texture("corona_virus_low.png"));
        virus3 = new Sprite(new Texture("corona_virus_low.png"));
        virus4 = new Sprite(new Texture("corona_virus_low.png"));

        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites
        virus.setScale(0.15F);					// This too

        virus2.setPosition(50, -150);	// This needs to be fixed with later sprites
        virus2.setScale(0.15F);					// This too

        virus3.setPosition(-300, 40);	// This needs to be fixed with later sprites
        virus3.setScale(0.15F);					// This too

        virus4.setPosition(50, 40);	// This needs to be fixed with later sprites
        virus4.setScale(0.15F);					// This too
    }

    public void addMainScreenController(MainScreenController controller) {
        controller.addStage(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
    }

    @Override
    public void render(float delta) {
        batch.draw(img, 0, 0, viewport.getWorldWidth(),viewport.getWorldHeight());

        virus.draw(batch);
        virus2.draw(batch);
        virus3.draw(batch);
        virus4.draw(batch);

        virus.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 600));
        virus2.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 600));
        virus3.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 240));
        virus4.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 240));

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        img.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    //rotToX/Y is the coordinates to be rotated to, orgX/Y is the location to be rotated around
    private float getAngle(int rotToX, int rotToY, int orgX, int orgY){
        rotHelper.set(rotToX - orgX, rotToY - orgY);
        return -rotHelper.angleDeg();	// Negative because it just works then :)
    }
}
