package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
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
 */
public class GameScreen extends AbstractScreen implements Screen {

    private final GameScreenController gameScreenController;



    // Panels
    private final BottomBarUpgradePanel bottomBarUpgradePanel;
    private final RightSidePanel rightSidePanel;

    private final IViewModel model;
    private final Stage stageHUD;

    private ProgressBar progressBar;
    private final TextureRegion progressBarFilled = new TextureRegion(new Texture(Gdx.files.internal("GameScreen/progressbar/ProgressBarFilled.png")));
    private final Image progressBarSmurf = new Image(new Texture("GameScreen/progressbar/SmurfImage.png"));
    private final Sprite waypointMarker = new Sprite(new Texture("GameScreen/progressbar/WaypointMarker.png"));

    private final InputMultiplexer multiplexer = new InputMultiplexer();

    private final TextureAtlas pauseButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.atlas")); // Load atlas file from skin
    private final Skin pauseButtonSkin = new Skin(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.json"), pauseButtonAtlas); // Create skin object
    private final Button pauseButton = new Button(pauseButtonSkin);

    private final Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
    private final Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Image mapImage;

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

        stageHUD.addActor(bottomBarPanelBackground);
        stageHUD.addActor(sideBarBackground);

        createProgressBar();

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

        renderProgressBar();

        renderWaypointsOnProgressBar();

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


        //TODO Remove when not needed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            model.getViruses().add(VirusFactory.createVirusOne());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            model.startRoundPressed();
        }

    }


    private void createProgressBar() {
        TextureAtlas progressBarAtlas = new TextureAtlas(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.atlas")); // Load atlas file from skin
        Skin progressBarSkin = new Skin(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.json"), progressBarAtlas); // Create skin object
        progressBar = new ProgressBar(0, model.getWinningRound(), 1, false, progressBarSkin);
        progressBar.setSize(921, 52);
        progressBar.setPosition(280, 60);

        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth()/progressBar.getMaxValue()) * progressBar.getValue() - 1, 120);

        Image finnishFlag = new Image(new Texture(Gdx.files.internal("GameScreen/progressbar/FinnishFlagImage.png")));
        finnishFlag.setPosition(progressBar.getX() + progressBar.getWidth() - 15, progressBar.getY() + progressBar.getHeight()/2);

        stageHUD.addActor(progressBar);
        stageHUD.addActor(progressBarSmurf);
        stageHUD.addActor(finnishFlag);
    }

    private void renderProgressBar() {
        progressBar.setValue(model.getCurrentRound());
        progressBarFilled.setRegion(0, 0,(int) (((2 + progressBar.getWidth())/progressBar.getMaxValue()) * progressBar.getValue()) - 2 , 46);
        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth()/progressBar.getMaxValue()) * progressBar.getValue() - 20, 120);
        batch.begin();
        batch.draw(progressBarFilled, progressBar.getX() + 3, progressBar.getY() + 3);
        batch.end();
    }

    private void renderWaypointsOnProgressBar() {
        int[][]  waypointData = {{1,1},{2,2},{3,3}};
        float progressBarStepWidth = progressBar.getWidth()/progressBar.getMaxValue();
        for (int[] waypoint : waypointData) {
            Sprite virusSprite = spriteMap.get("virus" + waypoint[0]);
            float waypointPos = progressBar.getX() + waypoint[1] * progressBarStepWidth;
            virusSprite.setPosition(waypointPos - virusSprite.getWidth()/2, 0);
            virusSprite.setScale(0.5f);

            waypointMarker.setPosition(waypointPos, 58);

            batch.begin();
            virusSprite.draw(super.batch);
            if (model.getCurrentRound() != waypoint[1]) waypointMarker.draw(super.batch);

            batch.end();
            virusSprite.setScale(1);
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
