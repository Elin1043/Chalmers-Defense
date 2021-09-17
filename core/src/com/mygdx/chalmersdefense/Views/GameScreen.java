package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Model.SpawnViruses;
import com.mygdx.chalmersdefense.Model.Virus;
import com.mygdx.chalmersdefense.Model.VirusFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;

    List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);

    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;


        //virus.setPosition(-300, -150);	// This needs to be fixed with later sprites
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        for (Virus virus: allViruses) {     // Om den lägger till ett virus exakt samtidigt blir det inte bra
            virus.update();
            virus.getSprite().draw(batch);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            allViruses.add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            virusSpawner.spawnRound(1);
        }
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
