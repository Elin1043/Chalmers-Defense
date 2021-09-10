package com.mygdx.chalmersdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Vector;

public class Launcher extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite virus;
	Sprite virus2;
	Sprite virus3;
	Sprite virus4;
	Music music;


	private final Vector2 rotHelper= new Vector2();

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Chalmers_logga.png");


		virus = new Sprite(new Texture("corona_virus_low.png"));
		virus2 = new Sprite(new Texture("corona_virus_low.png"));
		virus3 = new Sprite(new Texture("corona_virus_low.png"));
		virus4 = new Sprite(new Texture("corona_virus_low.png"));

		virus.setPosition(-300, -150);	// This needs to be fixed with later sprites
		virus.setScale(0.15F);					// This too

		virus2.setPosition(50, -150);	// This needs to be fixed with later sprites
		virus2.setScale(0.15F);					// This too

		virus3.setPosition(-300, 40);	// This needs to be fixed with later sprites
		virus3.setScale(0.15F);					// This too

		virus4.setPosition(50, 40);	// This needs to be fixed with later sprites
		virus4.setScale(0.15F);					// This too


		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));

		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();

		Gdx.graphics.setWindowedMode(1920, 1080); // Sets the width and height of the program window
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);
		batch.begin();
		batch.draw(img, 200, 100, 250, 300);

		virus.draw(batch);
		virus2.draw(batch);
		virus3.draw(batch);
		virus4.draw(batch);

		virus.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 600));
		virus2.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 600));
		virus3.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 300, 240));
		virus4.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 1000, 240));
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}


	//rotToX/Y is the coordinates to be rotated to, orgX/Y is the location to be rotated around
	private float getAngle(int rotToX, int rotToY, int orgX, int orgY){
		rotHelper.set(rotToX - orgX, rotToY - orgY);
		return -rotHelper.angleDeg();	// Negative because it just works then :)
	}

	public int testJunit(int willDouble){
		return willDouble * 2;
	}

}
