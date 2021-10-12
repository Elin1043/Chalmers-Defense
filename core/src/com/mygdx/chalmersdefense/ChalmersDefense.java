package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;

import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.*;

/**
 * @author Daniel Persson
 * @author Elin Forsberg
 * @author Joel Båtsman Hilmersson
 * @author Jenny Carlsson
 * The starup class of the application
 *
 * 2021-09-16 Modified by Elin Forsberg: Added a timer to update Model <br>
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: Changed timer to use libGDX timer instead of javaswing <br>
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Moved timer to GameTimer class instead <br>
 */
public class ChalmersDefense extends Game {

    @Override
    public void create() {
        Model model = new Model();

        // Creating Controllers
        MainScreenController mainScreenController = new MainScreenController();

        GameScreenController gameScreenController = new GameScreenController(model);
        RightSidePanelController rightSidePanelController = new RightSidePanelController(model);
        BottomBarPanelController bottomBarPanelController = new BottomBarPanelController(model);

        // Creating Views
        AbstractScreen mainScreen = new MainScreen(mainScreenController);
        AbstractScreen gameScreen = new GameScreen(model, gameScreenController, rightSidePanelController, bottomBarPanelController);
        new Sounds();

        // Init ScreenManager
        ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);


    }
}
