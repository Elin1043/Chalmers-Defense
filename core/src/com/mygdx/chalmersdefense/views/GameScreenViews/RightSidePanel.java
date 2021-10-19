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
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.utilities.FontFactory;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class used to render the right panel
 * <p>
 * 2021-10-05 Created by Daniel Persson <br>
 * 2021-10-15 Modified by Elin Forsberg: Added methods for powerUps
 */
final public class RightSidePanel {
    private final int WIDTH = 320;

    private final Stage stage;
    private final RightSidePanelController rightSidePanelController;
    private final IViewModel model;


    private final Label towerLabel = createLabel("Towers", 20);
    private final Label powerUpLabel = createLabel("Power-ups", 620);


    private final HashMap<Integer, ImageButton> towerButtons = new HashMap<>();
    private final HashMap<Button, Integer> powerUpButtons = new HashMap<>();

    private final ImageButton smurfButton = createRightPanelButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1616, 830, "smurf");
    private final ImageButton chemistButton = createRightPanelButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1766, 830, "chemist");
    private final ImageButton electroButton = createRightPanelButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1766, 650, "electro");
    private final ImageButton hackerButton = createRightPanelButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1616, 650, "hacker");
    private final ImageButton mechButton = createRightPanelButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1616, 470, "mech");
    private final ImageButton ecoButton = createRightPanelButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1766, 470, "eco");


    private final Button cleanHandsPowerUpButton = createPowerUpButton("CleanHands");
    private final Button vaccinatedPowerUpButton = createPowerUpButton("Vaccination");
    private final Button maskedUpPowerUpButton = createPowerUpButton("MaskedUp");


    private final Label cleanHandsTimerLabel = createPowerUpTimeLabel("10",1615, 355);
    private final Label maskedUpTimerLabel = createPowerUpTimeLabel("10",1615, 275);
    private final Label vaccinatedTimerLabel = createPowerUpTimeLabel("10",1615, 187);

    private final Label cleanHandsLabel = createPowerUpLabel("Clean hands",1685, 385);
    private final Label maskedUpLabel = createPowerUpLabel("Masked-up",1685, 300);
    private final Label vaccinatedLabel = createPowerUpLabel("Vaccinated",1685, 216);

    private final Label cleanHandsLabelDesc = createPowerUpDesc("Triples attack speed of your towers for 5 sec", 352);
    private final Label maskedUpLabelDesc = createPowerUpDesc("Increases range of your towers for 20 sec", 268);
    private final Label vaccinatedLabelDesc = createPowerUpDesc("Fires a vaccination storm to damage all viruses", 184);

    private final Label cleanHandsLabelPrice = createPowerUpPriceLabel("$300",1900, 385);
    private final Label maskedUpLabelPrice = createPowerUpPriceLabel("$100",1900, 300);
    private final Label vaccinatedLabelPrice = createPowerUpPriceLabel("$500",1900, 216);

    public RightSidePanel(Stage stage, IViewModel model, RightSidePanelController rightSidePanelController) {
        this.stage = new Stage(stage.getViewport());
        this.model = model;
        this.rightSidePanelController = rightSidePanelController;

        initialize();
    }

    private void initialize() {
        towerButtons.put(100, smurfButton);
        towerButtons.put(200, chemistButton);
        towerButtons.put(300, hackerButton);
        towerButtons.put(400, electroButton);
        towerButtons.put(500, mechButton);
        towerButtons.put(600, ecoButton);

        initializePowerUpButtons();

        stage.addActor(smurfButton);
        stage.addActor(chemistButton);
        stage.addActor(hackerButton);
        stage.addActor(electroButton);
        stage.addActor(mechButton);
        stage.addActor(ecoButton);

        stage.addActor(towerLabel);
        stage.addActor(powerUpLabel);

        stage.addActor(cleanHandsTimerLabel);
        stage.addActor(maskedUpTimerLabel);
        stage.addActor(vaccinatedTimerLabel);

        stage.addActor(cleanHandsLabel);
        stage.addActor(maskedUpLabel);
        stage.addActor(vaccinatedLabel);

        stage.addActor(cleanHandsLabelDesc);
        stage.addActor(maskedUpLabelDesc);
        stage.addActor(vaccinatedLabelDesc);

        stage.addActor(cleanHandsLabelPrice);
        stage.addActor(maskedUpLabelPrice);
        stage.addActor(vaccinatedLabelPrice);


        addButtonListener();

        createStartRoundButton();

    }



    private void initializePowerUpButtons(){
        powerUpButtons.put(maskedUpPowerUpButton,100);
        powerUpButtons.put(cleanHandsPowerUpButton,300);
        powerUpButtons.put(vaccinatedPowerUpButton,500 );

        cleanHandsPowerUpButton.setPosition(1620, 329);
        cleanHandsPowerUpButton.setName("cleanHands");

        maskedUpPowerUpButton.setPosition(1620, 245);
        maskedUpPowerUpButton.setName("maskedUp");

        vaccinatedPowerUpButton.setPosition(1620, 161);
        vaccinatedPowerUpButton.setName("vaccinated");


        stage.addActor(cleanHandsPowerUpButton);
        stage.addActor(maskedUpPowerUpButton);
        stage.addActor(vaccinatedPowerUpButton);
    }

    /**
     * Method used to render right panel to the screen
     */
    public void render() {
        checkAffordableTowers();
        checkPowerUpButtonCooldown();



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

    private Button createPowerUpButton(String name){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/powerUpButtons/" + name + "Skin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/powerUpButtons/" + name + "Skin.json"), atlas); // Create skin object

        return new Button(skin);
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

    private void checkPowerUpButtonCooldown(){
        int[] timers = model.getPowerUpTimer();
        boolean[] active = model.getPowerUpActive();


        updatePowerUpButtons(timers[0],  cleanHandsPowerUpButton, cleanHandsTimerLabel, active[0]);
        updatePowerUpButtons(timers[1], maskedUpPowerUpButton, maskedUpTimerLabel, active[1]);
        updatePowerUpButtons(timers[2], vaccinatedPowerUpButton, vaccinatedTimerLabel, active[2]);

    }

    private void updatePowerUpButtons(int timer, Button powerUpButton, Label label, boolean active){
        if(timer == -1){
            label.setVisible(false);
            powerUpButton.setTouchable(Touchable.enabled);
            powerUpButton.setDisabled(false);
            powerUpButton.setColor(Color.WHITE);
            checkAffordablePowerUp(powerUpButton);

        }
        else if (active){
            powerUpButton.setTouchable(Touchable.disabled);
            powerUpButton.setColor(new Color(Color.GOLD));
            label.setVisible(true);
            label.setText(timer + 1);
        }
        else{
            powerUpButton.setTouchable(Touchable.disabled);
            powerUpButton.setColor(Color.LIGHT_GRAY);


            label.setVisible(true);
            label.setText(timer + 1);
        }
    }


    //Checks what powerUps the player can afford
    private void checkAffordablePowerUp(Button powerUpButton){
        int i = powerUpButtons.get(powerUpButton);
        if (model.getMoney() >=i  && !powerUpButton.isTouchable()) {
            powerUpButton.setColor(Color.WHITE);

        } else if (model.getMoney() < i && powerUpButton.isTouchable()) {
            powerUpButton.setTouchable(Touchable.disabled);
            powerUpButton.setDisabled(true);
        }


    }

    //Checks what towers the player can afford
    private void checkAffordableTowers() {
        for (Integer i : towerButtons.keySet()) {
            if (model.getMoney() >= i && !towerButtons.get(i).isTouchable()) {
                towerButtons.get(i).setTouchable(Touchable.enabled);
                towerButtons.get(i).getImage().setColor(Color.WHITE);

            } else if (model.getMoney() < i && towerButtons.get(i).isTouchable()) {
                towerButtons.get(i).setTouchable(Touchable.disabled);
                towerButtons.get(i).getImage().setColor(new Color(Color.LIGHT_GRAY));
            }
        }
    }

    //Label methods

    private Label createLabel(String text, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle36BlackBold());
        label.setPosition(1920 - WIDTH / 2F - label.getWidth() / 2, 1080 - label.getHeight() - y);
        return label;
    }

    private Label createPowerUpLabel(String text, float x, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle20BlackSemiBold());
        label.setPosition(x,y);
        return label;
    }

    private Label createPowerUpPriceLabel(String text, float x, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle20BlackSemiBold());
        label.setAlignment(Align.right);
        label.setPosition(x - label.getWidth(),y);
        return label;
    }

    private Label createPowerUpTimeLabel(String text, float x, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle34SkyBold());
        label.setAlignment(Align.center);
        label.setPosition(x + label.getWidth()/2, y);
        return label;
    }

    private Label createPowerUpDesc(String text, float y) {
        Label label = new Label(text, FontFactory.getLabelStyle18Black());
        label.setPosition(1688,y);
        label.setWrap(true);
        label.setWidth(220);
        return label;
    }

    //Start round button methods
    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        Button startRoundButton = new Button(skin);
        startRoundButton.setPosition(1920 - WIDTH / 2F - startRoundButton.getWidth() / 2, 20);

        rightSidePanelController.addStartButtonListener(startRoundButton);
        stage.addActor(startRoundButton);
    }

}
