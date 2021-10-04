package com.mygdx.chalmersdefense.model;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for model to implement to be controlled by a controller
 */
public interface IControllModel {

    /**
     * Starts spawning viruses based on which is the current round
     */
    void startRoundPressed();

    /**
     * Creates a new tower when user starts dragging from a tower button.
     * @param towerName the name of the tower
     * @param x the X-position of the button
     * @param y the Y-position of the button
     */
    void dragStart(String towerName, float x, float y);

    /**
     * Handles a tower being dragged.
     * Updates the towers position after mouse and check for collision
     * @param buttonWidth The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x The X-position of the mouse
     * @param y The Y-position of the mouse
     * @param windowHeight The height of the window
     * @param windowWidth  The width of the window
     */
    void onDrag(float buttonWidth, float buttonHeight, float x, float y, int windowHeight, int windowWidth);

    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     * @param buttonWidth The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x The X-position of the mouse
     * @param y The Y-position of the mouse
     */
    void dragEnd(float buttonWidth, float buttonHeight, float x, float y);

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

}
