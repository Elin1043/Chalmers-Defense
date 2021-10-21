package com.mygdx.chalmersdefense.views.overlays;

import com.mygdx.chalmersdefense.controllers.overlays.*;
import com.mygdx.chalmersdefense.utilities.Preferences;

public abstract class OverlayFactory {

    public static AbstractOverlay CreatePauseMenuOverlay(AbstractOverlayController abstractOverlayController, PauseMenuOverlayController pauseMenuOverlayController) {
        return new PauseMenuOverlay(abstractOverlayController, pauseMenuOverlayController);
    }

    public static AbstractOverlay CreateSettingsOverlay(AbstractOverlayController abstractOverlayController, SettingsOverlayController settingsOverlayController, Preferences preferences) {
        return new SettingsOverlay(abstractOverlayController, settingsOverlayController, preferences);
    }

    public static AbstractOverlay CreateLostPanelOverlay(AbstractOverlayController abstractOverlayController, LostPanelOverlayController lostPanelOverlayController) {
        return new LostPanelOverlay(abstractOverlayController, lostPanelOverlayController);
    }

    public static AbstractOverlay CreateWinPanelOverlay(AbstractOverlayController abstractOverlayController, WinPanelOverlayController winPanelOverlayController) {
        return new WinPanelOverlay(abstractOverlayController, winPanelOverlayController);
    }

    public static AbstractOverlay CreateInfoOverlay(AbstractOverlayController abstractOverlayController) {
        return new InfoOverlay(abstractOverlayController);
    }
}
