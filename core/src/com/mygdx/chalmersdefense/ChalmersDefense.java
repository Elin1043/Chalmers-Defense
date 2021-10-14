package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;

import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.controllers.overlays.LostPanelOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.PauseMenuOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.SettingsOverlayController;
import com.mygdx.chalmersdefense.controllers.overlays.WinPanelOverlayController;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.views.*;
import com.mygdx.chalmersdefense.views.GameScreenViews.*;
import com.mygdx.chalmersdefense.views.overlays.OverlayManager;

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
 * 2021-10-13 Modified by Daniel Persson: Added preferences
 */
public class ChalmersDefense extends Game {

    @Override
    public void create() {
        Model model = new Model();
        Preferences preferences = new Preferences();

        // Creating Controllers
        MainScreenController mainScreenController = new MainScreenController();

        GameScreenController gameScreenController = new GameScreenController(model);
        RightSidePanelController rightSidePanelController = new RightSidePanelController(model);
        BottomBarPanelController bottomBarPanelController = new BottomBarPanelController(model);

        PauseMenuOverlayController pauseMenuOverlayController = new PauseMenuOverlayController(model);
        SettingsOverlayController settingsOverlayController = new SettingsOverlayController(model, preferences);
        LostPanelOverlayController lostPanelOverlayController = new LostPanelOverlayController(model);
        WinPanelOverlayController winPanelOverlayController = new WinPanelOverlayController(model);

        // Creating Views
        AbstractScreen mainScreen = new MainScreen(mainScreenController);
        AbstractScreen gameScreen = new GameScreen(model, gameScreenController, rightSidePanelController, bottomBarPanelController, settingsOverlayController);

        AbstractOverlay pauseMenuOverlay = new PauseMenuOverlay(pauseMenuOverlayController);
        AbstractOverlay settingsMenuOverlay = new SettingsOverlay(settingsOverlayController);
        AbstractOverlay lostPanelOverlay = new LostPanelOverlay(lostPanelOverlayController);
        AbstractOverlay winPanelOverlay = new WinPanelOverlay(winPanelOverlayController);

        new Sounds(preferences);

        // Init ScreenManager
        ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

        // Init OverlayManager
        OverlayManager.getInstance().initialize(pauseMenuOverlay, settingsMenuOverlay, lostPanelOverlay, winPanelOverlay);

    }
}
