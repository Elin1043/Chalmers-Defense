package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.model.VirusFactory;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.controllers.TowerClickListener;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.towers.Tower;


import java.util.HashMap;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author Daniel Persson
 *
 * 2021-09-20 Modified by Elin Forsberg: Added methods and variables to handle placing towers
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: All sprites now comes from hashmap when rendering
 * 2021-09-24 Modified by Elin Forsberg: Added methods to render projectiles
 */
public class GameScreen extends AbstractScreen implements Screen {

    private RightSidePanelController rightSidePanelController;
    private BottomBarPanelController bottomBarPanelController;
    private GameScreenController gameScreenController;
    private Model model;

    private Image sideBarBackground;
    private final Image lifeIcon = new Image(new Texture("lifeIcon.png"));
    private Button startRoundButton;

    // Bottom bar
    private Image bottomBarPanelBackground;

    // Upgrade panel
    private Group bottomBarPanelUpgradeGroup;
    private Button upgradeButtonFirst;
    private Button upgradeButtonSecond;
    private Label towerNameLabel;

    private final Label lifeLabel;

    private final TextureAtlas upgradePanelAtlas = new TextureAtlas(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.atlas")); // Load atlas file from skin
    private final Skin upgradePanelSkin = new Skin(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.json"), upgradePanelAtlas); // Create skin object

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private LabelStyle labelStyleBlack36;
    private Label towerLabel;
    private Label powerUpLabel;

    private Image mapImage;

    private final ImageButton smurfButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830, "smurf");
    private final ImageButton chemistButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830, "chemist");
    private final ImageButton electroButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650, "electro");
    private final ImageButton hackerButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650, "hacker");
    private final ImageButton meckButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470, "meck");
    private final ImageButton ecoButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470, "eco");

    private TowerClickListener towerClickListener;
    private Batch batch = super.getBatch();


    private final HashMap<Integer, ImageButton> towerButtons = new HashMap<>();


    public GameScreen(Model model){
        super();
        this.rightSidePanelController = new RightSidePanelController(model);
        this.bottomBarPanelController = new BottomBarPanelController(model);
        this.gameScreenController = new GameScreenController(model);
        this.model = model;

        // This should come from classicPath class
        mapImage = new Image(new Texture("ClassicMap.png"));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);

        lifeIcon.setPosition(1650, 320);

        // Generating label style
        labelStyleBlack36 = generateLabelStyle(36, Color.BLACK);

        // START Bottom bar group creation
        bottomBarPanelUpgradeGroup = new Group();
        createBottomBarPanel();
        addActor(bottomBarPanelBackground);

        bottomBarPanelUpgradeGroup.setPosition(bottomBarPanelBackground.getWidth() - 1390, 0);
        bottomBarPanelUpgradeGroup.addActor(createBottomBarUpgradePanelBackground());
        createUpgradeButtons();

        towerNameLabel = new Label("", labelStyleBlack36);
        towerNameLabel.setPosition(170, 130);
        // END

        // START Right side panel creation
        createRightSidePanel();
        createStartRoundButton();

        towerClickListener = new TowerClickListener(model);

        towerLabel = createLabel("Towers", 20);
        lifeLabel = createLabel("Test", 700);

        powerUpLabel = createLabel("Power-ups", 620);

        towerButtons.put(100, smurfButton);
        towerButtons.put(200, chemistButton);
        towerButtons.put(300, hackerButton);
        towerButtons.put(400, electroButton);
        towerButtons.put(500, meckButton);
        towerButtons.put(600, ecoButton);

        addTowerButtonListener();
        // END
    }

    @Override
    public void buildStage() {
        // Bottom bar actors
        addActor(bottomBarPanelBackground);
        addActor(bottomBarPanelUpgradeGroup);
        bottomBarPanelUpgradeGroup.addActor(towerNameLabel);
        bottomBarPanelUpgradeGroup.setVisible(false);

        addActor(sideBarBackground);
        addActor(lifeIcon);
        addActor(smurfButton);
        addActor(chemistButton);
        addActor(hackerButton);
        addActor(electroButton);
        addActor(meckButton);
        addActor(ecoButton);

        addActor(mapImage);
        addActor(towerLabel);
        addActor(powerUpLabel);
        addActor(lifeLabel);
        addActor(startRoundButton);
    }

    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

        renderTowers();
        checkAffordableTowers();
        renderViruses();
        renderProjectiles();

        updateLifeCounter();
        // If clicked tower is present show upgrade panel.
        if (model.getClickedTower() != null) {
            bottomBarPanelUpgradeGroup.setVisible(true);
            updateUpgradePanelInfo(model.getClickedTower());
        } else {
            bottomBarPanelUpgradeGroup.setVisible(false);
        }


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.getVirusSpawner().spawnRound(1);
        }
    }

    private void updateLifeCounter() {
        lifeLabel.setText(model.getLivesLeft());
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
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private LabelStyle generateLabelStyle(int size, Color color){
        BitmapFont font36 = generateBitmapFont(size);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
    }

    private void createRightSidePanel() {
        sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
        sideBarBackground.setPosition(1920 - 320, 0);
    }

    //Render projectiles
    private void renderProjectiles() {
        super.batch.begin();

        for (Projectile projectile:model.getProjectilesList()) {
            Sprite projectileSprite = spriteMap.get(projectile.getName());
            projectileSprite.setPosition(projectile.getX(), projectile.getY());
            projectileSprite.draw(super.batch);
        }

        super.batch.end();
    }

    //Render viruses
    private void createBottomBarPanel() {
        bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));
        bottomBarPanelBackground.setPosition(0, 0);
    }

    private Actor createBottomBarUpgradePanelBackground() {
        Image bottomBarUpgradePanelBackground = new Image(new Texture("GameScreen/BottomBarUpgradePanel.png"));
        bottomBarUpgradePanelBackground.setPosition(0 , 3);
        return bottomBarUpgradePanelBackground;
    }

    private void createUpgradeButtons() {
        upgradeButtonFirst = new Button(upgradePanelSkin);
        bottomBarPanelUpgradeGroup.addActor(upgradeButtonFirst);
        upgradeButtonFirst.setPosition(580, 22);
        bottomBarPanelController.addClickListenerUpgradeButton(upgradeButtonFirst);

        upgradeButtonSecond = new Button(upgradePanelSkin);
        bottomBarPanelUpgradeGroup.addActor(upgradeButtonSecond);
        upgradeButtonSecond.setPosition(990, 22);
        bottomBarPanelController.addClickListenerUpgradeButton(upgradeButtonSecond);
    }

    private void updateUpgradePanelInfo(Tower tower) {
        towerNameLabel.setText(tower.getName());

    }

    private void renderViruses() {
        super.batch.begin();

        synchronized (model.getViruses()) {
            for (Virus virus : model.getViruses()) {

                Sprite virusSprite = spriteMap.get(virus.getSpriteKey());
                virusSprite.setPosition(virus.getX(), virus.getY());
                virusSprite.draw(super.batch);

            }

        }

        super.batch.end();
    }

    //Render towers
    private void renderTowers() {
        for (Tower tower: model.getTowers()) {
            Sprite towerSprite = spriteMap.get(tower.getSpriteKey());
            towerSprite.setPosition(tower.getPosX(), tower.getPosY());
            towerSprite.setRotation((float) tower.getAngle());

            //If tower is not placed and not colliding: circle around is grey
            if(!tower.isPlaced() && !tower.getCollision()){
                Gdx.gl.glEnable(GL_BLEND);
                Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(new Color(150/255F, 150/255F, 150/255F, 0.8F));
                shapeRenderer.circle(tower.getPosX() + tower.getWidth()/2, tower.getPosY() + tower.getHeight()/2, tower.getRange());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL_BLEND);
            }

            //If tower is not placed and colliding: circle around is red
            else if(!tower.isPlaced() && tower.getCollision()){
                Gdx.gl.glEnable(GL_BLEND);
                Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(new Color(255/255F, 51/255F, 51/255F, 0.8F));
                shapeRenderer.circle(tower.getPosX() + tower.getWidth()/2, tower.getPosY() + tower.getHeight()/2, tower.getRange());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL_BLEND);
            }

            //If tower is placed and dont have button: create a button and set that it's placed
            else if(tower.isPlaced() && !tower.getGotButton()){
                ImageButton btn = createInvisButtonsOnTower(towerSprite, tower.getPosX(), tower.getPosY());
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
        rightSidePanelController.addTowerButtonListener(ecoButton);
    }


    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1920 - sideBarBackground.getWidth()/2 - startRoundButton.getWidth()/2, 20);

        rightSidePanelController.addStartButtonListener(startRoundButton);
    }

    private ImageButton createRightPanelTowerButtons(Texture texture, int x, int y, String name) {
        TextureRegion towerButtonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable towerButtonRegDrawable = new TextureRegionDrawable(towerButtonTextureRegion);
        ImageButton towerButton = new ImageButton(towerButtonRegDrawable); //Set the button up
        towerButton.setPosition(x, y);
        towerButton.setName(name);


        return towerButton;

    }


    //Create button on towers placed
    private ImageButton createInvisButtonsOnTower(Sprite towerSprite,float x, float y) {
        TextureRegion invisButtonTextureRegion = new TextureRegion(towerSprite);
        TextureRegionDrawable invisTexRegDrawable = new TextureRegionDrawable(invisButtonTextureRegion);

        ImageButton invisButton = new ImageButton(invisTexRegDrawable); //Set the button up
        invisButton.setColor(255,255,255,0);
        invisButton.setSize(towerSprite.getWidth(), towerSprite.getHeight());
        invisButton.setPosition(x,y);


        this.addActor(invisButton);
        return invisButton;
    }


    //Checks what towers the player can afford
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
