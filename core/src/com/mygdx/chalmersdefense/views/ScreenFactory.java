package com.mygdx.chalmersdefense.views;

import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.IViewModel;
import com.mygdx.chalmersdefense.views.gameScreenViews.GameScreen;

/**
 * @author Daniel Persson
 * A factory for creating different screens
 */
public abstract class ScreenFactory {

    /**
     * Creates a MainScreen
     * @param model to get view data from
     * @param mainScreenController reference to specific controller
     * @return MainScreen object
     */
    public static AbstractScreen createMainScreen(IViewModel model, MainScreenController mainScreenController) {
        return new MainScreen(model, mainScreenController);
    }

    /**
     * Creates a GameScreen
     * @param model to get view data from
     * @param gameScreenController reference to specific controller
     * @param rightSidePanelController reference to controller for RightSidePanel
     * @param bottomBarPanelController reference to controller for BottomBarUpgradePanel
     * @return GameScreen object
     */
    public static AbstractScreen createGameScreen(IViewModel model, GameScreenController gameScreenController, RightSidePanelController rightSidePanelController, BottomBarPanelController bottomBarPanelController) {
        return new GameScreen(model, gameScreenController, rightSidePanelController, bottomBarPanelController);
    }
}
