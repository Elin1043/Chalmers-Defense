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
import com.mygdx.chalmersdefense.views.gameScreenViews.BottomBarUpgradePanel;
import com.mygdx.chalmersdefense.views.gameScreenViews.RightSidePanel;
import com.mygdx.chalmersdefense.views.viewUtilities.FontFactory;
import com.mygdx.chalmersdefense.utilities.RangeCircle;
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
    private final GameScreenController gameScreenController;   // Controller for GameScreen
    // Panels
    private final BottomBarUpgradePanel bottomBarUpgradePanel; // Upgrade panel object
    private final RightSidePanel rightSidePanel;               // Right side HUD with towers and powerups

    private final IViewModel model;     // Reference to models IView methods
    private final Stage stageHUD;       // A separate stage for displaying HUD information

    // Updatable progressbar items
    private ProgressBar progressBar;    // Progressbar for displaying round progress
    private final TextureRegion progressBarFilled = new TextureRegion(new Texture(Gdx.files.internal("GameScreen/progressbar/ProgressBarFilled.png"))); // A texture region of progressbar fill texture
    private final Image progressBarSmurf = new Image(new Texture("GameScreen/progressbar/SmurfImage.png"));     // Image of a smurf that is attached to progressbar knob
    private final Sprite waypointMarker = new Sprite(new Texture("GameScreen/progressbar/WaypointMarker.png")); // Sprite of waypoint markers

    // Updatable labels
    private final Label lifeLabel = new Label("", FontFactory.getLabelStyle36Black());
    private final Label moneyLabel = new Label("", FontFactory.getLabelStyle36Black());
    private final Label roundLabel = new Label("", FontFactory.getLabelStyle36Black());

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();  // Used to render circle

    /**
     * Creates the game screen of the game.
     *
     * @param model                    the model to display information from
     * @param gameScreenController     the controller class to use for adding listeners to this class
     * @param rightSidePanelController the controller class for use by the RightSidePanel
     * @param bottomBarPanelController the controller class for use by the BottomBarPanel
     */
    GameScreen(IViewModel model, GameScreenController gameScreenController, RightSidePanelController rightSidePanelController, BottomBarPanelController bottomBarPanelController) {
        super();
        this.rightSidePanel = new RightSidePanel(this, model, rightSidePanelController);
        this.bottomBarUpgradePanel = new BottomBarUpgradePanel(this, model, bottomBarPanelController, spriteMap, largeSpriteMap);
        this.model = model;
        this.stageHUD = new Stage(getViewport());
        this.gameScreenController = gameScreenController;

        createPauseButton();

        // Background image for bottom part of HUD
        createHUDBackgrounds();

        // Adds this stage to controller to get screen coordinates when window is scaled
        gameScreenController.addStageToController(this);

        // Enables input from both stages at the same time
        addToMultiplexer(bottomBarUpgradePanel.getStage());
        addToMultiplexer(rightSidePanel.getStage());
        addToMultiplexer(gameScreenController);
        addToMultiplexer(rightSidePanelController);

        createProgressBar();

        createLifeAndMoneyIcon();
    }

    @Override
    void setBackgroundImage() {
        Image mapImage = new Image(new Texture(model.getMapImagePath()));
        mapImage.setPosition(0, Gdx.graphics.getHeight() - mapImage.getHeight());
        gameScreenController.addMapClickListener(mapImage);
        getActors().insert(0, mapImage);
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

        updateLabelInfo();

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

        // Render open overlay on GameScreen stage
        OverlayManager.getInstance().showOverlay(model.getCurrentOverlay(), this);
        AbstractOverlay abstractOverlay = OverlayManager.getInstance().getCurrentOverlay();
        if (abstractOverlay != null) {
            abstractOverlay.render();
        }

        //TODO Remove when not needed
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            model.invincible();
        }


        //TODO Remove when not needed
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            model.skipRound();
        }

        //TODO Remove when not needed
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            model.moreMoney();
        }
    }


    //Create progressbar
    private void createProgressBar() {
        TextureAtlas progressBarAtlas = new TextureAtlas(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.atlas")); // Load atlas file from skin
        Skin progressBarSkin = new Skin(Gdx.files.internal("GameScreen/progressbar/ProgressBarSkin.json"), progressBarAtlas); // Create skin object
        progressBar = new ProgressBar(0, model.getWinningRound(), 1, false, progressBarSkin);
        progressBar.setSize(921, 52);
        progressBar.setPosition(350, 60);

        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth() / progressBar.getMaxValue()) * progressBar.getValue() - 1, 120);

        Image finnishFlag = new Image(new Texture(Gdx.files.internal("GameScreen/progressbar/FinnishFlagImage.png")));
        finnishFlag.setPosition(progressBar.getX() + progressBar.getWidth() - 2, progressBar.getY() + progressBar.getHeight() / 2);

        Label winningRoundLabel = new Label("Round: " + model.getWinningRound(), FontFactory.getLabelStyle18Black());
        winningRoundLabel.setPosition(progressBar.getX() + progressBar.getWidth() - winningRoundLabel.getWidth() - 21, progressBar.getY() - winningRoundLabel.getHeight() - 5);

        stageHUD.addActor(progressBar);
        stageHUD.addActor(progressBarSmurf);
        stageHUD.addActor(finnishFlag);
        stageHUD.addActor(winningRoundLabel);
    }


    //Renders progressbar
    private void renderProgressBar() {
        progressBar.setValue(model.getCurrentRound());

        // If progressbar is at 100% dont render knob.
        progressBar.setDisabled(model.getCurrentRound() >= model.getWinningRound());

        progressBarFilled.setRegion(0, 0, (int) (((progressBar.getWidth() - 6) / progressBar.getMaxValue()) * progressBar.getValue()) - 2, 46);
        progressBarSmurf.setPosition(progressBar.getX() + (progressBar.getWidth() / progressBar.getMaxValue()) * progressBar.getValue() - 20, 120);
        batch.begin();
        batch.draw(progressBarFilled, progressBar.getX() + 3, progressBar.getY() + 3);
        batch.end();
    }


    //Renders waypoint markers
    private void renderWaypointsOnProgressBar() {
        int[][] waypointData = {{1, 1}, {2, 3}, {3, 6}, {50, 10}, {4, 12}, {5, 16}, {50, 20}, {50, 30}};  // Data is structured like this: {Virus HP, first round appearance}.
        float progressBarStepWidth = progressBar.getWidth() / progressBar.getMaxValue();
        for (int[] waypoint : waypointData) {
            Sprite virusSprite = spriteMap.get("virus" + waypoint[0]);
            float waypointPos = progressBar.getX() + waypoint[1] * progressBarStepWidth;

            virusSprite.setScale(0.5f);
            if (waypoint[0] == 50) {
                virusSprite.setScale(0.25f);
            }

            virusSprite.setPosition(waypointPos - virusSprite.getWidth() / 2, progressBar.getY() - virusSprite.getHeight() / 2 - 20);
            waypointMarker.setPosition(waypointPos, 58);

            batch.begin();
            virusSprite.draw(super.batch);
            if (model.getCurrentRound() != waypoint[1]) waypointMarker.draw(super.batch);

            batch.end();
            virusSprite.setScale(1);
        }
    }


    //Renders all map objects
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


    //Renders range circle
    private void renderRangeCircle() {
        RangeCircle circle = model.getRangeCircle();

        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColorOfCircle(circle));
        shapeRenderer.circle(circle.getX(), circle.getY(), circle.getRange());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }


    //Update label info for HUD labels
    private void updateLabelInfo() {
        lifeLabel.setText(model.getLivesLeft());
        moneyLabel.setText(model.getMoney());
        roundLabel.setText("Round: " + model.getCurrentRound());
    }


    //Create pause button
    private void createPauseButton() {
        TextureAtlas pauseButtonAtlas = new TextureAtlas(Gdx.files.internal("buttons/gameScreenButtons/pauseButtonSkin/pauseButtonSkin.atlas")); // Load atlas file from skin
        Skin pauseButtonSkin = new Skin(Gdx.files.internal("buttons/gameScreenButtons/pauseButtonSkin/pauseButtonSkin.json"), pauseButtonAtlas); // Create skin object
        Button pauseButton = new Button(pauseButtonSkin);
        pauseButton.setPosition(10, 1070 - pauseButton.getHeight());
        gameScreenController.addPauseButtonClickListener(pauseButton);
        addActor(pauseButton);
    }


    //Background image for bottom part of HUD
    private void createHUDBackgrounds() {
        Image bottomBarPanelBackground = new Image(new Texture("GameScreen/BottomBarBackground.png"));
        bottomBarPanelBackground.setPosition(0, 0);
        stageHUD.addActor(bottomBarPanelBackground);

        Image sideBarBackground = new Image(new Texture("GameScreen/SideBarBackground.png"));
        sideBarBackground.setPosition(1920 - 320, 0);
        stageHUD.addActor(sideBarBackground);
    }


    //Creates life and money icons and labels
    private void createLifeAndMoneyIcon() {
        Image lifeIcon = new Image(new Texture("lifeIcon.png"));
        Image moneyIcon = new Image(new Texture("moneyIcon.png"));

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


    //Returns color of inputted circle
    private Color getColorOfCircle(RangeCircle circle) {
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
