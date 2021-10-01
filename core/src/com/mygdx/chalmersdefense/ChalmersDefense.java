package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.GameScreen;
import com.mygdx.chalmersdefense.views.MainScreen;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

/**
 *  @author
 *
 *
 *  2021-09-16 Modified by Elin Forsberg: Added a timer to update Model
 *  2021-09-23 Modified by Joel Båtsman Hilmersson: Changed timer to use libGDX timer instead of javaswing
 *  2021-09-30 Modified by Joel Båtsman Hilmersson: Moved timer to GameTimer class instead
 */
public class ChalmersDefense extends Game {

	@Override
	public void create() {
		Model model = new Model();

		// Creating Controllers
		MainScreenController mainScreenController = new MainScreenController();

		// Creating Views
		MainScreen mainScreen = new MainScreen(mainScreenController);
		GameScreen gameScreen = new GameScreen(model);

		// Init ScreenManager
		ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

		// Setup of music
		Music music;
		music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));
		music.setLooping(true);
		music.setVolume(0.2F);
		music.play();
	}


	public int testJunit(int willDouble) {
		return willDouble * 2;
	}
}
