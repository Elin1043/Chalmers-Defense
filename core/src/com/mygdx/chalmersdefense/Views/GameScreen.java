package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen extends AbstractScreen implements Screen {

    private Image virus;
    private Image sideBarBackground;
    private RightSidePanelController rightSidePanelController;
    private Button startRoundButton;

    public GameScreen(RightSidePanelController rightSidePanelController){
        super();
        this.rightSidePanelController = rightSidePanelController;

        virus = new Image(new Texture("corona_virus_low.png"));
        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites

        createRightSidePanel();
    }

    @Override
    public void buildStage() {
        super.render(Gdx.graphics.getDeltaTime());
        addActor(virus);
        addActor(sideBarBackground);

        createStartRoundButton();
    }

    private void createRightSidePanel() {
        sideBarBackground = new Image(new Texture("SideBarBackground.png"));
        sideBarBackground.setPosition(1920 - 320, 0);
    }

    private void createStartRoundButton() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.atlas")); // Load atlas file from skin
        Skin skin = new Skin(Gdx.files.internal("buttons/startRoundButtonSkin/startRoundButtonSkin.json"), atlas); // Create skin object
        startRoundButton = new Button(skin);
        startRoundButton.setPosition(1920 - sideBarBackground.getWidth()/2 - startRoundButton.getWidth()/2, 20);

        this.addActor(startRoundButton); //Add the button to the stage to perform rendering and take input.
        rightSidePanelController.addStartButtonListener(startRoundButton);
    }
}
