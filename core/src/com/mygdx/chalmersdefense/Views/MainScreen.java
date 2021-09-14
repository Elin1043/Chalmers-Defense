package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.badlogic.gdx.graphics.GL20;



public class MainScreen implements Screen {


    Texture img;
    Sprite virus;
    Sprite virus2;
    Sprite virus3;
    Sprite virus4;


    ChalmersDefense game;
    Camera camera;
    Viewport viewport;
    Batch batch;

    private final Vector2 rotHelper= new Vector2();

    public MainScreen(ChalmersDefense game, Camera camera, Viewport viewport, Batch batch){
        this.game = game;
        this.camera = camera;
        this.viewport = viewport;
        this.batch = batch;

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

        Gdx.graphics.setWindowedMode(1920, 1080); // Sets the width and height of the program window
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        ScreenUtils.clear(255, 255, 255, 1);

        batch.setProjectionMatrix(camera.combined); // Renders based on window pixels and not screen pixels.

        batch.begin();
        batch.draw(img, 0, 0, viewport.getWorldWidth(),viewport.getWorldHeight());

        virus.draw(batch);
        virus2.draw(batch);
        virus3.draw(batch);
        virus4.draw(batch);

        virus.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 600));
        virus2.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 600));
        virus3.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 240));
        virus4.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 240));
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void hide() {

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
