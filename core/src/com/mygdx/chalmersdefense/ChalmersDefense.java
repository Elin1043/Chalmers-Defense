package com.mygdx.chalmersdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.chalmersdefense.Views.MainScreen;

public class ChalmersDefense extends Game {
	private MainScreen mainScreen;

	Music music;
	Camera camera;
	Viewport viewport;
	SpriteBatch batch;


	@Override
	public void create () {
		camera = new OrthographicCamera(1920, 1080);
		viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
		viewport.setCamera(camera);
		batch = new SpriteBatch();
		mainScreen = new MainScreen(this,viewport, batch);
		setScreen(mainScreen);


		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));
		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();

		//Gdx.graphics.setWindowedMode(1920, 1080); // Sets the width and height of the program window
	}

	@Override
	public void render () {

		camera.update();
		ScreenUtils.clear(255, 255, 255, 1);
		batch.setProjectionMatrix(camera.combined); // Renders based on window pixels and not screen pixels.

		batch.begin();

		super.render();

		batch.end();

//		mainScreen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}




	public int testJunit(int willDouble){
		return willDouble * 2;
	}

}
