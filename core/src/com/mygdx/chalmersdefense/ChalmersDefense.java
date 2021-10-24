package com.mygdx.chalmersdefense;

import com.badlogic.gdx.Game;
import com.mygdx.chalmersdefense.controllers.*;
import com.mygdx.chalmersdefense.controllers.overlayControllers.*;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.views.*;
import com.mygdx.chalmersdefense.views.overlays.*;

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
 * 2021-10-14 Modified by Daniel Persson: Moved controller creation to this class
 * 2021-10-20 Modified by Jenny Carlsson: Added info view
 */
final public class ChalmersDefense extends Game {

    @Override
    public void create() {
        Preferences preferences = new Preferences();
        Model model = new Model(preferences);

        // Creating Controllers
        MainScreenController mainScreenController = new MainScreenController(model);

        GameScreenController gameScreenController = new GameScreenController(model);
        RightSidePanelController rightSidePanelController = new RightSidePanelController(model);
        BottomBarPanelController bottomBarPanelController = new BottomBarPanelController(model);

        AbstractOverlayController abstractOverlayController = new AbstractOverlayController(model);
        PauseMenuOverlayController pauseMenuOverlayController = new PauseMenuOverlayController(model);
        SettingsOverlayController settingsOverlayController = new SettingsOverlayController(model, preferences);
        LostPanelOverlayController lostPanelOverlayController = new LostPanelOverlayController(model);
        WinPanelOverlayController winPanelOverlayController = new WinPanelOverlayController(model);

        // Creating Views
        AbstractScreen mainScreen = ScreenFactory.createMainScreen(model, mainScreenController);
        AbstractScreen gameScreen = ScreenFactory.createGameScreen(model, gameScreenController, rightSidePanelController, bottomBarPanelController);

        AbstractOverlay pauseMenuOverlay = OverlayFactory.createPauseMenuOverlay(abstractOverlayController, pauseMenuOverlayController);
        AbstractOverlay settingsMenuOverlay = OverlayFactory.createSettingsOverlay(abstractOverlayController, settingsOverlayController, preferences);
        AbstractOverlay lostPanelOverlay = OverlayFactory.createLostPanelOverlay(abstractOverlayController, lostPanelOverlayController);
        AbstractOverlay winPanelOverlay = OverlayFactory.createWinPanelOverlay(abstractOverlayController, winPanelOverlayController);
        AbstractOverlay infoOverlay = OverlayFactory.createInfoOverlay(abstractOverlayController);

        // Sound
        new Sounds(preferences);

        // Init ScreenManager
        ScreenManager.getInstance().initialize(this, mainScreen, gameScreen);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

        // Init OverlayManager
        OverlayManager.getInstance().initialize(pauseMenuOverlay, settingsMenuOverlay, lostPanelOverlay, winPanelOverlay, infoOverlay);

    }
}
