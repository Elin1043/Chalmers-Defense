package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Model.Model;

public class GameScreen extends AbstractScreen implements Screen {

    Image virus;
    private RightSidePanelController rightSidePanelController;
    private Button startRoundButton;

    public GameScreen(Model model){
        super();
        rightSidePanelController = new RightSidePanelController(model);
        createStartRoundButton();
        virus = new Image(new Texture("corona_virus_low.png"));
        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites
    }

    @Override
    public void buildStage() {
        addActor(virus);
        super.render(Gdx.graphics.getDeltaTime());
    }

    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1600, 20);

        this.addActor(startRoundButton); //Add the button to the stage to perform rendering and take input.
        rightSidePanelController.addStartButtonListener(startRoundButton);
    }
}
