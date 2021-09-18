package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Models.Tower;
import com.mygdx.chalmersdefense.TowerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen implements Screen {

    Viewport viewport;
    Batch batch;
    TowerFactory factory = new TowerFactory();
    Tower chemist = factory.CreateChemist(0, 0);
    Tower smurf = factory.CreateSmurf(0, 0);
    Tower electro = factory.CreateElectro(0, 0);
    Tower hacker = factory.CreateHacker(0,0);
    Tower meck = factory.CreateMeck(0,0);
    Tower eco = factory.CreateEco(0,0);

    Tower newTower;
    BitmapFont font = new BitmapFont();

    ImageButton smurfButton;
    ImageButton chemistButton;
    ImageButton electroButton;
    ImageButton hackerButton;
    ImageButton meckButton;
    ImageButton ecobutton;
    ArrayList<Tower> towersList = new ArrayList<>();

    HashMap<Tower, ImageButton> towerButtons = new HashMap<Tower, ImageButton>();
    private final Stage stage;
    private final ShapeRenderer shapeRenderer;

    private boolean collision = false;
    private boolean falseLetGo = false;

    DragListener dragListener;

    private int money = 300;




    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.viewport = viewport;
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport); //Set up a stage for the ui

        chemistButton = createTowerButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830);
        towerButtons.put(chemist, chemistButton);

        smurfButton = createTowerButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830);
        towerButtons.put(smurf, smurfButton);

        hackerButton = createTowerButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650);
        towerButtons.put(hacker, hackerButton);

        electroButton = createTowerButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650);
        towerButtons.put(electro, electroButton);

        meckButton = createTowerButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470);
        towerButtons.put(meck, meckButton);

        ecobutton = createTowerButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470);
        towerButtons.put(eco, ecobutton);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
        addListenerToButton(smurf, smurfButton);
        addListenerToButton(chemist, chemistButton);
        addListenerToButton(electro, electroButton);

        addListenerToButton(hacker, hackerButton);
        addListenerToButton(meck, meckButton);
        addListenerToButton(eco, ecobutton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        createRightSidePanel();
        checkAffordableTowers();

        if(towersList != null){
            updateTowersOnMap();
        }

        if(falseLetGo){
            handleFalseLetGo();
        }

        font.setColor(new Color(Color.BLACK));
        font.getData().setScale(2,2);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
        font.draw(batch, "Towers", 1720, 1050);
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
        TextureRegion playButtonTextureRegion = new TextureRegion(texture);
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
        button.addListener(dragListener = new DragListener(){
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
                    case "HackerTower":
                        hacker = factory.CreateHacker((int)button.getX(), (int)button.getY());
                        newTower = hacker;
                        break;
                    case "MeckTower":
                        meck = factory.CreateMeck((int)button.getX(), (int)button.getY());
                        newTower = meck;
                        break;
                    case "EcoTower":
                        eco = factory.CreateEco((int)button.getX(), (int)button.getY());
                        newTower = eco;
                        break;
                    default:
                        return;
                }

                towersList.add(newTower);

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                newTower.getSprite().setPosition( Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) - button.getImage().getHeight()/2 );
                newTower.setCircle();

            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                if(!collision){
                    newTower.setPlaced(true);
                    newTower.getSprite().setPosition(Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight()  - Gdx.input.getY()) - button.getImage().getHeight()/2 );
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
        ImageButton but = createInvisButtons(tower,tower.getPosX(), tower.getPosY());
        but.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Test");
            }
        });
    }

    private void checkAffordableTowers(){
        for (Tower i : towerButtons.keySet()) {
            if(money >= i.getCost() && !towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.enabled);

            }
            else if (money < i.getCost() && towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.disabled);
                towerButtons.get(i).getImage().setColor(Color.LIGHT_GRAY);

            }
        }
    }

    private void updateTowersOnMap(){
        for (Tower tower: towersList) {
            tower.getSprite().draw(batch);
            tower.setPos(tower.getSprite().getX(), tower.getSprite().getY());

            if(!tower.isPlaced() && !checkCollisionOfTowers(tower)){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();
                collision = false;

            }
            else if(!tower.isPlaced() && checkCollisionOfTowers(tower)){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.RED);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();
                collision = true;
            }

        }
    }

    private boolean checkCollisionOfTowers(Tower tower){
        for(Tower checkTower: towersList){
            if(tower.getCircle().overlaps(checkTower.getCircle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            else{
                if(!(-20 < (tower.getPosX() - tower.getRange())) || (1580 < (tower.getPosX() + tower.getRange()))){
                    return true;
                }
                if(!(-20 < (tower.getPosY() - tower.getRange())) || (1000 < (tower.getPosY() + tower.getRange()))){
                    return true;
                }
            }
        }
        return false;

    }

    private void handleFalseLetGo(){
        newTower.getSprite().setPosition( Gdx.input.getX() - newTower.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) - newTower.getHeight()/2 );
        newTower.setCircle();
        if(!collision){
            falseLetGo = false;
            newTower.setPlaced(true);
            towerListener(newTower);
            shapeRenderer.end();
        }
    }

    private void createRightSidePanel() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xDDF6FF));
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
        shapeRenderer.dispose();
        stage.dispose();
    }
}
