package com.mygdx.chalmersdefense.views.GameScreenViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author Daniel Persson
 *
 */
public class LostPanel {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Stage stage;
    private float width = 800;
    private final float height = 600;



    public LostPanel(Stage stage) {
        this.stage = stage;
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(150 / 255F, 150 / 255F, 150 / 255F, 0.8F));
        shapeRenderer.rect(stage.getWidth()/2 - width/2, stage.getHeight()/2 - height/2, width, height);
        shapeRenderer.end();
    }
}
