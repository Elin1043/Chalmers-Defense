package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.chalmersdefense.Model.Virus;
import com.mygdx.chalmersdefense.Model.VirusFactory;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Controllers.TowerClickListener;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Model.Tower;

import java.util.HashMap;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author
 *
 *
 * @Modified by Elin Forsberg
 *  Added methods and variables to handle placing towers
 */
public class GameScreen extends AbstractScreen implements Screen {

    private final RightSidePanelController rightSidePanelController;
    private final Model model;

    private Image sideBarBackground;
    private Button startRoundButton;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final LabelStyle labelStyleBlack36;
    private final Label towerLabel;
    private final Label powerUpLabel;

    private final Image mapImage;

    private final ImageButton smurfButton;
    private final ImageButton chemistButton;
    private final ImageButton electroButton;
    private final ImageButton hackerButton;
    private final ImageButton meckButton;
    private final ImageButton ecobutton;

    private final TowerClickListener towerClickListener;


    private final HashMap<Integer, ImageButton> towerButtons = new HashMap<>();

    private final Sprite commonUseSprite = new Sprite(new Texture("viruses/virus1Hp.png"));


    public GameScreen(Model model, RightSidePanelController rightSidePanelController){
        super();
        this.rightSidePanelController = rightSidePanelController;
        this.model = model;

        mapImage = new Image(new Texture("ClassicMap.png"));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());

        labelStyleBlack36 = generateLabelStyle(36, Color.BLACK);

        createRightSidePanel();
        createStartRoundButton();

        towerClickListener = new TowerClickListener(model);

        towerLabel = createLabel("Towers", 20);

        powerUpLabel = createLabel("Power-ups", 620);

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
        addActor(sideBarBackground);
        addActor(smurfButton);
        addActor(chemistButton);
        addActor(hackerButton);
        addActor(electroButton);
        addActor(meckButton);
        addActor(ecobutton);

        addActor(mapImage);
        addActor(towerLabel);
        addActor(powerUpLabel);
        addActor(startRoundButton);
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

    private Label createLabel(String text, float y) {
        Label label = new Label(text, labelStyleBlack36);
        label.setPosition(1920 - sideBarBackground.getWidth()/2 - label.getWidth()/2, 1080 - label.getHeight() - y);
        return label;
    }


    private BitmapFont generateBitmapFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/CenturyGothic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font36 = generator.generateFont(parameter);
        generator.dispose();
        return font36;
    }

    private LabelStyle generateLabelStyle(int size, Color color){
        BitmapFont font36 = generateBitmapFont(size);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
    }

    private void createRightSidePanel() {
        sideBarBackground = new Image(new Texture("SideBarBackground.png"));
        sideBarBackground.setPosition(1920 - 320, 0);
    }


    private void renderViruses() {
        super.batch.begin();

        synchronized (model.getViruses()) {
            //Sprite virusSprite = new Sprite(new Texture("virus1Hp.png"));
            for (Virus virus : model.getViruses()) {
                //virusSprite = new Sprite(new Texture(virus.getImagePath()));

//                commonUseSprite.setTexture(new Texture(virus.getSpriteKey()));
//                commonUseSprite.setPosition(virus.getX(), virus.getY());
//                commonUseSprite.draw(super.batch);
                Sprite virusSprite = spriteMap.get(virus.getSpriteKey());
                virusSprite.setPosition(virus.getX(), virus.getY());
                virusSprite.draw(super.batch);


            }

        }

        super.batch.end();
    }

    private void renderTowers() {
        for (Tower tower: model.getTowers()) {
            Sprite towerSprite = new Sprite(new Texture(tower.getSpriteKey()));
            towerSprite.setPosition(tower.getPosX(), tower.getPosY());
            tower.setHeight(towerSprite.getHeight());
            tower.setWidth(towerSprite.getWidth());

            if(!tower.isPlaced() && !tower.getCollision()){
                Gdx.gl.glEnable(GL_BLEND);
                Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(new Color(150/255F, 150/255F, 150/255F, 0.8F));
                shapeRenderer.circle(tower.getPosX() + tower.getWidth()/2, tower.getPosY() + tower.getHeight()/2, tower.getRange());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL_BLEND);
            }
            else if(!tower.isPlaced() && tower.getCollision()){
                Gdx.gl.glEnable(GL_BLEND);
                Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(new Color(255/255F, 51/255F, 51/255F, 0.8F));
                shapeRenderer.circle(tower.getPosX() + tower.getWidth()/2, tower.getPosY() + tower.getHeight()/2, tower.getRange());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL_BLEND);
            }

            else if(tower.isPlaced() && !tower.getGotButton()){
                ImageButton btn = createInvisButtonsOnTower(tower, tower.getPosX(), tower.getPosY());
                btn.addListener(towerClickListener);
                tower.setGotButton(true);
            }

            super.batch.begin();
            towerSprite.draw(super.batch);
            super.batch.end();

        }


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
        startRoundButton.setPosition(1920 - sideBarBackground.getWidth()/2 - startRoundButton.getWidth()/2, 20);

        rightSidePanelController.addStartButtonListener(startRoundButton);
    }

    private ImageButton createTowerButtons(Texture texture, int x, int y, String name) {
        TextureRegion towerButtonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable towerButtonRegDrawable = new TextureRegionDrawable(towerButtonTextureRegion);
        ImageButton towerButton = new ImageButton(towerButtonRegDrawable); //Set the button up
        towerButton.setPosition(x, y);
        towerButton.setName(name);


        return towerButton;

    }


    private ImageButton createInvisButtonsOnTower(Tower tower,float x, float y) {
        Texture texture = new Texture(tower.getSpriteKey());
        TextureRegion invisButtonTextureRegion = new TextureRegion(texture);
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




}
