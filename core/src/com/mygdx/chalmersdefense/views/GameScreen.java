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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.views.GameScreenViews.BottomBarUpgradePanel;
import com.mygdx.chalmersdefense.views.GameScreenViews.LostPanel;
import com.mygdx.chalmersdefense.views.GameScreenViews.RightSidePanel;
import com.mygdx.chalmersdefense.views.GameScreenViews.WinPanel;

import static com.badlogic.gdx.graphics.GL20.*;

/**
 * @author Daniel Persson
 * <p>
 * 2021-09-20 Modified by Elin Forsberg: Added methods and variables to handle placing towers
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: All sprites now comes from hashmap when rendering and there are life and money labels
 * 2021-09-24 Modified by Elin Forsberg: Added methods to render projectiles
 * 2021-09-28 Modified by Daniel Persson: Added methods and instance variables to render upgrade panel and upgrade buttons
 * 2021-10-03 Modified by Elin Forsberg: Sprite render now uses general IMapObject and range circle rendering was separated
 * 2021-10-04 Modified by Daniel Persson: Refactored GameScreen into two seperate classes. BottomBarUpgradePanel and RightSidePanel
 * 2021-10-05 Modified by Daniel Persson: Added WinPanel rendering if game is won
 */
public class GameScreen extends AbstractScreen implements Screen {

    private final GameScreenController gameScreenController;
    private final LostPanel lostPanelOverlay;
    private final WinPanel winPanelOverlay;
    private final BottomBarUpgradePanel bottomBarUpgradePanel;
    private final RightSidePanel rightSidePanel;
    private final IViewModel model;
    private final Stage stageHUD;

    private final InputMultiplexer multiplexer = new InputMultiplexer();

    private final TextureAtlas pauseButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseButtonSkin = new Skin(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.json"), pauseButtonAtlas); // Create skin object
    private final Button pauseButton = new Button(pauseButtonSkin);

    private final Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
    private final Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));



    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Image mapImage;


    public GameScreen(Model model) {
        super();
        this.gameScreenController = new GameScreenController(model);
        this.lostPanelOverlay = new LostPanel(this, gameScreenController);
        this.winPanelOverlay = new WinPanel(this, gameScreenController);
        this.bottomBarUpgradePanel = new BottomBarUpgradePanel(this, model, spriteMap, largeSpriteMap);
        this.rightSidePanel = new RightSidePanel(this, model);
        this.model = model;
        this.stageHUD = new Stage(this.getViewport());

        pauseButton.setPosition(10, 1070 - pauseButton.getHeight());
        gameScreenController.addPauseButtonClickListener(pauseButton);

        // This should come from classicPath class
        mapImage = new Image(new Texture("ClassicMap.png"));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);


        bottomBarPanelBackground.setPosition(0, 0);
        sideBarBackground.setPosition(1920 - 320, 0);

        // Enables input from both stages at the same time
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(bottomBarUpgradePanel.getStage());
        multiplexer.addProcessor(rightSidePanel.getStage());
        Gdx.input.setInputProcessor(multiplexer);

    }

    /**
     * Setup actors
     */
    @Override
    public void buildStage() {
        stageHUD.addActor(bottomBarPanelBackground);
        stageHUD.addActor(sideBarBackground);

        addActor(mapImage);
        addActor(pauseButton);
    }


    /**
     * Renders GameScreen to screen
     *
     * @param delta the timeframe from previous frame to current frame
     */
    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());
        Gdx.input.setInputProcessor(multiplexer);

        renderRangeCircle();
        renderMapObjects();

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

        // Render lost game panel if game is lost
        if (model.getIsGameLost()) {
            lostPanelOverlay.render();
        } else {
            lostPanelOverlay.hideOverlay();
        }

        // Render win game overlay if game is won
        if (model.showWinPanel()) {
            winPanelOverlay.render();
        } else {
            winPanelOverlay.hideOverlay();
        }


        //TODO Remove when not needed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.startRoundPressed();
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
