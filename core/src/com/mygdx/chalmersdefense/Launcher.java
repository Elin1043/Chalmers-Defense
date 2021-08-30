package com.mygdx.chalmersdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Launcher extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Chalmers_logga.png");
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
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}
}
