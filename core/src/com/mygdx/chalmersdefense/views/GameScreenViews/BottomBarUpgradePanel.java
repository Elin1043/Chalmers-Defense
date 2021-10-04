package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.Model;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class used to render upgrade panel
 */
public class BottomBarUpgradePanel {
    private final Stage stage;
    private final Batch batch;
    private final BottomBarPanelController bottomBarPanelController;
    private final Model model;
    private final HashMap<String, Sprite> spriteMap;
    private final HashMap<String, Sprite> largeSpriteMap;

    private final InputMultiplexer multiplexer = new InputMultiplexer();

    private final Image bottomBarUpgradePanelBackground = new Image(new Texture("GameScreen/BottomBarUpgradePanel.png"));

    private final Group bottomBarPanelUpgradeGroup = new Group();
    private final Label towerNameLabel = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle36BlackBold());

    // Skin for upgrade buttons
    private final TextureAtlas upgradePanelAtlas = new TextureAtlas(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.atlas")); // Load atlas file from skin
    private final Skin upgradePanelSkin = new Skin(Gdx.files.internal("buttons/upgradeButtonSkin/UpgradeButtonSkin.json"), upgradePanelAtlas); // Create skin object

    // Upgrade button
    private final Button upgradeButtonFirst = new Button(upgradePanelSkin);
    private final Button upgradeButtonSecond = new Button(upgradePanelSkin);

    // Labels for upgrade buttons
    private final Label firstUpgradeButtonTitle = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle24BlackSemiBold());
    private final Label firstUpgradeButtonDesc = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle18Black());
    private final Label firstUpgradeButtonPrice = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle26Black());
    private final Label secondUpgradeButtonTitle = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle24BlackSemiBold());
    private final Label secondUpgradeButtonDesc = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle18Black());
    private final Label secondUpgradeButtonPrice = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle26Black());

    public BottomBarUpgradePanel(Stage stage, BottomBarPanelController bottomBarPanelController, Model model, HashMap<String, Sprite> spriteMap, HashMap<String, Sprite> largeSpriteMap) {
        this.stage = new Stage(stage.getViewport());
        this.batch = stage.getBatch();
        this.bottomBarPanelController = bottomBarPanelController;
        this.model = model;
        this.spriteMap = spriteMap;
        this.largeSpriteMap = largeSpriteMap;

        // Enables input from both stages at the same time
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this.stage);
        Gdx.input.setInputProcessor(multiplexer);

        initialize();
    }

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

        bottomBarPanelUpgradeGroup.addActor(towerNameLabel);
        bottomBarPanelUpgradeGroup.setVisible(false);
    }

    /**
     * Method used to render upgrade panel to the screen
     */
    public void render(IMapObject tower) {
        stage.act();
        stage.draw();
        updateUpgradePanelInfo(tower);
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
     * @return this stage
     */
    public Stage getStage() {
        return stage;
    }

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
    private void updateUpgradePanelInfo(IMapObject tower) {
        towerNameLabel.setText(tower.getSpriteKey().replaceFirst(".$","")); // Removes the upgrade level from the spriteKey to just leave the name left

        Sprite towerSpriteUpgradePanel = largeSpriteMap.get(tower.getSpriteKey() + "Large");
        towerSpriteUpgradePanel.setPosition(bottomBarPanelUpgradeGroup.getX() + (85 - towerSpriteUpgradePanel.getWidth()/2), bottomBarUpgradePanelBackground.getHeight()/2 - towerSpriteUpgradePanel.getHeight()/2);
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
    private void updateUpgradeButton(IMapObject tower, int buttonNr, Button upgradeButton, Label titleLabel, Label descLabel, Label priceLabel) {
        String towerName = tower.getSpriteKey().replaceFirst(".$","");              // Removes the upgrade level from the spriteKey to just leave the name left
        int towerUpgradeLevel = Character.getNumericValue(tower.getSpriteKey().charAt(tower.getSpriteKey().length() - 1));     // Gets the last char in the string, and therefore the upgrade level

        Sprite upgradedTowerSprite = spriteMap.get(towerName + (buttonNr + 1));
        upgradedTowerSprite.setPosition(upgradeButton.getX() + (268 - upgradedTowerSprite.getWidth()/2), (upgradeButton.getHeight() - upgradeButton.getY())/2 - upgradedTowerSprite.getHeight()/2 + upgradeButton.getY() + 20);
        upgradedTowerSprite.setRotation(0);

        boolean cantAfford = model.getMoney() < model.getTowerUpgradePrice(towerName, buttonNr);
        boolean upgradeIsBought = (towerUpgradeLevel >= 1 + buttonNr);

        // If upgrade is bought disable button input
        if (upgradeIsBought) {
            upgradeButton.setChecked(true);
            upgradeButton.setTouchable(Touchable.disabled);
        } else {
            upgradeButton.setChecked(false);
            upgradeButton.setTouchable(Touchable.enabled);

            // If player can afford upgrade enable button
            if (cantAfford) {
                upgradeButton.setDisabled(true);
                upgradeButton.setTouchable(Touchable.disabled);
            } else {
                upgradeButton.setDisabled(false);
                upgradeButton.setTouchable(Touchable.enabled);
            }
        }

        // Modify second button only
        if (buttonNr == 2) updateSecondUpgradeButton(towerUpgradeLevel, upgradeButton, upgradedTowerSprite, cantAfford, upgradeIsBought);


        batch.begin();
        upgradedTowerSprite.draw(batch);
        upgradedTowerSprite.setColor(Color.WHITE);
        batch.end();

        //upgradeButton.setDisabled(model.getMoney() < model.getTowerUpgradePrice(tower, 1));

        titleLabel.setText(model.getTowerUpgradeTitle(towerName, buttonNr));
        descLabel.setText(model.getTowerUpgradeDesc(towerName, buttonNr));
        priceLabel.setText("" + model.getTowerUpgradePrice(towerName, buttonNr));
    }

    private void updateSecondUpgradeButton(int towerUpgradeLevel, Button upgradeButton, Sprite upgradedTowerSprite, boolean cantAfford, boolean upgradeIsBought) {
        if (!upgradeIsBought) {
            // If first upgrade not bought disable second button
            if (towerUpgradeLevel == 1) {
                upgradeButton.setDisabled(true);
                upgradeButton.setTouchable(Touchable.disabled);
                upgradeButton.setColor(Color.LIGHT_GRAY);
                upgradedTowerSprite.setColor(Color.LIGHT_GRAY);
                // If first upgrade is bought enable second upgrade button
            } else if (towerUpgradeLevel >= 2) {
                upgradeButton.setTouchable(Touchable.enabled);
                upgradeButton.setColor(Color.WHITE);
                upgradedTowerSprite.setColor(Color.WHITE);

                // If player can afford enable upgrade button
                if (!cantAfford) {
                    upgradeButton.setDisabled(false);
                } else {
                    upgradeButton.setTouchable(Touchable.disabled);
                }
            }
        }
    }
}
