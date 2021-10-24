package com.mygdx.chalmersdefense.model.modelUtilities;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for use by wraped timer object
 */
public interface IGameTimer {

    /**
     * Starts the timer that updates model (Effectively un-pauses the game)
     */
    void startUpdateTimer();

    /**
     * Stops the timer that updates model (Effectively pauses the game state)
     */
    void stopUpdateTimer();

    /**
     * Change model update speed to run simulation faster or slower based on current speed
     */
    void changeUpdateSpeed();

    /**
     * Gets if game speed is sped up
     *
     * @return true if game is sped up. Otherwise false
     */
    boolean isGameSpedUp();
}
