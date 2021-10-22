package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.views.GameScreenViews.BottomBarUpgradePanel;
import com.mygdx.chalmersdefense.views.GameScreenViews.RightSidePanel;
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
 * 2021-10-19 Modified by Daniel Persson: Added progressbar for displaying round progress <br>
 */
final class GameScreen extends AbstractScreen implements Screen {
    private final GameScreenController gameScreenController;
    // Panels
    private final BottomBarUpgradePanel bottomBarUpgradePanel; // Upgrade panel object
    private final RightSidePanel rightSidePanel;               // Right side HUD with towers and powerups

    private final IViewModel model;  // Reference to models IView methods
    private final Stage stageHUD;    // A separate stage for displaying HUD information

    private ProgressBar progressBar; // Progressbar for displaying round progress
    private final TextureRegion progressBarFilled = new TextureRegion(new Texture(Gdx.files.internal("GameScreen/progressbar/ProgressBarFilled.png"))); // A texture region of progressbar fill texture
    private final Image progressBarSmurf = new Image(new Texture("GameScreen/progressbar/SmurfImage.png"));     // Image of a smurf that is attached to progressbar knob
    private final Sprite waypointMarker = new Sprite(new Texture("GameScreen/progressbar/WaypointMarker.png")); // Sprite of waypoint markers


    private final Image lifeIcon = new Image(new Texture("lifeIcon.png"));
    private final Image moneyIcon = new Image(new Texture("moneyIcon.png"));
    private final Label lifeLabel = new Label("", FontFactory.getLabelStyle36Black());
    private final Label moneyLabel = new Label("", FontFactory.getLabelStyle36Black());
    private final Label roundLabel = new Label("", FontFactory.getLabelStyle36Black());

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    GameScreen(IViewModel model, GameScreenController gameScreenController, RightSidePanelController rightSidePanelController, BottomBarPanelController bottomBarPanelController) {
        super();
        this.rightSidePanel = new RightSidePanel(this, model, rightSidePanelController);
        this.bottomBarUpgradePanel = new BottomBarUpgradePanel(this, model, bottomBarPanelController, spriteMap, largeSpriteMap);
        this.model = model;
        this.stageHUD = new Stage(this.getViewport());
        this.gameScreenController = gameScreenController;

        // Create skin object
        // Load atlas file from skin
        TextureAtlas pauseButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.atlas"));
        Skin pauseButtonSkin = new Skin(Gdx.files.internal("buttons/pauseButtonSkin/pauseButtonSkin.json"), pauseButtonAtlas);
        Button pauseButton = new Button(pauseButtonSkin);
        pauseButton.setPosition(10, 1070 - pauseButton.getHeight());
        gameScreenController.addPauseButtonClickListener(pauseButton);

        // Background image for bottom part of HUD
        Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));
        bottomBarPanelBackground.setPosition(0, 0);
        Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
        stageHUD.addActor(bottomBarPanelBackground);

        // This should come from classicPath class
        Image mapImage = new Image(new Texture("ClassicMap.png")); // TODO Hämta från Path
        mapImage.setPosition(0, bottomBarPanelBackground.getHeight());
        gameScreenController.addMapClickListener(mapImage);

        // Background image for right part of HUD
        sideBarBackground.setPosition(1920 - 320, 0);
        stageHUD.addActor(sideBarBackground);

        // Enables input from both stages at the same time
        addToMultiplexer(bottomBarUpgradePanel.getStage());
        addToMultiplexer(rightSidePanel.getStage());
        addToMultiplexer(gameScreenController);
        addToMultiplexer(rightSidePanelController);


        createProgressBar();

        addActor(mapImage);
        addActor(pauseButton);
        createLifeAndMoneyIcon();
    }

    @Override
    void setBackgroundImage(){
        Image mapImage = new Image(new Texture(model.getMapImagePath())); // TODO Hämta från Path
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);
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
            model.getVirusesToAddList().add(VirusFactory.createVirusOne());
        }


        //TODO Remove when not needed
        if(Gdx.input.isKeyPressed(Input.Keys.K)) {
            model.getViruses().clear();
        }

    }


    private void createProgressBar() {
        TextureAtlas progressBarAtlas = new TextureAtlas(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.atlas")); // Load atlas file from skin
        Skin progressBarSkin = new Skin(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.json"), progressBarAtlas); // Create skin object
        progressBar = new ProgressBar(0, model.getWinningRound(), 1, false, progressBarSkin);
        progressBar.setSize(921, 52);
        progressBar.setPosition(350, 60);

        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth()/progressBar.getMaxValue()) * progressBar.getValue() - 1, 120);

        Image finnishFlag = new Image(new Texture(Gdx.files.internal("GameScreen/progressbar/FinnishFlagImage.png")));
        finnishFlag.setPosition(progressBar.getX() + progressBar.getWidth() - 2, progressBar.getY() + progressBar.getHeight()/2);

        Label winningRoundLabel = new Label("Round: " + model.getWinningRound(), FontFactory.getLabelStyle18Black());
        winningRoundLabel.setPosition(progressBar.getX() + progressBar.getWidth() - winningRoundLabel.getWidth(), progressBar.getY() - winningRoundLabel.getHeight() - 5);

        stageHUD.addActor(progressBar);
        stageHUD.addActor(progressBarSmurf);
        stageHUD.addActor(finnishFlag);
        stageHUD.addActor(winningRoundLabel);
    }

    private void renderProgressBar() {
        progressBar.setValue(model.getCurrentRound());

        // If progressbar is at 100% dont render knob.
        progressBar.setDisabled(model.getCurrentRound() >= model.getWinningRound());

        progressBarFilled.setRegion(0, 0,(int) (((progressBar.getWidth() - 6)/progressBar.getMaxValue()) * progressBar.getValue()) - 2 , 46);
        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth()/progressBar.getMaxValue()) * progressBar.getValue() - 20, 120);
        batch.begin();
        batch.draw(progressBarFilled, progressBar.getX() + 3, progressBar.getY() + 3);
        batch.end();
    }

    private void renderWaypointsOnProgressBar() {
        int[][]  waypointData = {{1,1},{2,3},{3,6},{50,10},{4,12},{5,16},{50,20},{50,30}};
        float progressBarStepWidth = progressBar.getWidth()/progressBar.getMaxValue();
        for (int[] waypoint : waypointData) {
            Sprite virusSprite = spriteMap.get("virus" + waypoint[0]);
            float waypointPos = progressBar.getX() + waypoint[1] * progressBarStepWidth;

            virusSprite.setScale(0.5f);
            if(waypoint[0] == 50){virusSprite.setScale(0.25f);}

            virusSprite.setPosition(waypointPos - virusSprite.getWidth()/2, progressBar.getY() - virusSprite.getHeight()/2 -20);
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
