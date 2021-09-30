package com.mygdx.chalmersdefense.utilities;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.model.IUpdateModel;

/**
 * @author Joel Båtsman Hilmersson
 *
 * A class containing metods related to the
 */
public class GameTimer {
    private final Timer timer = new Timer();
    private float delay = 0.005F;
    private final IUpdateModel model;

    public GameTimer(IUpdateModel model) {
        this.model = model;
    }

    /**
     * Starts the timer that updates model (Effectively un-pauses the game)
     */
    public void startUpdateTimer() {

    }

    /**
     * Stops the timer that updates model (Effectively pauses the game state)
     */
    public void stopUpdateTimer() {

    }

    /**
     * Change model update speed to run simulation faster or slower based on current speed
     */
    public void changeUpdateSpeed() {

    }
}
