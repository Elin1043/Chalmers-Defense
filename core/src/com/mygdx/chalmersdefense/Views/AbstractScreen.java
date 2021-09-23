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

    final HashMap<String, Sprite> spriteMap = new HashMap<>();

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
        spriteMap.put("SmurfTower1", new Sprite(new Texture("Towers/Smurf.png")));
        spriteMap.put("ChemistTower1", new Sprite(new Texture("Towers/Chemist.png")));
        spriteMap.put("ElectroTower1", new Sprite(new Texture("Towers/Electro.png")));
        spriteMap.put("HackerTower1", new Sprite(new Texture("Towers/Hacker.png")));
        spriteMap.put("MeckTower1", new Sprite(new Texture("Towers/Meck.png")));
        spriteMap.put("EcoTower1", new Sprite(new Texture("Towers/Eco.png")));
        spriteMap.put("virus1", new Sprite(new Texture("viruses/virus1Hp.png")));
        spriteMap.put("virus2", new Sprite(new Texture("viruses/virus2Hp.png")));
        spriteMap.put("virus3", new Sprite(new Texture("viruses/virus3Hp.png")));
        spriteMap.put("virus4", new Sprite(new Texture("viruses/virus4Hp.png")));
        spriteMap.put("virus5", new Sprite(new Texture("viruses/virus5Hp.png")));

    }
}

