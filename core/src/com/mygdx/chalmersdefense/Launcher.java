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
	Music music;
	Vector2 test;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Chalmers_logga.png");
		virus = new Sprite(new Texture("corona_virus_low.png"));
		test = new Vector2();

		virus.setPosition(-300, -150);
		virus.setScale(0.15F);



		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));

		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();

		Gdx.graphics.setWindowedMode(1280, 900); // Sets the width and height of the program window
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);
		batch.begin();
		batch.draw(img, 200, 100, 250, 300);
		virus.draw(batch);


		virus.setRotation(getAngle(Gdx.input.getX(), Gdx.input.getY(), 200, 200));
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}

	private float getAngle(int rotToX, int rotToY, int orgX, int orgY){
		test.set(rotToX - orgX, rotToY - orgY);
		return test.angleDeg();
	}

}
