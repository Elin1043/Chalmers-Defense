package com.mygdx.chalmersdefense.views.overlays;

import com.mygdx.chalmersdefense.views.AbstractScreen;
import com.mygdx.chalmersdefense.views.GameScreenViews.*;
import com.mygdx.chalmersdefense.views.ScreenEnum;
import com.mygdx.chalmersdefense.views.ScreenManager;

public class OverlayManager {
    private static OverlayManager instance;

    // All overlays. Maybe move to separate class OverlayManager
    private AbstractOverlay pauseMenuOverlay;
    private AbstractOverlay settingsOverlay;
    private AbstractOverlay lostPanelOverlay;
    private AbstractOverlay winPanelOverlay;

    private AbstractOverlay currentOverlay;
    private AbstractOverlay prevOverlay;

    private OverlayManager() {
        super();
    }

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
    public void initialize(AbstractOverlay pauseMenuOverlay, AbstractOverlay settingsOverlay, AbstractOverlay lostPanelOverlay, AbstractOverlay winPanelOverlay) {
        this.pauseMenuOverlay = pauseMenuOverlay;
        this.settingsOverlay = settingsOverlay;
        this.lostPanelOverlay = lostPanelOverlay;
        this.winPanelOverlay = winPanelOverlay;
    }

    /**
     * Shows the screen based on inputted ScreenEnum
     *
     * @param overlayEnum which screen to switch to
     */
    public void showOverlay(ScreenOverlayEnum overlayEnum) {
        // Render current overlay to be shown
        AbstractScreen currentScreen = ScreenManager.getInstance().getCurrentScreen();
        currentOverlay = getOverlay(overlayEnum);

        if (currentOverlay == null && prevOverlay != null) prevOverlay.hideOverlay();
        if (currentOverlay != null && currentOverlay != prevOverlay) {
            currentOverlay.setStage(currentScreen);
            //currentOverlay.render();
        }
        prevOverlay = currentOverlay;
    }

    private AbstractOverlay getOverlay(ScreenOverlayEnum overlayEnum) {
        return switch (overlayEnum) {
            case PAUSE_MENU -> pauseMenuOverlay;
            case WINPANEL -> winPanelOverlay;
            case LOSEPANEL -> lostPanelOverlay;
            case SETTINGS -> settingsOverlay;
            case NONE -> null;
        };
    }

    public AbstractOverlay getCurrentOverlay() {
        return currentOverlay;
    }
}
