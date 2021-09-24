package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.Controllers.MainScreenController;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Views.GameScreen;
import com.mygdx.chalmersdefense.Views.MainScreen;
import com.mygdx.chalmersdefense.Views.ScreenEnum;
import com.mygdx.chalmersdefense.Views.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author
 *
 *
 * @Modified by Elin Forsberg
 *  Added a timer to update Model
 */
public class ChalmersDefense extends Game {
	// The delay (s) between when game data is being updated
	private final float delay = 0.005F;
	// The timer is started with a listener (see below) that executes the statements
	// each step between delays.
	private Timer timer;

	Music music;
	Model model;


	@Override
	public void create() {
		timer =  new Timer();
		model = new Model(this);


		// Creating Controllers
		MainScreenController mainScreenController = new MainScreenController();
		RightSidePanelController rightSidePanelController = new RightSidePanelController(model);

		// Creating Views
		MainScreen mainScreen = new MainScreen(mainScreenController);
		GameScreen gameScreen = new GameScreen(model, rightSidePanelController);

		// Init ScreenManager
		ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);


		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));
		music.setLooping(true);
		music.setVolume(0.2F);
		music.play();

		setupTimer();
		timer.start();

	}

	private void setupTimer() {
		timer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				model.updateModel();
			}
		}, 0, delay);
	}


	public int testJunit(int willDouble) {
		return willDouble * 2;
	}



}
