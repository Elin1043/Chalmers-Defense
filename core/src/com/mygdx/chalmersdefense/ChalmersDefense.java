package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.chalmersdefense.Controllers.MainScreenController;
import com.mygdx.chalmersdefense.Controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.Controllers.TowerButtonListener;
import com.mygdx.chalmersdefense.Model.Model;
import com.mygdx.chalmersdefense.Views.GameScreen;
import com.mygdx.chalmersdefense.Views.MainScreen;
import com.mygdx.chalmersdefense.Views.ScreenEnum;
import com.mygdx.chalmersdefense.Views.ScreenManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChalmersDefense extends Game {
	private final int delay = 50;
	// The timer is started with an listener (see below) that executes the statements
	// each step between delays.
	private Timer timer;

	Music music;
	Model model;

	GameScreen gameScreen;

	@Override
	public void create () {
		timer = new Timer(delay, new TimerListener());

		model = new Model(this);


		// Creating Controllers
		MainScreenController mainScreenController = new MainScreenController();
		RightSidePanelController rightSidePanelController = new RightSidePanelController(model);

		// Creating Views
		MainScreen mainScreen = new MainScreen(mainScreenController);
		gameScreen = new GameScreen(rightSidePanelController, model);

		// Init ScreenManager
		ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

		//Initialize listeners
		TowerButtonListener towerButtonListener = new TowerButtonListener(model);
		gameScreen.addTowerButtonListener(towerButtonListener);

		music = Gdx.audio.newMusic(Gdx.files.internal("ponggamesound.wav"));
		music.setLooping(true);
		music.setVolume((float) 0.2);
		music.play();
		timer.start();



		//Gdx.graphics.setWindowedMode(1920, 1080); // Sets the width and height of the program window
	}



	public int testJunit(int willDouble){
		return willDouble * 2;
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			model.updateModel();
			gameScreen.update();
		}
	}

}
