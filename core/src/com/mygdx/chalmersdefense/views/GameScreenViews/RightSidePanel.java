package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.FontFactory;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class used to render the right panel
 * <p>
 * 2021-10-05 Created by Daniel Persson <br>
 */
public class RightSidePanel {
    private final int WIDTH = 320;

    private final Stage stage;
    private final RightSidePanelController rightSidePanelController;
    private final Model model;


    private final Label towerLabel = createLabel("Towers", 20);
    private final Label powerUpLabel = createLabel("Power-ups", 620);



    private final Label roundLabel = createLabel("Round: HH", 920);

    private final HashMap<Integer, ImageButton> towerButtons = new HashMap<>();

    private final ImageButton smurfButton = createRightPanelButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830, "smurf");
    private final ImageButton chemistButton = createRightPanelButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830, "chemist");
    private final ImageButton electroButton = createRightPanelButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650, "electro");
    private final ImageButton hackerButton = createRightPanelButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650, "hacker");
    private final ImageButton mechButton = createRightPanelButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470, "mech");
    private final ImageButton ecoButton = createRightPanelButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470, "eco");

    private final ImageButton cleanHandsPowerUpButton = createRightPanelButtons(new Texture("buttons/powerUpButtons/CleanHands.png"), 1620, 329, "cleanHands");
    private final ImageButton maskedUpPowerUpButton = createRightPanelButtons(new Texture("buttons/powerUpButtons/MaskedUp.png"), 1620, 245, "maskedUp");
    private final ImageButton vaccinatedPowerUpButton = createRightPanelButtons(new Texture("buttons/powerUpButtons/Vaccinated.png"), 1620, 161, "vaccinated");

    private final Label cleanHandsLabel = createPowerUpLabel("Clean hands",1685, 379);
    private final Label maskedUpLabel = createPowerUpLabel("Masked-up",1685, 295);
    private final Label vaccinatedLabel = createPowerUpLabel("Vaccinated",1685, 211);

    private Button startRoundButton;

    public RightSidePanel(Stage stage, Model model) {
        this.stage = new Stage(stage.getViewport());
        this.rightSidePanelController = new RightSidePanelController(model);
        this.model = model;

        initialize();
    }

    private void initialize() {
        towerButtons.put(100, smurfButton);
        towerButtons.put(200, chemistButton);
        towerButtons.put(300, hackerButton);
        towerButtons.put(400, electroButton);
        towerButtons.put(500, mechButton);
        towerButtons.put(600, ecoButton);



        stage.addActor(smurfButton);
        stage.addActor(chemistButton);
        stage.addActor(hackerButton);
        stage.addActor(electroButton);
        stage.addActor(mechButton);
        stage.addActor(ecoButton);

        stage.addActor(cleanHandsPowerUpButton);
        stage.addActor(maskedUpPowerUpButton);
        stage.addActor(vaccinatedPowerUpButton);

        stage.addActor(towerLabel);
        stage.addActor(powerUpLabel);

        stage.addActor(cleanHandsLabel);
        stage.addActor(maskedUpLabel);
        stage.addActor(vaccinatedLabel);

        stage.addActor(roundLabel);

        addButtonListener();

        createStartRoundButton();
    }

    /**
     * Method used to render right panel to the screen
     */
    public void render() {
        checkAffordableTowers();
        updateLabels();

        stage.act();
        stage.draw();
    }

    /**
     * Method used to get stage
     *
     * @return this stage
     */
    public Stage getStage() {
        return stage;
    }

    private ImageButton createRightPanelButtons(Texture texture, int x, int y, String name) {
        TextureRegion buttonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable buttonRegDrawable = new TextureRegionDrawable(buttonTextureRegion);
        ImageButton button = new ImageButton(buttonRegDrawable); //Set the button up
        button.setPosition(x, y);
        button.setName(name);

        return button;

    }


    private void addButtonListener() {
        rightSidePanelController.addTowerButtonListener(smurfButton);
        rightSidePanelController.addTowerButtonListener(chemistButton);
        rightSidePanelController.addTowerButtonListener(hackerButton);
        rightSidePanelController.addTowerButtonListener(electroButton);
        rightSidePanelController.addTowerButtonListener(mechButton);
        rightSidePanelController.addTowerButtonListener(ecoButton);

        rightSidePanelController.addPowerUpButtonListener(cleanHandsPowerUpButton);
        rightSidePanelController.addPowerUpButtonListener(maskedUpPowerUpButton);
        rightSidePanelController.addPowerUpButtonListener(vaccinatedPowerUpButton);


    }

    //Checks what towers the player can afford
    private void checkAffordableTowers() {
        for (Integer i : towerButtons.keySet()) {
            if (model.getMoney() >= i && !towerButtons.get(i).isTouchable()) {
                towerButtons.get(i).setTouchable(Touchable.enabled);
                towerButtons.get(i).getImage().setColor(Color.WHITE);

            } else if (model.getMoney() < i && towerButtons.get(i).isTouchable()) {
                towerButtons.get(i).setTouchable(Touchable.disabled);
                towerButtons.get(i).getImage().setColor(Color.LIGHT_GRAY);
            }
        }
    }

    //Label methods
    private void updateLabels() {
        roundLabel.setText("Round: " + model.getCurrentRound());
    }

    private Label createLabel(String text, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle36BlackBold());
        label.setPosition(1920 - WIDTH / 2F - label.getWidth() / 2, 1080 - label.getHeight() - y);
        return label;
    }

    private Label createPowerUpLabel(String text, float x, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle20Black());
        label.setPosition(x,y);
        return label;
    }

    //Start round button methods
    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1920 - WIDTH / 2F - startRoundButton.getWidth() / 2, 20);

        rightSidePanelController.addStartButtonListener(startRoundButton);
        stage.addActor(startRoundButton);
    }

}
