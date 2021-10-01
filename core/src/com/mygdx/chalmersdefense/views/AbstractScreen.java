package com.mygdx.chalmersdefense.views;

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

/**
 * @author Daniel Persson
 *
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: Created Hashmap with sprites
 */
public abstract class AbstractScreen extends Stage implements Screen {

    final HashMap<String, Sprite> spriteMap = new HashMap<>();
    final HashMap<String, Sprite> largeSpriteMap = new HashMap<>();

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
        spriteMap.put("IT-Smurf1", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf1.png")));
        spriteMap.put("Chemist1", new Sprite(new Texture("towers/Chemist/Chemist1.png")));
        spriteMap.put("Hackerman1", new Sprite(new Texture("towers/Hackerman/Hackerman1.png")));
        spriteMap.put("Electroman1", new Sprite(new Texture("towers/Electroman/Electroman1.png")));
        spriteMap.put("Mechoman1", new Sprite(new Texture("towers/Mechoman/Mechoman1.png")));
        spriteMap.put("MechMiniTower1", new Sprite(new Texture("towers/MechMiniTower/MechMiniTower1.png")));
        spriteMap.put("Economist1", new Sprite(new Texture("towers/Economist/Economist1.png")));


        spriteMap.put("IT-Smurf2", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf2.png")));
        spriteMap.put("Chemist2", new Sprite(new Texture("towers/Chemist/Chemist2.png")));
        spriteMap.put("Hackerman2", new Sprite(new Texture("towers/Hackerman/Hackerman2.png")));
        spriteMap.put("Electroman2", new Sprite(new Texture("towers/Electroman/Electroman2.png")));
        spriteMap.put("Mechoman2", new Sprite(new Texture("towers/Mechoman/Mechoman1.png")));
        spriteMap.put("Economist2", new Sprite(new Texture("towers/Economist/Economist2.png")));

        spriteMap.put("IT-Smurf3", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf3.png")));
        spriteMap.put("Chemist3", new Sprite(new Texture("towers/Chemist/Chemist3.png")));
        spriteMap.put("Hackerman3", new Sprite(new Texture("towers/Hackerman/Hackerman3.png")));
        spriteMap.put("Electroman3", new Sprite(new Texture("towers/Electroman/Electroman3.png")));
        spriteMap.put("Mechoman3", new Sprite(new Texture("towers/Mechoman/Mechoman1.png")));
        spriteMap.put("Economist3", new Sprite(new Texture("towers/Economist/Economist3.png")));

        spriteMap.put("virus1", new Sprite(new Texture("viruses/virus1Hp.png")));
        spriteMap.put("virus2", new Sprite(new Texture("viruses/virus2Hp.png")));
        spriteMap.put("virus3", new Sprite(new Texture("viruses/virus3Hp.png")));
        spriteMap.put("virus4", new Sprite(new Texture("viruses/virus4Hp.png")));
        spriteMap.put("virus5", new Sprite(new Texture("viruses/virus5Hp.png")));

        spriteMap.put("bullet", new Sprite(new Texture("projectiles/bullet.png")));
        spriteMap.put("smurfProjectile1", new Sprite(new Texture("projectiles/smurfProjectile1.png")));
        spriteMap.put("smurfProjectile2", new Sprite(new Texture("projectiles/smurfProjectile2.png")));
        spriteMap.put("electroProjectile1", new Sprite(new Texture("projectiles/electroProjectile1.png")));
        spriteMap.put("electroProjectile2", new Sprite(new Texture("projectiles/electroProjectile2.png")));
        spriteMap.put("chemistProjectile1", new Sprite(new Texture("projectiles/chemistProjectile1.png")));
        spriteMap.put("chemistAcid1", new Sprite(new Texture("projectiles/chemistAcid1.png")));
        spriteMap.put("mechaProjectile1", new Sprite(new Texture("projectiles/mechaProjectile1.png")));
        spriteMap.put("money", new Sprite(new Texture("projectiles/money.png")));



        largeSpriteMap.put("IT-Smurf1Large", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf1Large.png")));
        largeSpriteMap.put("Chemist1Large", new Sprite(new Texture("towers/Chemist/Chemist1Large.png")));
        largeSpriteMap.put("Hackerman1Large", new Sprite(new Texture("towers/Hackerman/Hackerman1Large.png")));
        largeSpriteMap.put("Electroman1Large", new Sprite(new Texture("towers/Electroman/Electroman1Large.png")));
        largeSpriteMap.put("Mechoman1Large", new Sprite(new Texture("towers/Mechoman/Mechoman1Large.png")));
        largeSpriteMap.put("Economist1Large", new Sprite(new Texture("towers/Economist/Economist1Large.png")));

        largeSpriteMap.put("IT-Smurf2Large", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf2Large.png")));
        largeSpriteMap.put("Chemist2Large", new Sprite(new Texture("towers/Chemist/Chemist2Large.png")));
        largeSpriteMap.put("Hackerman2Large", new Sprite(new Texture("towers/Hackerman/Hackerman2Large.png")));
        largeSpriteMap.put("Electroman2Large", new Sprite(new Texture("towers/Electroman/Electroman2Large.png")));
        largeSpriteMap.put("Mechoman2Large", new Sprite(new Texture("towers/Mechoman/Mechoman2Large.png")));
        largeSpriteMap.put("Economist2Large", new Sprite(new Texture("towers/Economist/Economist2Large.png")));

        largeSpriteMap.put("IT-Smurf3Large", new Sprite(new Texture("towers/IT-Smurf/IT-Smurf3Large.png")));
        largeSpriteMap.put("Chemist3Large", new Sprite(new Texture("towers/Chemist/Chemist3Large.png")));
        largeSpriteMap.put("Hackerman3Large", new Sprite(new Texture("towers/Hackerman/Hackerman3Large.png")));
        largeSpriteMap.put("Electroman3Large", new Sprite(new Texture("towers/Electroman/Electroman3Large.png")));
        largeSpriteMap.put("Mechoman3Large", new Sprite(new Texture("towers/Mechoman/Mechoman3Large.png")));
        largeSpriteMap.put("Economist3Large", new Sprite(new Texture("towers/Economist/Economist3Large.png")));
    }
}

