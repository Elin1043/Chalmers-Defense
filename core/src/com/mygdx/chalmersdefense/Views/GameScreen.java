package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Models.Tower;
import com.mygdx.chalmersdefense.TowerFactory;

import java.util.ArrayList;

import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;
    TowerFactory factory = new TowerFactory();
    Tower chemist = factory.CreateChemist(0, 0);
    Tower smurf = factory.CreateSmurf(300, 300);
    Tower electro = factory.CreateElectro(300, 300);

    Tower newTower;


    ImageButton smurfButton;
    ImageButton chemistButton;
    ArrayList<Tower> towersList = new ArrayList<>();
    ArrayList<ImageButton> towerButtons = new ArrayList<>();
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private Circle circle;

    private boolean collision = false;
    private boolean falseLetGo = false;




    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();


        stage = new Stage(viewport); //Set up a stage for the ui

        chemistButton = createTowerButtons(chemist.getSprite().getTexture(), 1800, 900);
        smurfButton = createTowerButtons(smurf.getSprite().getTexture(), 1650, 900);



    }

    private ImageButton createInvisButtons(Tower tower,float x, float y){
        Texture playButtonTexture = tower.getSprite().getTexture();
        TextureRegion playButtonTextureRegion = new TextureRegion(playButtonTexture);
        TextureRegionDrawable playTexRegDrawable = new TextureRegionDrawable(playButtonTextureRegion);
        ImageButton invisButton = new ImageButton(playTexRegDrawable); //Set the button up
        invisButton.setColor(255,255,255,0);
        invisButton.setSize(tower.getWidth(), tower.getHeight());
        invisButton.setPosition(x,y);


        stage.addActor(invisButton); //Add the button to the stage to perform rendering and take input.
        return invisButton;
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


    public void addListenerToButton(final Tower tower, final ImageButton button){

        final String towerName = tower.getName();
        button.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {

                switch(towerName){
                    case "SmurfTower":
                         smurf = factory.CreateSmurf((int)button.getX(), (int)button.getY());
                         newTower = smurf;
                        break;
                    case "ChemistTower":
                        chemist = factory.CreateChemist((int)button.getX(), (int)button.getY());
                        newTower = chemist;
                        break;
                    case "ElectroTower":
                        electro = factory.CreateElectro((int)button.getX(), (int)button.getY());
                        newTower = electro;
                        break;
                    default:
                        return;
                }

                towersList.add(newTower);

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                newTower.getSprite().setPosition( Gdx.input.getX() - button.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) - button.getHeight()/2 );
                newTower.setCircle();

            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                if(!collision){
                    newTower.setPlaced(true);
                    newTower.getSprite().setPosition(Gdx.input.getX() - button.getWidth(),(Gdx.graphics.getHeight()  - Gdx.input.getY()) - button.getHeight()/2 );
                    newTower.setCircle();
                    towerListener(newTower);

                }
                else{
                   falseLetGo = true;
                }
            }
        });
    }

    private void towerListener(Tower tower){
        ImageButton but = createInvisButtons(newTower,newTower.getPosX(), newTower.getPosY());
        towerButtons.add(but);
        but.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Test");
            }
        });
    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        addListenerToButton(smurf, smurfButton);
        addListenerToButton(chemist, chemistButton);

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(towersList != null){
            for (Tower tower: towersList) {
                tower.getSprite().draw(batch);
                tower.setPos(tower.getSprite().getX(), tower.getSprite().getY());

                if(!tower.isPlaced() && !checkCollision(tower)){
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.LIGHT_GRAY);
                    tower.drawRadius(shapeRenderer);
                    shapeRenderer.end();
                    collision = false;

                }
                else if(!tower.isPlaced() && checkCollision(tower)){
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.RED);
                    tower.drawRadius(shapeRenderer);
                    shapeRenderer.end();
                    collision = true;
                }

            }
        }

        if(falseLetGo){
            newTower.getSprite().setPosition( Gdx.input.getX() - newTower.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) - newTower.getHeight()/2 );
            newTower.setCircle();
            if(!collision){
                falseLetGo = false;
                newTower.setPlaced(true);
                towerListener(newTower);
                shapeRenderer.end();
            }
        }


        createRightSidePanel();
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    private boolean checkCollision(Tower tower){
        for(Tower checkTower: towersList){
            if(tower.getCircle().overlaps(checkTower.getCircle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
        }
        return false;

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
