package com.mygdx.chalmersdefense.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen extends AbstractScreen implements Screen {

    Image virus;

    public GameScreen(){
        super();
        virus = new Image(new Texture("corona_virus_low.png"));
        virus.setPosition(-300, -150);	// This needs to be fixed with later sprites
    }

    @Override
    public void buildStage() {
        addActor(virus);
        super.render(Gdx.graphics.getDeltaTime());
    }
}
