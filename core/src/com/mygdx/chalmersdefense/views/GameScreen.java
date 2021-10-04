package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.utilities.FontFactory;
import com.mygdx.chalmersdefense.views.GameScreenViews.BottomBarUpgradePanel;
import com.mygdx.chalmersdefense.views.GameScreenViews.LostPanel;

import java.util.HashMap;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author Daniel Persson
 *
 * 2021-09-20 Modified by Elin Forsberg: Added methods and variables to handle placing towers
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: All sprites now comes from hashmap when rendering and there are life and money labels
 * 2021-09-24 Modified by Elin Forsberg: Added methods to render projectiles
 * 2021-09-28 Modified by Daniel Persson: Added methods and instance variables to render upgrade panel and upgrade buttons
 * 2021-10-03 Modified by Elin Forsberg: Sprite render now uses general IMapObject and range circle rendering was separated
 */
public class GameScreen extends AbstractScreen implements Screen {

    private final RightSidePanelController rightSidePanelController;
    private final BottomBarPanelController bottomBarPanelController;
    private final GameScreenController gameScreenController;
    private final LostPanel lostPanelView;
    private final BottomBarUpgradePanel bottomBarUpgradePanel;
    private final Model model;

    private final Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
    private final Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));

    private final Image lifeIcon = new Image(new Texture("lifeIcon.png"));
    private final Image moneyIcon = new Image(new Texture("moneyIcon.png"));

    private Button startRoundButton;

    private final Label lifeLabel = createLabel("Test", 700);
    private final Label moneyLabel = createLabel("Test", 800);
    private final Label roundLabel = createLabel("Round: HH", 900);

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Label towerLabel;
    private final Label powerUpLabel;

    private final Image mapImage;

    private final ImageButton smurfButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830, "smurf");
    private final ImageButton chemistButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830, "chemist");
    private final ImageButton electroButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650, "electro");
    private final ImageButton hackerButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650, "hacker");
    private final ImageButton meckButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470, "meck");
    private final ImageButton ecoButton = createRightPanelTowerButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470, "eco");


    private final Batch batch = super.getBatch();


    private final HashMap<Integer, ImageButton> towerButtons = new HashMap<>();


    public GameScreen(Model model) {
        super();
        this.rightSidePanelController = new RightSidePanelController(model);
        this.bottomBarPanelController = new BottomBarPanelController(model);
        this.gameScreenController = new GameScreenController(model);
        this.lostPanelView = new LostPanel(this, gameScreenController);
        this.bottomBarUpgradePanel = new BottomBarUpgradePanel(this, bottomBarPanelController, model, spriteMap, largeSpriteMap);
        this.model = model;

        // This should come from classicPath class
        mapImage = new Image(new Texture("ClassicMap.png"));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);


        bottomBarPanelBackground.setPosition(0, 0);

        // START Right side panel creation
        sideBarBackground.setPosition(1920 - 320, 0);

        createStartRoundButton();

        towerLabel = createLabel("Towers", 20);

        powerUpLabel = createLabel("Power-ups", 620);

        towerButtons.put(100, smurfButton);
        towerButtons.put(200, chemistButton);
        towerButtons.put(300, hackerButton);
        towerButtons.put(400, electroButton);
        towerButtons.put(500, meckButton);
        towerButtons.put(600, ecoButton);

        lifeIcon.setPosition(1650, 320);
        moneyIcon.setPosition(1650, 220);

        addTowerButtonListener();
        // END
    }

    @Override
    public void buildStage() {
        addActor(bottomBarPanelBackground);

        addActor(sideBarBackground);
        addActor(lifeIcon);
        addActor(moneyIcon);
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
        addActor(moneyLabel);
        addActor(roundLabel);
        addActor(startRoundButton);

        lostPanelView.initialize();
    }


    //Render methods
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());
        Gdx.input.setInputProcessor(this);

        checkAffordableTowers();
        renderRangeCircle();
        renderMapObjects();

        updateLabels();
        // If clicked tower is present show upgrade panel.
        if (model.getClickedTower() != null) {
            bottomBarUpgradePanel.render(model.getClickedTower());
        } else {
            bottomBarUpgradePanel.hideBottomBar();
        }

        updateLabels();
        if (model.getIsGameLost()) {
            lostPanelView.render();
        } else {
            lostPanelView.hideLostPanelGroup();
        }


        //TODO Remove when not needed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.startRoundPressed();
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void renderMapObjects(){
        super.batch.begin();
        for (IMapObject mapObject: model.getAllMapObjects()) {
            Sprite objectSprite = spriteMap.get(mapObject.getSpriteKey());
            objectSprite.setPosition(mapObject.getX(), mapObject.getY());
            objectSprite.setRotation(mapObject.getAngle());

            objectSprite.draw(super.batch);

        }
        super.batch.end();

    }

    private void renderRangeCircle(){
        GetRangeCircle circle = model.getRangeCircle();

        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColorOfCircle(circle));
        shapeRenderer.circle(circle.getX(), circle.getY(), circle.getRange());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }

    private Color getColorOfCircle(GetRangeCircle circle){
        switch (circle.getColor()){
            case RED -> {
                return new Color (255 / 255F, 51 / 255F, 51 / 255F, 0.8F);
            }
            case GRAY -> {
                return new Color (150 / 255F, 150 / 255F, 150 / 255F, 0.8F);
            }
            default -> {
                return Color.CLEAR;
            }
        }
    }


    //Tower buttons methods

    private ImageButton createRightPanelTowerButtons(Texture texture, int x, int y, String name) {
        TextureRegion towerButtonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable towerButtonRegDrawable = new TextureRegionDrawable(towerButtonTextureRegion);
        ImageButton towerButton = new ImageButton(towerButtonRegDrawable); //Set the button up
        towerButton.setPosition(x, y);
        towerButton.setName(name);


        return towerButton;

    }

    private void addTowerButtonListener() {
        rightSidePanelController.addTowerButtonListener(smurfButton);
        rightSidePanelController.addTowerButtonListener(chemistButton);
        rightSidePanelController.addTowerButtonListener(hackerButton);
        rightSidePanelController.addTowerButtonListener(electroButton);
        rightSidePanelController.addTowerButtonListener(meckButton);
        rightSidePanelController.addTowerButtonListener(ecoButton);
    }

    //Checks what towers the player can afford
    private void checkAffordableTowers() {
        for (Integer i : towerButtons.keySet()) {
            if(model.getMoney() >= i && !towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.enabled);
                towerButtons.get(i).getImage().setColor(Color.WHITE);

            }
            else if (model.getMoney()< i && towerButtons.get(i).isTouchable()){
                towerButtons.get(i).setTouchable(Touchable.disabled);
                towerButtons.get(i).getImage().setColor(Color.LIGHT_GRAY);
            }
        }
    }

    //Label methods
    private void updateLabels() {
        lifeLabel.setText(model.getLivesLeft());
        moneyLabel.setText(model.getMoney());
        roundLabel.setText("Round: " + model.getCurrentRound());
    }

    private Label createLabel(String text, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle36BlackBold());
        label.setPosition(1920 - sideBarBackground.getWidth()/2 - label.getWidth()/2, 1080 - label.getHeight() - y);
        return label;
    }

    //Start round button methods
    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1920 - sideBarBackground.getWidth()/2 - startRoundButton.getWidth()/2, 20);

        rightSidePanelController.addStartButtonListener(startRoundButton);
    }





}
