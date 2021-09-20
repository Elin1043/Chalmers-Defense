package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Controllers.TowerButtonListener;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Model.Tower;

public class GameScreen extends AbstractScreen implements Screen {

    Image virus;
    private RightSidePanelController rightSidePanelController;
    private Button startRoundButton;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Model model;

    ImageButton smurfButton;
    ImageButton chemistButton;
    ImageButton electroButton;
    ImageButton hackerButton;
    ImageButton meckButton;
    ImageButton ecobutton;

    public GameScreen(RightSidePanelController rightSidePanelController, Model model){
        super();
        this.rightSidePanelController = rightSidePanelController;
        this.model = model;
        createStartRoundButton();


        smurfButton = createTowerButtons(new Texture("buttons/TowerButtons/SmurfButton.png"), 1620, 830, "smurf");
        chemistButton = createTowerButtons(new Texture("buttons/TowerButtons/ChemistButton.png"), 1770, 830, "chemist");
        hackerButton = createTowerButtons(new Texture("buttons/TowerButtons/HackerButton.png"), 1620, 650, "hacker");
        electroButton = createTowerButtons(new Texture("buttons/TowerButtons/ElectroButton.png"), 1770, 650, "electro");
        meckButton = createTowerButtons(new Texture("buttons/TowerButtons/MeckoButton.png"), 1620, 470, "meck");
        ecobutton = createTowerButtons(new Texture("buttons/TowerButtons/EcoButton.png"), 1770, 470, "eco");
    }

    @Override
    public void buildStage() {
        addActor(smurfButton);
        addActor(chemistButton);
        addActor(hackerButton);
        addActor(electroButton);
        addActor(meckButton);
        addActor(ecobutton);

        super.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        super.getBatch().begin();
        renderTowers();
        super.getBatch().end();

    }


    public void addTowerButtonListener(DragListener listener){
        smurfButton.addListener(listener);
        chemistButton.addListener(listener);
        hackerButton.addListener(listener);
        electroButton.addListener(listener);
        meckButton.addListener(listener);
        ecobutton.addListener(listener);
    }

    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1600, 20);

        this.addActor(startRoundButton); //Add the button to the stage to perform rendering and take input.
        rightSidePanelController.addStartButtonListener(startRoundButton);
    }

    private ImageButton createTowerButtons(Texture texture, int x, int y, String name){
        TextureRegion towerButtonTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable towerButtonRegDrawable = new TextureRegionDrawable(towerButtonTextureRegion);
        ImageButton towerButton = new ImageButton(towerButtonRegDrawable); //Set the button up
        towerButton.setPosition(x, y);
        towerButton.setName(name);


        this.addActor(towerButton); //Add the button to the stage to perform rendering and take input.
        return towerButton;


    }

    private void renderTowers() {

        for (Tower tower: model.getTowers()) {
            tower.getSprite().draw(super.getBatch());

            if(!tower.isPlaced() && !tower.getCollision()){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();


            }
            else if(!tower.isPlaced() && tower.getCollision()){
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.RED);
                tower.drawRadius(shapeRenderer);
                shapeRenderer.end();

            }

        }

    }
}
