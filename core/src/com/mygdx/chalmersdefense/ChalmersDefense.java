package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;

import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.*;

/**
 * @author Daniel Persson
 * @author Elin Forsberg
 * @author Joel Båtsman Hilmersson
 * @author Jenny Carlsson
 *
 * 2021-09-16 Modified by Elin Forsberg: Added a timer to update Model
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: Changed timer to use libGDX timer instead of javaswing
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Moved timer to GameTimer class instead
 */
public class ChalmersDefense extends Game {

    @Override
    public void create() {
        Model model = new Model();

        // Creating Controllers
        MainScreenController mainScreenController = new MainScreenController();

        // Creating Views
        AbstractScreen mainScreen = new MainScreen(mainScreenController);
        AbstractScreen gameScreen = new GameScreen(model);
        new Sounds();

        // Init ScreenManager
        ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);


    }
}
