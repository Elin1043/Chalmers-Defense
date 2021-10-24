package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.utilities.RangeCircle;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for model to implement only expose view methods to the view
 */
public interface IViewModel {

    /**
     * Return the tower that was clicked
     *
     * @return clicked tower
     */
    IMapObject getClickedTower();

    /**
     * Return the circle used for range
     *
     * @return the circle
     */
    RangeCircle getRangeCircle();

    /**
     * A delegation for getting title of a tower upgrade.
     *
     * @param towerName    The towers name
     * @param upgradeLevel what upgrade to get title of
     * @return a String with towers upgrade title depending on upgrade level.
     */
    String getTowerUpgradeTitle(String towerName, int upgradeLevel);

    /**
     * A delegation for getting description of a tower upgrade.
     *
     * @param towerName    The towers name
     * @param upgradeLevel what upgrade to get description of
     * @return a String with towers upgrade description depending on upgrade level.
     */
    String getTowerUpgradeDesc(String towerName, int upgradeLevel);

    /**
     * A delegation for getting price of a tower upgrade.
     *
     * @param towerName    The towers name
     * @param upgradeLevel what upgrade to get price of
     * @return a String with towers upgrade price depending on upgrade level.
     */
    Integer getTowerUpgradePrice(String towerName, int upgradeLevel);


    /**
     * Get the sell price of the clicked tower
     *
     * @return price
     */
    int getClickedTowerSellPrice();


    /**
     * Get target mode of clicked tower
     *
     * @return target mode
     */
    String getClickedTowerTargetMode();

    /**
     * Returns which overlay is supposed to show
     *
     * @return which overlay is supposed to show
     */
    ScreenOverlayEnum getCurrentOverlay();

    /**
     * Return the current money value
     *
     * @return the money value
     */
    int getMoney();

    /**
     * Returns the lives left of player
     *
     * @return lives left
     */
    int getLivesLeft();

    /**
     * Returns the current round
     *
     * @return current round
     */
    int getCurrentRound();

    /**
     * Returns winning round
     *
     * @return winning round
     */
    int getWinningRound();

    /**
     * Returns a list of every current active object on the map
     *
     * @return the list of all objects
     */
    List<IMapObject> getAllMapObjects();


    /**
     * Return the active timer of the powerUps
     *
     * @return active timer
     */
    int[] getPowerUpTimer();

    /**
     * Return if the different powerUps are active
     *
     * @return active or not
     */
    boolean[] getPowerUpActive();

    /**
     * Returns if the game is stopped and the map is cleared from viruses
     *
     * @return a boolean for game state
     */
    boolean isGameStopped();

    /**
     * Returns the background image path
     *
     * @return image path
     */
    String getMapImagePath();

    /**
     * Gets if game speed is sped up
     *
     * @return true if game is sped up. Otherwise false
     */
    boolean isGameSpedUp();
}
