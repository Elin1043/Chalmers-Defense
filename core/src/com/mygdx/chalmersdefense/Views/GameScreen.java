package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Models.Tower;
import com.mygdx.chalmersdefense.TowerFactory;
import com.mygdx.chalmersdefense.Vectors;

public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;

    private Object TowerFactory;


    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        TowerFactory factory= new TowerFactory();
        Tower smurf = factory.CreateSmurf(0,0);
        Tower chemist = factory.CreateChemist(100,100);
        Tower electro = factory.CreateElectro(300,300);

        chemist.getSprite().draw(batch);
        smurf.getSprite().draw(batch);
        electro.getSprite().draw(batch);



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
