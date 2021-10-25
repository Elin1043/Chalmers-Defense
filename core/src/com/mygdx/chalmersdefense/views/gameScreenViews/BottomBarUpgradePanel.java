package com.mygdx.chalmersdefense.views.gameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class used to render upgrade panel
 * <p>
 * 2021-10-05 Created by Daniel Persson <br>
 */
final public class BottomBarUpgradePanel {
    private final Stage stage;          // Stage used for BottomBarUpgradePanel components
    private final IViewModel model;     // Reference to models IView methods
    private final BottomBarPanelController bottomBarPanelController; // Controller for BottomBarUpgradePanel

    private final HashMap<String, Sprite> spriteMap;        // Reference to sprites
    private final HashMap<String, Sprite> largeSpriteMap;   // Reference to sprites
    private final Batch batch;                              // Batch to use for rendering sprites

    private final String buttonsAssetsRoot = "buttons/gameScreenButtons/";  // Root path for button skins

    private final Image bottomBarUpgradePanelBackground = new Image(new Texture("GameScreen/BottomBarUpgradePanel.png"));  // Background image for upgrade panel

    private final Group bottomBarPanelUpgradeGroup = new Group();   // Group to add actors to
    private final Label towerNameLabel = new Label("", FontFactory.getLabelStyle36BlackBold());  // Label for tower name

    // Buttons
    private final Button upgradeButtonFirst = createUpgradePanelButton();   // First upgrade button
    private final Button upgradeButtonSecond = createUpgradePanelButton();  // Second upgrade button

    // Updatable labels for upgrade buttons
    private final Label firstUpgradeButtonTitle = new Label("", FontFactory.getLabelStyle24BlackSemiBold());
    private final Label firstUpgradeButtonDesc = new Label("", FontFactory.getLabelStyle18Black());
    private final Label firstUpgradeButtonPrice = new Label("", FontFactory.getLabelStyle26Black());
    private final Label secondUpgradeButtonTitle = new Label("", FontFactory.getLabelStyle24BlackSemiBold());
    private final Label secondUpgradeButtonDesc = new Label("", FontFactory.getLabelStyle18Black());
    private final Label secondUpgradeButtonPrice = new Label("", FontFactory.getLabelStyle26Black());

    // Updatable labels
    private final Label sellPriceLabel = new Label("", FontFactory.getLabelStyle26Black());
    private final Label targetModeLabel = new Label("", FontFactory.getLabelStyle20Black());

    /**
     * Creates the BottomBarUpgrade panel for use by GameScreen
     *
     * @param stage                    the parent stage
     * @param model                    the model to display information from
     * @param bottomBarPanelController the controller class to use for adding listeners to this class
     * @param spriteMap                the sprite hashmap for the game containing all sprites
     * @param largeSpriteMap           the large sprite hashmap for the game containing larger sprites
     */
    public BottomBarUpgradePanel(Stage stage, IViewModel model, BottomBarPanelController bottomBarPanelController, HashMap<String, Sprite> spriteMap, HashMap<String, Sprite> largeSpriteMap) {
        this.stage = new Stage(stage.getViewport());
        this.model = model;
        this.bottomBarPanelController = bottomBarPanelController;
        this.spriteMap = spriteMap;
        this.largeSpriteMap = largeSpriteMap;

        this.batch = stage.getBatch();

        initialize();
    }


    // Initializes all GUI components
    private void initialize() {
        stage.addActor(bottomBarPanelUpgradeGroup);
        bottomBarPanelUpgradeGroup.setPosition(210, 0);
        bottomBarPanelUpgradeGroup.addActor(bottomBarUpgradePanelBackground);
        bottomBarUpgradePanelBackground.setPosition(0, 3);

        towerNameLabel.setPosition(170, 135);

        upgradeButtonFirst.setPosition(580, 22);
        upgradeButtonSecond.setPosition(990, 22);

        createUpgradeButtons(upgradeButtonFirst, firstUpgradeButtonTitle, firstUpgradeButtonDesc, firstUpgradeButtonPrice);
        createUpgradeButtons(upgradeButtonSecond, secondUpgradeButtonTitle, secondUpgradeButtonDesc, secondUpgradeButtonPrice);

        createSellButton();
        createChangeTargetModeButton();

        bottomBarPanelUpgradeGroup.addActor(towerNameLabel);
        bottomBarPanelUpgradeGroup.setVisible(false);
    }

    /**
     * Method used to render upgrade panel to the screen
     *
     * @param tower to update upgrade panel with
     */
    public void render(IMapObject tower) {
        stage.act();
        stage.draw();
        updateUpgradePanelInfo(tower);
        updatePanelLabels();
        bottomBarPanelUpgradeGroup.setVisible(true);
    }

    /**
     * Hides upgrade panel
     */
    public void hideBottomBar() {
        bottomBarPanelUpgradeGroup.setVisible(false);
    }

    /**
     * Method used to get stage
     *
     * @return this stage
     */
    public Stage getStage() {
        return stage;
    }


    // Creates a upgrade button
    private Button createUpgradePanelButton() {
        // Skin for upgrade buttons
        TextureAtlas upgradePanelAtlas = new TextureAtlas(Gdx.files.internal(buttonsAssetsRoot + "upgradeButtonSkin/UpgradeButtonSkin.atlas")); // Load atlas file from skin
        Skin upgradePanelSkin = new Skin(Gdx.files.internal(buttonsAssetsRoot + "upgradeButtonSkin/UpgradeButtonSkin.json"), upgradePanelAtlas); // Create skin object
        return new Button(upgradePanelSkin);
    }


    // Create target mode buttons
    private void createChangeTargetModeButton() {
        TextureRegion changeTargetTextureRegion1 = new TextureRegion(new Texture(Gdx.files.internal(buttonsAssetsRoot + "changeTargetModeButton.png")));
        TextureRegionDrawable changeTargetTexRegDrawable1 = new TextureRegionDrawable(changeTargetTextureRegion1);

        TextureRegion changeTargetTextureRegion2 = new TextureRegion(new Texture(Gdx.files.internal(buttonsAssetsRoot + "changeTargetModeButton.png")));
        changeTargetTextureRegion2.flip(true, false);
        TextureRegionDrawable changeTargetTexRegDrawable2 = new TextureRegionDrawable(changeTargetTextureRegion2);

        ImageButton changeTarget1 = new ImageButton(changeTargetTexRegDrawable1);
        ImageButton changeTarget2 = new ImageButton(changeTargetTexRegDrawable2);
        changeTarget1.setPosition(753, 115);
        changeTarget2.setPosition(620, 115);

        changeTarget1.setName("right");
        changeTarget2.setName("left");

        stage.addActor(changeTarget1);
        stage.addActor(changeTarget2);
        stage.addActor(targetModeLabel);
        bottomBarPanelController.addClickListenerTargetModeButton(changeTarget1);
        bottomBarPanelController.addClickListenerTargetModeButton(changeTarget2);

        targetModeLabel.setPosition(653, 133);
        targetModeLabel.setWidth(100);
        targetModeLabel.setAlignment(Align.center);

    }


    // Creates sell button
    private void createSellButton() {
        TextureRegion sellButtonTextureRegion = new TextureRegion(new Texture(Gdx.files.internal(buttonsAssetsRoot + "towerSellButton.png")));
        TextureRegionDrawable sellTexRegDrawable = new TextureRegionDrawable(sellButtonTextureRegion);
        ImageButton sellButton = new ImageButton(sellTexRegDrawable);
        sellButton.setPosition(380, 30);

        stage.addActor(sellButton);
        stage.addActor(sellPriceLabel);
        bottomBarPanelController.addClickListenerSellButton(sellButton);


        sellPriceLabel.setPosition(520, 55);
    }


    // Update labels
    private void updatePanelLabels() {
        sellPriceLabel.setText("+" + "$" + model.getClickedTowerSellPrice());

        targetModeLabel.setText(model.getClickedTowerTargetMode());
    }


    // Sets up the upgrade button with labels and image.
    private void createUpgradeButtons(Button upgradeButton, Label titleLabel, Label descLabel, Label priceLabel) {
        bottomBarPanelUpgradeGroup.addActor(upgradeButton);
        bottomBarPanelController.addClickListenerUpgradeButton(upgradeButton);

        upgradeButton.addActor(titleLabel);
        upgradeButton.addActor(descLabel);
        upgradeButton.addActor(priceLabel);

        titleLabel.setPosition(110, 120);

        descLabel.setPosition(110, 80);
        descLabel.setWrap(true);
        descLabel.setWidth(240);

        priceLabel.setPosition(110, 25);
    }


    //Updates the upgrade panel.
    private void updateUpgradePanelInfo(IMapObject tower) {
        towerNameLabel.setText(tower.getSpriteKey().replaceFirst(".$", "")); // Removes the upgrade level from the spriteKey to just leave the name left

        Sprite towerSpriteUpgradePanel = largeSpriteMap.get(tower.getSpriteKey() + "Large");
        towerSpriteUpgradePanel.setPosition(bottomBarPanelUpgradeGroup.getX() + (85 - towerSpriteUpgradePanel.getWidth() / 2), bottomBarUpgradePanelBackground.getHeight() / 2 - towerSpriteUpgradePanel.getHeight() / 2);
        towerSpriteUpgradePanel.setRotation(0);

        batch.begin();
        towerSpriteUpgradePanel.draw(batch);
        batch.end();

        updateUpgradeButton(tower, 1, upgradeButtonFirst, firstUpgradeButtonTitle, firstUpgradeButtonDesc, firstUpgradeButtonPrice);
        updateUpgradeButton(tower, 2, upgradeButtonSecond, secondUpgradeButtonTitle, secondUpgradeButtonDesc, secondUpgradeButtonPrice);
    }


    // Updates button labels and button data. Also sets button to correct state depending on available money and current upgrade level.
    private void updateUpgradeButton(IMapObject tower, int buttonNr, Button upgradeButton, Label titleLabel, Label descLabel, Label priceLabel) {
        String towerName = tower.getSpriteKey().replaceFirst(".$", "");              // Removes the upgrade level from the spriteKey to just leave the name left
        int towerUpgradeLevel = Character.getNumericValue(tower.getSpriteKey().charAt(tower.getSpriteKey().length() - 1));     // Gets the last char in the string, and therefore the upgrade level

        Sprite upgradedTowerSprite = spriteMap.get(towerName + (buttonNr + 1));

        boolean cantAfford = model.getMoney() < model.getTowerUpgradePrice(towerName, buttonNr);
        boolean upgradeIsBought = (towerUpgradeLevel >= 1 + buttonNr);

        // If upgrade is bought disable button input
        if (upgradeIsBought) {
            upgradeIsBought(upgradeButton);
        } else {
            upgradeIsNotBought(upgradeButton, cantAfford);
        }

        // Modify second button only
        if (buttonNr == 2) {
            updateSecondUpgradeButton(towerUpgradeLevel, upgradeButton, upgradedTowerSprite, cantAfford, upgradeIsBought);
        }

        placeAndDrawUpgradeSprite(upgradeButton, upgradedTowerSprite);
        updateUpgradeInformationLabels(buttonNr, titleLabel, descLabel, priceLabel, towerName);
    }


    // Places and draws the upgrade sprite in the button
    private void placeAndDrawUpgradeSprite(Button upgradeButton, Sprite upgradedTowerSprite) {
        upgradedTowerSprite.setPosition(upgradeButton.getX() + (268 - upgradedTowerSprite.getWidth() / 2), (upgradeButton.getHeight() - upgradeButton.getY()) / 2 - upgradedTowerSprite.getHeight() / 2 + upgradeButton.getY() + 20);
        upgradedTowerSprite.setRotation(0);

        batch.begin();
        upgradedTowerSprite.draw(batch);
        upgradedTowerSprite.setColor(Color.WHITE);
        batch.end();
    }


    // Makes upgrade button blue
    private void upgradeIsBought(Button upgradeButton) {
        upgradeButton.setChecked(true);
        upgradeButton.setTouchable(Touchable.disabled);
    }


    // Makes green if player can afford, otherwise makes it red
    private void upgradeIsNotBought(Button upgradeButton, boolean cantAfford) {
        upgradeButton.setChecked(false);
        upgradeButton.setTouchable(Touchable.enabled);

        // If player can't afford upgrade disable button (makes it red)
        if (cantAfford) {
            upgradeButton.setDisabled(true);
            upgradeButton.setTouchable(Touchable.disabled);
        } else {
            upgradeButton.setDisabled(false);
            upgradeButton.setTouchable(Touchable.enabled);
        }
    }


    // Updates the text information on the upgrade buttons
    private void updateUpgradeInformationLabels(int buttonNr, Label titleLabel, Label descLabel, Label priceLabel, String towerName) {
        titleLabel.setText(model.getTowerUpgradeTitle(towerName, buttonNr));
        descLabel.setText(model.getTowerUpgradeDesc(towerName, buttonNr));
        priceLabel.setText("" + model.getTowerUpgradePrice(towerName, buttonNr));
    }


    // Updates the visual style of upgrade button two
    private void updateSecondUpgradeButton(int towerUpgradeLevel, Button upgradeButton, Sprite upgradedTowerSprite, boolean cantAfford, boolean upgradeIsBought) {

        if (!upgradeIsBought) {
            upgradeTwoIsNotBought(towerUpgradeLevel, upgradeButton, upgradedTowerSprite, cantAfford);
        } else { //
            resetOldDisabledStatus(upgradeButton, upgradedTowerSprite);
        }
    }


    // Handles logic behind upgrade button twos visual style
    private void upgradeTwoIsNotBought(int towerUpgradeLevel, Button upgradeButton, Sprite upgradedTowerSprite, boolean cantAfford) {

        // If first upgrade not bought disable second button
        if (towerUpgradeLevel == 1) {
            makeUpgradeButtonDisabled(upgradeButton, upgradedTowerSprite);

            // If player can afford upgrade enable button
            if (!cantAfford) {
                upgradeButton.setDisabled(false);
            }

            // If first upgrade is bought enable second upgrade button
        } else if (towerUpgradeLevel >= 2) {
            resetOldDisabledStatus(upgradeButton, upgradedTowerSprite);

            // If player can't afford, disable upgrade button (make it red)
            if (cantAfford) {
                upgradeButton.setTouchable(Touchable.disabled);
                upgradeButton.setDisabled(true);
            }
        }
    }


    // Disables upgrade button
    private void makeUpgradeButtonDisabled(Button upgradeButton, Sprite upgradedTowerSprite) {
        upgradeButton.setDisabled(true);
        upgradeButton.setTouchable(Touchable.disabled);
        upgradeButton.setColor(Color.LIGHT_GRAY);
        upgradedTowerSprite.setColor(Color.LIGHT_GRAY);
    }


    // Resets old disabled status for the button reassuring it will be new state for the button
    private void resetOldDisabledStatus(Button upgradeButton, Sprite upgradedTowerSprite) {
        upgradeButton.setDisabled(false);
        upgradeButton.setTouchable(Touchable.enabled);
        upgradeButton.setColor(Color.WHITE);
        upgradedTowerSprite.setColor(Color.WHITE);
    }
}
