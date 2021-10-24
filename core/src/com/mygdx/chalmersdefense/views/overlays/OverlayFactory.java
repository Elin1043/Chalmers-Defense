package com.mygdx.chalmersdefense.views.overlays;

import com.mygdx.chalmersdefense.controllers.overlayControllers.*;
import com.mygdx.chalmersdefense.utilities.Preferences;

/**
 * @author Daniel Persson
 * A factory for creating different overlays
 */
public abstract class OverlayFactory {

    /**
     * Creates a PauseMenuOverlay
     *
     * @param abstractOverlayController  common controller to use
     * @param pauseMenuOverlayController specific controller to use
     * @return PauseMenuOverlay object
     */
    public static AbstractOverlay createPauseMenuOverlay(AbstractOverlayController abstractOverlayController, PauseMenuOverlayController pauseMenuOverlayController) {
        return new PauseMenuOverlay(abstractOverlayController, pauseMenuOverlayController);
    }

    /**
     * Creates a SettingsOverlay
     *
     * @param abstractOverlayController common controller to use
     * @param settingsOverlayController specific controller to use
     * @param preferences               reference to game preferences
     * @return SettingsOverlay object
     */
    public static AbstractOverlay createSettingsOverlay(AbstractOverlayController abstractOverlayController, SettingsOverlayController settingsOverlayController, Preferences preferences) {
        return new SettingsOverlay(abstractOverlayController, settingsOverlayController, preferences);
    }

    /**
     * Creates a LostPanelOverlay
     *
     * @param abstractOverlayController  common controller to use
     * @param lostPanelOverlayController specific controller to use
     * @return LostPanelOverlay object
     */
    public static AbstractOverlay createLostPanelOverlay(AbstractOverlayController abstractOverlayController, LostPanelOverlayController lostPanelOverlayController) {
        return new LostPanelOverlay(abstractOverlayController, lostPanelOverlayController);
    }

    /**
     * Creates a WinPanelOverlay
     *
     * @param abstractOverlayController common controller to use
     * @param winPanelOverlayController specific controller to use
     * @return WinPanelOverlay object
     */
    public static AbstractOverlay createWinPanelOverlay(AbstractOverlayController abstractOverlayController, WinPanelOverlayController winPanelOverlayController) {
        return new WinPanelOverlay(abstractOverlayController, winPanelOverlayController);
    }

    /**
     * Creates a InfoOverlay
     *
     * @param abstractOverlayController common controller to use
     * @return InfoOverlay object
     */
    public static AbstractOverlay createInfoOverlay(AbstractOverlayController abstractOverlayController) {
        return new InfoOverlay(abstractOverlayController);
    }
}
