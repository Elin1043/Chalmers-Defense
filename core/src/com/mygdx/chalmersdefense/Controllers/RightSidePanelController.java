package com.mygdx.chalmersdefense.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Views.GameScreen;

public class RightSidePanelController implements IController {
    private Model model;
    private Stage stage;
    private Button startRoundButton;

    public RightSidePanelController(Model model) {
        this.model = model;
    }

    public void addStage(Stage stage) {
        this.stage = stage;
        createStartRoundButton();
    }

    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1600, 20);

        stage.addActor(startRoundButton); //Add the button to the stage to perform rendering and take input.

        startRoundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.startRoundButtonAction();
            }
        });
    }

    public void resetStartRoundButton() {
        startRoundButton.reset();
    }
}
