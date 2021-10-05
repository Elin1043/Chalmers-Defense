package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for model to implement only expose view methods to the view
 */
public interface IViewModel {

    IMapObject getClickedTower();

    GetRangeCircle getRangeCircle();

    /**
     * A delegation for getting title of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get title of
     * @return a String with towers upgrade title depending on upgrade level.
     */
    String getTowerUpgradeTitle(String towerName, int upgradeLevel);

    /**
     * A delegation for getting description of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get description of
     * @return a String with towers upgrade description depending on upgrade level.
     */
    String getTowerUpgradeDesc(String towerName, int upgradeLevel);

    /**
     * A delegation for getting price of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get price of
     * @return a String with towers upgrade price depending on upgrade level.
     */
    Long getTowerUpgradePrice(String towerName, int upgradeLevel);

    boolean getIsGameLost();

    /**
     * Returns if WinPanel is supposed to show
     * @return if WinPanel is supposed to show
     */
    boolean showWinPanel();

    /**
     * Return the current money value
     * @return the money value
     */
    int getMoney();

    /**
     * Returns the lives left of player
     * @return lives left
     */
    int getLivesLeft();

    /**
     * Returns the current round
     * @return current round
     */
    int getCurrentRound();

    /**
     * Returns a list of every current active object on the map
     * @return the list of all objects
     */
    List<IMapObject> getAllMapObjects();

    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    //TODO Remove THIS when not needed
    List<IVirus> getViruses();

    //TODO THIS SHOULD NOT BE HERE, remove when all keyboard stuff is removed in view (Keyboard controller is a better idee)
    void startRoundPressed();
}
