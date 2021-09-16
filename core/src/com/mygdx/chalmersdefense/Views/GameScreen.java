package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Models.Tower;
import com.mygdx.chalmersdefense.TowerFactory;
import com.mygdx.chalmersdefense.Vectors;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;
    TowerFactory factory = new TowerFactory();
    Tower smurf = factory.CreateSmurf(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
    Tower chemist = factory.CreateChemist(200, 200);
    Tower electro = factory.CreateElectro(500, 500);
    Stage stage;
    Boolean held = false;
    ImageButton smurfButton;
    ImageButton chemistButton;
    ArrayList<Tower> towersList = new ArrayList<>();




    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;
        stage = new Stage(viewport); //Set up a stage for the ui

    }

    private ImageButton createTowerButtons(Texture texture, int x, int y){
        Texture playButtonTexture = texture;
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        ImageButton playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(0, 0);

        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.

        return playButton;
    }



    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
        smurfButton = createTowerButtons(smurf.getSprite().getTexture(), 0, 0);
        chemistButton = createTowerButtons(chemist.getSprite().getTexture(), 0, 0);


        smurfButton.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                smurf = factory.CreateSmurf((int)(x - smurfButton.getWidth() / 2),(int)(y - smurfButton.getHeight() / 2));
                towersList.add(smurf);

            }
        });

        chemistButton.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                chemist = factory.CreateChemist((int)(x - chemistButton.getWidth() / 2),(int)(y - chemistButton.getHeight() / 2));
                towersList.add(chemist);

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                chemist.getSprite().setPosition(x - chemist.getSprite().getWidth() / 2,y - chemist.getSprite().getHeight() / 2 );
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                chemist.getSprite().setPosition(x - chemist.getSprite().getWidth() / 2,y - chemist.getSprite().getHeight() / 2 );
            }
        });


    }






    @Override
    public void render(float delta) {
        if(towersList != null){
            for (Tower tower: towersList) {
                tower.getSprite().draw(batch);
                tower.setPos((int)tower.getSprite().getX(), (int)tower.getSprite().getY());

            }
        }





        stage.draw();
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
