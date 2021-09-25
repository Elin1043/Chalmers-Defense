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
	// The delay (ms) corresponds to 20 updates a sec (hz)
	private final int delay = 5;
	// The timer is started with an listener (see below) that executes the statements
	// each step between delays.
	private Timer timer;

	Music music;
	Model model;


	@Override
	public void create() {
		timer = new Timer(delay, new TimerListener());

		model = new Model(this);


		// Creating Controllers
		MainScreenController mainScreenController = new MainScreenController();

		// Creating Views
		MainScreen mainScreen = new MainScreen(mainScreenController);
		GameScreen gameScreen = new GameScreen(model);

		// Init ScreenManager
		ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);


		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));
		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();
		timer.start();

	}



	public int testJunit(int willDouble) {
		return willDouble * 2;
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			model.updateModel();
			//gameScreen.update();
		}
	}

}
