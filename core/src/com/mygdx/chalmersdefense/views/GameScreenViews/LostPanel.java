package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.chalmersdefense.utilities.FontFactory;


/**
 * @author Daniel Persson
 *
 */
public class LostPanel {
    private Stage stage;
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final TextureAtlas lostButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.atlas")); // Load atlas file from skin
    private final Skin lostButtonSkin = new Skin(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.json"), lostButtonTexture); // Create skin object

    private final Group lostPanelGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/LostPanelBackgroundImage.png"));

    private final Label title = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
    private final Label mainText = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
    private final Button mainMenuButton = new Button(lostButtonSkin);
    private final Button tryAgainButton = new Button(lostButtonSkin);

    public LostPanel(Stage stage) {
        this.stage = new Stage(stage.getViewport());
    }

    public void initialize() {
        stage.addActor(lostPanelGroup);
        lostPanelGroup.addActor(backgroundImage);
        lostPanelGroup.addActor(title);
        lostPanelGroup.addActor(mainMenuButton);
        lostPanelGroup.addActor(tryAgainButton);

        backgroundImage.setPosition(stage.getWidth()/2 - WIDTH/2, stage.getHeight()/2 - HEIGHT/2);

        title.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
            backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainMenuButton.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth()/4 - mainMenuButton.getWidth()/2,
            backgroundImage.getY() + 100);

        tryAgainButton.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth() * 3/4 - mainMenuButton.getWidth()/2,
            backgroundImage.getY() + 100);

        lostPanelGroup.setVisible(false);
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();

        lostPanelGroup.setVisible(true);

    }

    public void hideLostPanelGroup() {
        lostPanelGroup.setVisible(false);
    }
}
