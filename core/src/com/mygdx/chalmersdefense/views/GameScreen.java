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
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
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
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;


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
    private final Model model;

    private final Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
    private final Image lifeIcon = new Image(new Texture("lifeIcon.png"));
    private final Image moneyIcon = new Image(new Texture("moneyIcon.png"));

    private Button startRoundButton;

    // Bottom bar
    private Image bottomBarPanelBackground;

    // Upgrade panel
    private final Group bottomBarPanelUpgradeGroup = new Group();
    private final Label towerNameLabel = new Label("", generateLabelStyle(36, Color.BLACK, 1));

    // Generating label style
    private final LabelStyle labelStyleBlack36 = generateLabelStyle(36, Color.BLACK, 1);

    private final Label lifeLabel = createLabel("Test", 700);
    private final Label moneyLabel = createLabel("Test", 800);
    private final Label roundLabel = createLabel("Round: HH", 900);
    // Skin for upgrade buttons
    private final TextureAtlas upgradePanelAtlas = new TextureAtlas(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.atlas")); // Load atlas file from skin
    private final Skin upgradePanelSkin = new Skin(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.json"), upgradePanelAtlas); // Create skin object

    // Upgrade button
    private final Button upgradeButtonFirst = new Button(upgradePanelSkin);
    private final Button upgradeButtonSecond = new Button(upgradePanelSkin);

    // Labels for upgrade buttons
    private final Label firstUpgradeButtonTitle = new Label("", generateLabelStyle(24, Color.BLACK, 0.5f));
    private final Label firstUpgradeButtonDesc = new Label("", generateLabelStyle(18, Color.BLACK, 0));
    private final Label firstUpgradeButtonPrice = new Label("", generateLabelStyle(26, Color.BLACK, 0));
    private final Label secondUpgradeButtonTitle = new Label("", generateLabelStyle(24, Color.BLACK, 0.5f));
    private final Label secondUpgradeButtonDesc = new Label("", generateLabelStyle(18, Color.BLACK, 0));
    private final Label secondUpgradeButtonPrice = new Label("", generateLabelStyle(26, Color.BLACK, 0));


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
        moneyIcon.setPosition(1650, 220);


        // START Bottom bar group creation
        createBottomBarPanel();
        addActor(bottomBarPanelBackground);

        bottomBarPanelUpgradeGroup.setPosition(bottomBarPanelBackground.getWidth() - 1390, 0);
        bottomBarPanelUpgradeGroup.addActor(createBottomBarUpgradePanelBackground());
        upgradeButtonFirst.setPosition(580, 22);
        upgradeButtonSecond.setPosition(990, 22);

        createUpgradeButtons(upgradeButtonFirst, firstUpgradeButtonTitle, firstUpgradeButtonDesc, firstUpgradeButtonPrice);
        createUpgradeButtons(upgradeButtonSecond, secondUpgradeButtonTitle, secondUpgradeButtonDesc, secondUpgradeButtonPrice);

        towerNameLabel.setPosition(170, 135);
        // END

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
    }


    //Render methods
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

        checkAffordableTowers();
        renderRangeCircle();
        renderMapObjects();

        updateLabels();
        // If clicked tower is present show upgrade panel.
        if (model.getClickedTower() != null) {
            bottomBarPanelUpgradeGroup.setVisible(true);
            updateUpgradePanelInfo(model.getClickedTower());
        } else {
            bottomBarPanelUpgradeGroup.setVisible(false);
        }

        updateLabels();

        //TODO Remove when not needed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.startRoundPressed();
        }
    }

    private void renderMapObjects(){
        for (IMapObject mapObject: model.getAllMapObjects()) {
            Sprite objectSprite = spriteMap.get(mapObject.getSpriteKey());
            objectSprite.setPosition(mapObject.getX(), mapObject.getY());
            objectSprite.setRotation(mapObject.getAngle());

            super.batch.begin();
            objectSprite.draw(super.batch);
            super.batch.end();
        }

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
        Label label = new Label(text, labelStyleBlack36);
        label.setPosition(1920 - sideBarBackground.getWidth()/2 - label.getWidth()/2, 1080 - label.getHeight() - y);
        return label;
    }


    private BitmapFont generateBitmapFont(int size, float borderWidth) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/CenturyGothic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderWidth = borderWidth;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private LabelStyle generateLabelStyle(int size, Color color, float borderWidth){
        BitmapFont font36 = generateBitmapFont(size, borderWidth);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
    }



    //Bottom bar methods
    private void createBottomBarPanel() {
        bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));
        bottomBarPanelBackground.setPosition(0, 0);
    }

    private Actor createBottomBarUpgradePanelBackground() {
        Image bottomBarUpgradePanelBackground = new Image(new Texture("GameScreen/BottomBarUpgradePanel.png"));
        bottomBarUpgradePanelBackground.setPosition(0 , 3);
        return bottomBarUpgradePanelBackground;
    }

    //Upgrade methods

    /**
     * Sets up the upgrade button with labels and image.
     * @param upgradeButton the button to set up
     * @param titleLabel the title label to set up
     * @param descLabel the description label to set up
     * @param priceLabel the price label to set up
     */
    private void createUpgradeButtons(Button upgradeButton, Label titleLabel, Label descLabel, Label priceLabel) {
        bottomBarPanelUpgradeGroup.addActor(upgradeButton);
        bottomBarPanelController.addClickListenerUpgradeButton(upgradeButton);

        upgradeButton.addActor(titleLabel);
        upgradeButton.addActor(descLabel);
        upgradeButton.addActor(priceLabel);

        titleLabel.setPosition(110,120);

        descLabel.setPosition(110,80);
        descLabel.setWrap(true);
        descLabel.setWidth(240);

        priceLabel.setPosition(110, 25);
    }

    /**
     * Updates the upgrade panel.
     * @param tower tower name used to get correct sprite
     */
    private void updateUpgradePanelInfo(ITower tower) {
        towerNameLabel.setText(tower.getName());

        Sprite towerSpriteUpgradePanel = largeSpriteMap.get(tower.getSpriteKey() + "Large");
        towerSpriteUpgradePanel.setPosition(bottomBarPanelBackground.getWidth() - 1360, bottomBarPanelBackground.getHeight()/2 - towerSpriteUpgradePanel.getHeight()/2);
        towerSpriteUpgradePanel.setRotation(0);

        batch.begin();
        towerSpriteUpgradePanel.draw(batch);
        batch.end();

        updateUpgradeButton(tower, 1, upgradeButtonFirst, firstUpgradeButtonTitle, firstUpgradeButtonDesc, firstUpgradeButtonPrice);
        updateUpgradeButton(tower, 2, upgradeButtonSecond, secondUpgradeButtonTitle, secondUpgradeButtonDesc, secondUpgradeButtonPrice);
    }

    /**
     * Updates button labels and button data. Also sets button to correct state depending on available money and current upgrade level.
     * @param tower tower to get data from
     * @param buttonNr frist or second upgrade button
     * @param upgradeButton the button to modify
     * @param titleLabel the title label to modify
     * @param descLabel the description label to modify
     * @param priceLabel the price label to modify
     */
    private void updateUpgradeButton(ITower tower, int buttonNr, Button upgradeButton, Label titleLabel, Label descLabel, Label priceLabel) {
        Sprite upgradedTowerSprite = spriteMap.get(tower.getSpriteKey().replaceFirst(".$","") + (buttonNr + 1));
        upgradedTowerSprite.setPosition(upgradeButton.getX() + (268 - upgradedTowerSprite.getWidth()/2), (upgradeButton.getHeight() - upgradeButton.getY())/2 - upgradedTowerSprite.getHeight()/2 + upgradeButton.getY() + 20);
        upgradedTowerSprite.setRotation(0);



        // If upgrade is bought disable button input
        if ((tower.getUpgradeLevel() >= 1 + buttonNr)) {
            upgradeButton.setChecked(true);
            upgradeButton.setTouchable(Touchable.disabled);
        } else {
            upgradeButton.setChecked(false);
            upgradeButton.setTouchable(Touchable.enabled);
        }

        // If first upgrade not bought disable second button
        if (buttonNr == 2 && tower.getUpgradeLevel() == 1) {
            upgradeButton.setTouchable(Touchable.disabled);
            upgradeButton.setColor(Color.LIGHT_GRAY);
            upgradedTowerSprite.setColor(Color.LIGHT_GRAY);
        } else if (buttonNr == 2 && tower.getUpgradeLevel() >= 2) {
            upgradeButton.setTouchable(Touchable.enabled);
            upgradeButton.setColor(Color.WHITE);
            upgradedTowerSprite.setColor(Color.WHITE);
        }

        batch.begin();
        upgradedTowerSprite.draw(batch);
        upgradedTowerSprite.setColor(Color.WHITE);
        batch.end();

        //upgradeButton.setDisabled(model.getMoney() < model.getTowerUpgradePrice(tower, 1));

        titleLabel.setText(model.getTowerUpgradeTitle(tower, buttonNr));
        descLabel.setText(model.getTowerUpgradeDesc(tower, buttonNr));
        priceLabel.setText("" + model.getTowerUpgradePrice(tower, buttonNr));
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
