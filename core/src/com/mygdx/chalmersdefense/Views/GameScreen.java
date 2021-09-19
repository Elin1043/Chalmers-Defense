package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen extends AbstractScreen implements Screen {

    private Image virus;
    private Image sideBarBackground;
    private RightSidePanelController rightSidePanelController;
    private Button startRoundButton;
    private Label towerLabel;
    private LabelStyle labelStyleBlack36;

    public GameScreen(RightSidePanelController rightSidePanelController){
        super();
        this.rightSidePanelController = rightSidePanelController;
        labelStyleBlack36 = generateLabelStyle(36, Color.BLACK);

        virus = new Image(new Texture("corona_virus_low.png"));
        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites

        createRightSidePanel();

        createTowerLabel();
    }

    @Override
    public void buildStage() {
        super.render(Gdx.graphics.getDeltaTime());
        addActor(virus);
        addActor(sideBarBackground);
        addActor(towerLabel);

        createStartRoundButton();
    }

    private void createTowerLabel() {
        towerLabel = new Label("Towers", labelStyleBlack36);
        towerLabel.setPosition(1920 - sideBarBackground.getWidth()/2 - towerLabel.getWidth()/2, 1080 - towerLabel.getHeight() - 20);
    }

    private BitmapFont generateBitmapFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/CenturyGothic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font36 = generator.generateFont(parameter);
        generator.dispose();
        return font36;
    }

    private LabelStyle generateLabelStyle(int size, Color color){
        BitmapFont font36 = generateBitmapFont(size);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font36;
        labelStyle.fontColor = color;
        return labelStyle;
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
