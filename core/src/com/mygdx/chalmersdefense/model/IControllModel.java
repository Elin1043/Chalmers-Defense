package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for model to implement only expose controll methods to the controller
 */
public interface IControllModel {

    /**
     * Starts spawning viruses based on which is the current round
     */
    void startRoundPressed();

    /**
     * Creates a new tower when user starts dragging from a tower button.
     *
     * @param towerName the name of the tower
     * @param x         the X-position of the button
     * @param y         the Y-position of the button
     */
    void dragStart(String towerName, float x, float y);

    /**
     * Handles a tower being dragged.
     * Updates the towers position after mouse and check for collision
     *  @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     * @param windowHeight The height of the window
     * @param windowWidth  The width of the window
     */
    void onDrag(float x, float y, int windowHeight, int windowWidth);

    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     *  @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     */
    void dragEnd(float x, float y);

    /**
     * Delegates upgrade method to upgrade class. And decreases players money if upgrade is applied.
     */
    void upgradeClickedTower();

    /**
     * Handles when a placed tower is clicked
     */
    void checkIfTowerClicked(float mouseX, float mouseY);

    /**
     * Resets all model components to starting condition
     */
    void resetModel();

    /**
     * Sets showOverlay to supplied overlay enum
     * @param overlay enum of overlay to show
     */
    void setShowOverlay(ScreenOverlayEnum overlay);

    /**
     * Sell clicked tower
     */
    void sellClickedTower();

    /**
     * Change tower targetMode
     */
    void changeTargetMode(boolean goRight);

    /**
     * Starts the main game timer
     */
    void startGameUpdate();

    /**
     * Stops the main game timer
     */
    void stopGameUpdate();

    /**
     * Handle a powerUp button being clicked
     * @param powerUpName The name of the power-up to be activated
     */
    void powerUpClicked(String powerUpName);
}
