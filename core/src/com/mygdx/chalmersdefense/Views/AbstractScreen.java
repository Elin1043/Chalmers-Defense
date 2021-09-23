package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;

public abstract class AbstractScreen extends Stage implements Screen {

    final HashMap<Sprite, String> map = new HashMap<>();

    final Batch batch = new SpriteBatch();
    protected AbstractScreen() {
        super(new FitViewport(1920, 1080, new OrthographicCamera(1920, 1080)));
        createSprites();
    }

    public abstract void buildStage();

    @Override
    public void render(float delta) {
        // Clear screen
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(this.getCamera().combined); // Renders based on window pixels and not screen pixels.

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && Gdx.graphics.isFullscreen()) {
            Gdx.graphics.setWindowedMode(1920, 1080);
        }


        super.act(delta);
        super.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override public void hide() {
        Gdx.input.setInputProcessor(null);
    }
    @Override public void pause() {}
    @Override public void resume() {}

    private void createSprites() {
        map.put(new Sprite(new Texture("Towers/Smurf.png")), "SmurfTower1");
        map.put(new Sprite(new Texture("Towers/Chemist.png")), "ChemistTower1");
        map.put(new Sprite(new Texture("Towers/Electro.png")), "HackerTower1");
        map.put(new Sprite(new Texture("Towers/Hacker.png")), "ower1");
        map.put(new Sprite(new Texture("Towers/Meck.png")), "smurfTower1");
        map.put(new Sprite(new Texture("Towers/Eco.png")), "smurfTower1");
    }
}

