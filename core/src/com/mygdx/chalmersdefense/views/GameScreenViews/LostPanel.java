package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.utilities.FontFactory;

import static com.badlogic.gdx.graphics.GL20.*;


/**
 * @author Daniel Persson
 *
 */
public class LostPanel {
    private Stage stage;
    private GameScreenController gameScreenController;
    private final float WIDTH = 810;
    private final float HEIGHT = 400;

    private final TextureAtlas lostButtonTexture = new TextureAtlas(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.atlas")); // Load atlas file from skin
    private final Skin lostButtonSkin = new Skin(Gdx.files.internal("buttons/lostGameButtonSkin/lostGameButtonSkin.json"), lostButtonTexture); // Create skin object

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Group lostPanelGroup = new Group();

    private final Image backgroundImage = new Image(new Texture("GameScreen/LostPanelBackgroundImage.png"));

    private final Label title = new Label("You Lost :'(", FontFactory.getLabelStyle36BlackBold());
    private final Label mainText = new Label("The evil Corona viruses have won. Chalmers have fallen and will now be the center of the new wave of even more powerful virus variants.", FontFactory.getLabelStyle18Black());

    private final Button mainMenuButton = new Button(lostButtonSkin);
    private final Label mainMenuButtonText = new Label("Main menu", FontFactory.getLabelStyle24BlackSemiBold());

    private final Button tryAgainButton = new Button(lostButtonSkin);
    private final Label tryAgainButtonText = new Label("Try again", FontFactory.getLabelStyle24BlackSemiBold());

    public LostPanel(Stage stage, GameScreenController gameScreenController) {
        this.stage = new Stage(stage.getViewport());
        this.gameScreenController = gameScreenController;
    }

    public void initialize() {
        stage.addActor(lostPanelGroup);
        lostPanelGroup.addActor(backgroundImage);
        lostPanelGroup.addActor(title);
        lostPanelGroup.addActor(mainText);
        lostPanelGroup.addActor(mainMenuButton);
        mainMenuButton.addActor(mainMenuButtonText);
        lostPanelGroup.addActor(tryAgainButton);
        tryAgainButton.addActor(tryAgainButtonText);

        backgroundImage.setPosition(stage.getWidth()/2 - WIDTH/2, stage.getHeight()/2 - HEIGHT/2);

        title.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth() / 2 - title.getWidth() / 2,
            backgroundImage.getY() + backgroundImage.getHeight() - 100);

        mainText.setWrap(true);
        mainText.setWidth(720);
        mainText.setAlignment(Align.center);
        mainText.setPosition(
                backgroundImage.getX() + backgroundImage.getWidth() / 2 - 720 / 2,
                backgroundImage.getY() + backgroundImage.getHeight() - 150);

        gameScreenController.addLostPanelMainMenuClickListener(mainMenuButton);
        mainMenuButton.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth()/4 - mainMenuButton.getWidth()/2,
            backgroundImage.getY() + 65);
        mainMenuButtonText.setPosition(mainMenuButton.getWidth()/2 - mainMenuButtonText.getWidth()/2, mainMenuButton.getHeight()/2 - mainMenuButtonText.getHeight()/2 + 5);

        gameScreenController.addLostPanelTryAgainClickListener(tryAgainButton);
        tryAgainButton.setPosition(
            backgroundImage.getX() + backgroundImage.getWidth() * 3/4 - mainMenuButton.getWidth()/2,
            backgroundImage.getY() + 65);
        tryAgainButtonText.setPosition(tryAgainButton.getWidth()/2 - tryAgainButtonText.getWidth()/2, tryAgainButton.getHeight()/2 - tryAgainButtonText.getHeight()/2 + 5);

        lostPanelGroup.setVisible(false);
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);

        // Generate gray transparent overlay background
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(20 / 255F, 20 / 255F, 20 / 255F, 0.6F));
        shapeRenderer.rect(0, 0, stage.getWidth(), stage.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);

        stage.act();
        stage.draw();

        lostPanelGroup.setVisible(true);

    }

    public void hideLostPanelGroup() {
        lostPanelGroup.setVisible(false);
    }
}
