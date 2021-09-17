package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;

public class GameScreen implements Screen {

    private final ChalmersDefense game;
    Viewport viewport;
    Batch batch;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    Sprite virus;

    public GameScreen(ChalmersDefense game, Batch batch, Viewport viewport){
        this.game = game;
        this.viewport = viewport;
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport); //Set up a stage for the ui

        virus = new Sprite(new Texture("corona_virus_low.png"));

        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites

    }

    public void addRightSidePanelController(RightSidePanelController controller) {
        controller.addStage(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
    }

    @Override
    public void render(float delta) {
        virus.draw(batch);

        createRightSidePanel();

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    private void createRightSidePanel() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xF0FBFF));
        shapeRenderer.rect(Gdx.graphics.getWidth() - 320, 0, 320, Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
