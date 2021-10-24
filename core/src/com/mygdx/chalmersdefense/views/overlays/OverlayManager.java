package com.mygdx.chalmersdefense.views.overlays;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

/**
 * @author Daniel Persson
 * A singleton class for switching between overlays
 */
final public class OverlayManager {
    private static OverlayManager instance; // Instance of OverlayManager

    // All overlays. Maybe move to separate class OverlayManager
    private AbstractOverlay pauseMenuOverlay;
    private AbstractOverlay settingsOverlay;
    private AbstractOverlay lostPanelOverlay;
    private AbstractOverlay winPanelOverlay;
    private AbstractOverlay infoOverlay;

    private AbstractOverlay currentOverlay;  // Currently open overlay
    private AbstractOverlay prevOverlay;     // Previous open overlay

    /**
     * Singleton constructor
     */
    private OverlayManager() {}

    /**
     * Returns this instance
     *
     * @return the only ScreenManager instance
     */
    public static OverlayManager getInstance() {
        if (instance == null) {
            instance = new OverlayManager();
        }
        return instance;
    }

    /**
     * Initialize the different screens
     *
     */
    public void initialize(AbstractOverlay pauseMenuOverlay, AbstractOverlay settingsOverlay, AbstractOverlay lostPanelOverlay, AbstractOverlay winPanelOverlay, AbstractOverlay infoOverlay) {
        this.pauseMenuOverlay = pauseMenuOverlay;
        this.settingsOverlay = settingsOverlay;
        this.lostPanelOverlay = lostPanelOverlay;
        this.winPanelOverlay = winPanelOverlay;
        this.infoOverlay = infoOverlay;
    }

    /**
     * Shows the screen based on inputted ScreenEnum
     *
     * @param overlayEnum which screen to switch to
     * @param currentScreen to set currentOverlay stage to
     */
    public void showOverlay(ScreenOverlayEnum overlayEnum, Stage currentScreen) {
        // Render current overlay to be shown
        currentOverlay = getOverlay(overlayEnum);

        if (currentOverlay == null && prevOverlay != null) prevOverlay.hideOverlay();
        if (currentOverlay != null && currentOverlay != prevOverlay) {
            currentOverlay.setStage(currentScreen);
        }
        prevOverlay = currentOverlay;
    }

    /**
     * Returns AbstractOverlay object depending on overlayEnum
     */
    private AbstractOverlay getOverlay(ScreenOverlayEnum overlayEnum) {
        return switch (overlayEnum) {
            case PAUSE_MENU -> pauseMenuOverlay;
            case WINPANEL -> winPanelOverlay;
            case LOSEPANEL -> lostPanelOverlay;
            case SETTINGS -> settingsOverlay;
            case INFO -> infoOverlay;
            case NONE -> null;
        };
    }

    /**
     * Get currently showing overlay
     * @return overlay object
     */
    public AbstractOverlay getCurrentOverlay() {
        return currentOverlay;
    }
}
