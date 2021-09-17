package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Models.Tower;
import com.mygdx.chalmersdefense.TowerFactory;
import com.mygdx.chalmersdefense.Vectors;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;
    TowerFactory factory = new TowerFactory();
    Tower chemist = factory.CreateChemist(0, 0);
    Tower smurf = factory.CreateSmurf(300, 300);


    ImageButton smurfButton;
    ImageButton chemistButton;
    ArrayList<Tower> towersList = new ArrayList<>();
    private Stage stage;
    private ShapeRenderer shapeRenderer;




    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();


        stage = new Stage(viewport); //Set up a stage for the ui

        chemistButton = createTowerButtons(chemist.getSprite().getTexture(), 1800, 900);
        smurfButton = createTowerButtons(smurf.getSprite().getTexture(), 1650, 900);



    }

    private ImageButton createTowerButtons(Texture texture, int x, int y){
        Texture playButtonTexture = texture;
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        ImageButton playButton = new ImageButton(playTexRegDrawable); //Set the button up
        playButton.setPosition(x, y);


        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.

        return playButton;
        //virus.setPosition(-300, -150);	// This needs to be fixed with later sprites

    }

    public void addRightSidePanelController(RightSidePanelController controller) {
        controller.addStage(stage);
    }



    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        smurfButton.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                smurf = factory.CreateSmurf((int)smurfButton.getX(), (int)smurfButton.getY());
                towersList.add(smurf);

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                smurf.getSprite().setPosition( Gdx.input.getX() - smurfButton.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) - smurfButton.getHeight()/2 );
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                smurf.getSprite().setPosition(Gdx.input.getX() - smurfButton.getWidth(),(Gdx.graphics.getHeight()  - Gdx.input.getY()) - smurfButton.getHeight()/2 );
            }
        });




        chemistButton.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                chemist = factory.CreateChemist((int)chemistButton.getX(), (int)chemistButton.getY());
                towersList.add(chemist);

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                chemist.getSprite().setPosition( Gdx.input.getX() - chemistButton.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) - chemistButton.getHeight()/2 );
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                chemist.getSprite().setPosition(Gdx.input.getX() - chemistButton.getWidth(),(Gdx.graphics.getHeight()  - Gdx.input.getY()) - chemistButton.getHeight()/2 );
            }
        });




    }



    @Override
    public void render(float delta) {
        //virus.draw(batch);

        smurf.getSprite().draw(batch);
        //createBorder(smurf);
        createRightSidePanel();
        if(towersList != null){
            for (Tower tower: towersList) {
                tower.getSprite().draw(batch);

                tower.setPos(tower.getSprite().getX(), tower.getSprite().getY());
                //createBorder(tower);

            }
        }

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    private void createBorder(Tower tower) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xF0FBFF));
        shapeRenderer.rect(tower.getPosX(), tower.getPosY(), tower.getWidth(), tower.getHeight());
        shapeRenderer.end();

    }

    private void createRightSidePanel() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xF0FBFF));
        shapeRenderer.rect(Gdx.graphics.getWidth() - 320, 0, 320, Gdx.graphics.getHeight());
        shapeRenderer.end();

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
        Gdx.input.setInputProcessor(null);
    }
}
