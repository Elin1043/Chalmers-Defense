package com.mygdx.chalmersdefense.views;

import com.mygdx.chalmersdefense.controllers.BottomBarPanelController;
import com.mygdx.chalmersdefense.controllers.GameScreenController;
import com.mygdx.chalmersdefense.controllers.MainScreenController;
import com.mygdx.chalmersdefense.controllers.RightSidePanelController;
import com.mygdx.chalmersdefense.model.IViewModel;

public abstract class ScreenFactory {

    public static AbstractScreen CreateMainScreen(IViewModel model, MainScreenController mainScreenController) {
        return new MainScreen(model, mainScreenController);
    }

    public static AbstractScreen CreateGameScreen(IViewModel model, GameScreenController gameScreenController, RightSidePanelController rightSidePanelController, BottomBarPanelController bottomBarPanelController) {
        return new GameScreen(model, gameScreenController, rightSidePanelController, bottomBarPanelController);
    }
}
