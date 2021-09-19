package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Views.ScreenEnum;
import com.mygdx.chalmersdefense.Views.ScreenManager;

public class ChalmersDefense extends Game {

	Music music;
	Model model;

	@Override
	public void create () {
		model = new Model(this);

		ScreenManager.getInstance().initialize(this, model);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));
		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();

		//Gdx.graphics.setWindowedMode(1920, 1080); // Sets the width and height of the program window
	}



	public int testJunit(int willDouble){
		return willDouble * 2;
	}

}
