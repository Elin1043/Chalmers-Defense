package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.GameScreen;
import com.mygdx.chalmersdefense.views.MainScreen;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

import javax.swing.*;
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

		// Creating Views
		MainScreen mainScreen = new MainScreen(mainScreenController);
		GameScreen gameScreen = new GameScreen(model);

		// Init ScreenManager
		ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);


		music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));
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
