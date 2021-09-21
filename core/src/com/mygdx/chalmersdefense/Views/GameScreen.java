package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.chalmersdefense.Model.Virus;
import com.mygdx.chalmersdefense.Model.VirusFactory;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Controllers.TowerClickListener;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Model.Tower;

import java.util.HashMap;
/**
 * @author
 *
 *
 * @Modified by Elin Forsberg
 *  Added methods and variables to handle placing towers
 */
public class GameScreen extends AbstractScreen implements Screen {

    private RightSidePanelController rightSidePanelController;
    private Button startRoundButton;
    private Model model;


    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Image mapImage;

    private ImageButton smurfButton;
    private ImageButton chemistButton;
    private ImageButton electroButton;
    private ImageButton hackerButton;
    private ImageButton meckButton;
    private ImageButton ecobutton;

    private TowerClickListener towerClickListener;



    private HashMap<Integer, ImageButton> towerButtons = new HashMap<>();


    public GameScreen(Model model, RightSidePanelController rightSidePanelController){
        super();
        this.rightSidePanelController = rightSidePanelController;
        this.model = model;

        mapImage = new Image(new Texture("ClassicMap.png"));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());

        createStartRoundButton();
        towerClickListener = new TowerClickListener(model);

        smurfButton = createTowerButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830, "smurf");
        towerButtons.put(100, smurfButton);
        chemistButton = createTowerButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830, "chemist");
        towerButtons.put(200, chemistButton);
        hackerButton = createTowerButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650, "hacker");
        towerButtons.put(300, hackerButton);
        electroButton = createTowerButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650, "electro");
        towerButtons.put(400, electroButton);
        meckButton = createTowerButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470, "meck");
        towerButtons.put(500, meckButton);
        ecobutton = createTowerButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470, "eco");
        towerButtons.put(600, ecobutton);

        addTowerButtonListener();

    }

    @Override
    public void buildStage() {
        addActor(smurfButton);
        addActor(chemistButton);
        addActor(hackerButton);
        addActor(electroButton);
        addActor(meckButton);
        addActor(ecobutton);

        addActor(mapImage);
    }

    @Override
    public void render(float delta) {

        super.render(Gdx.graphics.getDeltaTime());

        renderTowers();
        checkAffordableTowers();


        renderViruses();


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.getVirusSpawner().spawnRound(1);
        }


    }

    private void renderViruses() {
        super.batch.begin();

        try {
            for (Virus virus: model.getViruses()) {     // Om den lägger till ett virus exakt samtidigt blir det inte bra
                virus.getSprite().draw(super.batch);
            }

        } catch (ConcurrentModificationException e) {
            System.out.println("FAIL when rendering Virus");

            for (Virus virus: model.getViruses()) {
                virus.getSprite().draw(super.batch);
            }
        }

        super.batch.end();
    }


    private void addTowerButtonListener() {
        rightSidePanelController.addTowerButtonListener(smurfButton);
        rightSidePanelController.addTowerButtonListener(chemistButton);
        rightSidePanelController.addTowerButtonListener(hackerButton);
        rightSidePanelController.addTowerButtonListener(electroButton);
        rightSidePanelController.addTowerButtonListener(meckButton);
        rightSidePanelController.addTowerButtonListener(ecobutton);
    }


    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1600, 20);

        this.addActor(startRoundButton); //Add the button to the stage to perform rendering and take input.
        rightSidePanelController.addStartButtonListener(startRoundButton);
    }

    private ImageButton createTowerButtons(Texture texture, int x, int y, String name) {
        TextureRegion towerButtonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable towerButtonRegDrawable = new TextureRegionDrawable(towerButtonTextureRegion);
        ImageButton towerButton = new ImageButton(towerButtonRegDrawable); //Set the button up
        towerButton.setPosition(x, y);
        towerButton.setName(name);


        this.addActor(towerButton); //Add the button to the stage to perform rendering and take input.
        return towerButton;

    }


    private ImageButton createInvisButtonsOnTower(Tower tower,float x, float y) {
        Texture invisButtonTexture = tower.getSprite().getTexture();
        TextureRegion invisButtonTextureRegion = new TextureRegion(invisButtonTexture);
        TextureRegionDrawable invisTexRegDrawable = new TextureRegionDrawable(invisButtonTextureRegion);
        ImageButton invisButton = new ImageButton(invisTexRegDrawable); //Set the button up
        invisButton.setColor(255,255,255,0);
        invisButton.setSize(tower.getWidth(), tower.getHeight());
        invisButton.setPosition(x,y);


        this.addActor(invisButton);
        return invisButton;
    }

    private void checkAffordableTowers() {
        for (Integer i : towerButtons.keySet()) {
            if(model.getMoney() >= i && !towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.enabled);

            }
            else if (model.getMoney()< i && towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.disabled);
                towerButtons.get(i).getImage().setColor(Color.LIGHT_GRAY);


            }
        }
    }

    private void renderTowers() {

        for (Tower tower: model.getTowers()) {


            if(!tower.isPlaced() && !tower.getCollision()){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();
            }
            else if(!tower.isPlaced() && tower.getCollision()){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.RED);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();

            }

            else if(tower.isPlaced() && !tower.getGotButton()){
                ImageButton btn = createInvisButtonsOnTower(tower, tower.getPosX(), tower.getPosY());
                btn.addListener(towerClickListener);
                tower.setGotButton(true);
            }


            super.batch.begin();
            tower.getSprite().draw(super.batch);
            super.batch.end();

        }


    }


}
