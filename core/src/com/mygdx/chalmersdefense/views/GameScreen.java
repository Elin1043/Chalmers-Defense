package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.controllers.overlays.SettingsOverlayController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.views.GameScreenViews.*;
import com.mygdx.chalmersdefense.views.overlays.AbstractOverlay;
import com.mygdx.chalmersdefense.views.overlays.OverlayManager;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author Daniel Persson
 * <p>
 * 2021-09-20 Modified by Elin Forsberg: Added methods and variables to handle placing towers <br>
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: All sprites now comes from hashmap when rendering and there are life and money labels <br>
 * 2021-09-24 Modified by Elin Forsberg: Added methods to render projectiles <br>
 * 2021-09-28 Modified by Daniel Persson: Added methods and instance variables to render upgrade panel and upgrade buttons <br>
 * 2021-10-03 Modified by Elin Forsberg: Sprite render now uses general IMapObject and range circle rendering was separated <br>
 * 2021-10-04 Modified by Daniel Persson: Refactored GameScreen into two seperate classes. BottomBarUpgradePanel and RightSidePanel <br>
 * 2021-10-05 Modified by Daniel Persson: Added WinPanelOverlay rendering if game is won <br>
 * 2021-10-11 Modified by Daniel Persson: Added overlay enums for displaying overlays <br>
 * 2021-10-19 Modified by Joel Båtsman Hilmersson: Made class use superclass multiplexer for inputProcessor <br>
 */
public class GameScreen extends AbstractScreen implements Screen {

    private final GameScreenController gameScreenController;



    // Panels
    private final BottomBarUpgradePanel bottomBarUpgradePanel;
    private final RightSidePanel rightSidePanel;

    private final IViewModel model;
    private final Stage stageHUD;

    private final TextureAtlas pauseButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseButtonSkin = new Skin(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.json"), pauseButtonAtlas); // Create skin object
    private final Button pauseButton = new Button(pauseButtonSkin);

    private final Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
    private final Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));

    private final Image lifeIcon = new Image(new Texture("lifeIcon.png"));
    private final Image moneyIcon = new Image(new Texture("moneyIcon.png"));
    private final Label lifeLabel = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle36Black());
    private final Label moneyLabel = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle36Black());
    private final Label roundLabel = new Label("", com.mygdx.chalmersdefense.utilities.FontFactory.getLabelStyle36Black());

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public GameScreen(IViewModel model, GameScreenController gameScreenController, RightSidePanelController rightSidePanelController, BottomBarPanelController bottomBarPanelController) {
        super();
        this.gameScreenController = gameScreenController;
        this.rightSidePanel = new RightSidePanel(this, model, rightSidePanelController);
        this.bottomBarUpgradePanel = new BottomBarUpgradePanel(this, model, bottomBarPanelController, spriteMap, largeSpriteMap);
        this.model = model;
        this.stageHUD = new Stage(this.getViewport());

        pauseButton.setPosition(10, 1070 - pauseButton.getHeight());
        gameScreenController.addPauseButtonClickListener(pauseButton);

        // This should come from classicPath class
        Image mapImage = new Image(new Texture("ClassicMap.png")); // TODO Hämta från Path
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);


        bottomBarPanelBackground.setPosition(0, 0);
        sideBarBackground.setPosition(1920 - 320, 0);

        // Enables input from both stages at the same time
        addToMultiplexer(bottomBarUpgradePanel.getStage());
        addToMultiplexer(rightSidePanel.getStage());
        addToMultiplexer(gameScreenController);
        addToMultiplexer(rightSidePanelController);

        stageHUD.addActor(bottomBarPanelBackground);
        stageHUD.addActor(sideBarBackground);

        addActor(mapImage);
        addActor(pauseButton);
        createLifeAndMoneyIcon();
    }

    /**
     * Renders GameScreen to screen
     *
     * @param delta the timeframe from previous frame to current frame
     */
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

        renderRangeCircle();
        renderMapObjects();

        updateButtonInfo();

        // Renders HUD above sprites but below upgrade panel.
        stageHUD.act();
        stageHUD.draw();

        // Renders right HUD panel
        rightSidePanel.render();

        // If clicked tower is present show upgrade panel.
        if (model.getClickedTower() != null) {
            bottomBarUpgradePanel.render(model.getClickedTower());
        } else {
            bottomBarUpgradePanel.hideBottomBar();
        }

        OverlayManager.getInstance().showOverlay(model.showOverlay());

        AbstractOverlay abstractOverlay = OverlayManager.getInstance().getCurrentOverlay();
        if (abstractOverlay != null) {
            abstractOverlay.render();
        }

    }

    private void renderMapObjects() {
        super.batch.begin();
        for (IMapObject mapObject : model.getAllMapObjects()) {
            Sprite objectSprite = spriteMap.get(mapObject.getSpriteKey());
            objectSprite.setPosition(mapObject.getX(), mapObject.getY());
            objectSprite.setRotation(mapObject.getAngle());

            objectSprite.draw(super.batch);

        }
        super.batch.end();

    }

    private void renderRangeCircle() {
        GetRangeCircle circle = model.getRangeCircle();

        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColorOfCircle(circle));
        shapeRenderer.circle(circle.getX(), circle.getY(), circle.getRange());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }

    private void updateButtonInfo(){
        lifeLabel.setText(model.getLivesLeft());
        moneyLabel.setText(model.getMoney());
        roundLabel.setText("Round: " + model.getCurrentRound());
    }

    private void createLifeAndMoneyIcon(){
        lifeIcon.setPosition(23, 100);
        moneyIcon.setPosition(23, 20);

        lifeLabel.setPosition(96, 140);
        moneyLabel.setPosition(96, 60);

        roundLabel.setAlignment(Align.right);
        roundLabel.setPosition(1570, 140);

        stageHUD.addActor(lifeLabel);
        stageHUD.addActor(moneyLabel);
        stageHUD.addActor(lifeIcon);
        stageHUD.addActor(moneyIcon);
        stageHUD.addActor(roundLabel);
    }

    private Color getColorOfCircle(GetRangeCircle circle) {
        switch (circle.getColor()) {
            case RED -> {
                return new Color(255 / 255F, 51 / 255F, 51 / 255F, 0.8F);
            }
            case GRAY -> {
                return new Color(150 / 255F, 150 / 255F, 150 / 255F, 0.8F);
            }
            default -> {
                return Color.CLEAR;
            }
        }
    }
}
