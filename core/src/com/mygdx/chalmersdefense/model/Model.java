package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.Upgrades;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.SpawnViruses;
import com.mygdx.chalmersdefense.utilities.GameTimer;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;

import java.util.List;


/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 * Class handeling all the models in the game.
 *
 * 2021-09-20 Modified by Elin Forsberg: Added methods to handle towers + collisions
 * 2021-09-20 Modified by Joel Båtsman Hilmersson: Made updateVirus loop syncronized
 * 2021-09-22 Modified by Daniel Persson: Added support for storing a clicked tower and added algorithm for finding what tower is being clicked.
 * 2021-09-24 Modified by Elin Forsberg: Added methods to handle projectiles
 * 2021-09-25 Modified by Joel Båtsman Hilmersson: Added support for round system
 * 2021-09-27 Modified by Elin Forsberg: Added methods to handle different attacks from towers
 * 2021-09-27 Modified by Daniel Persson: Added delegation getters for upgrade title, description and price.
 * 2021-09-28 Modified by Everyone: Moved methods to Map class
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Added a specifc timer object
 */

public class Model implements IUpdateModel, IControllModel {
    private final int WINNING_ROUND = 10;
    private final int LIVES = 100;
    private final int START_CAPITAL = 3000;

    private final GameTimer timer = new GameTimer(this);
    private Rounds round = new Rounds(WINNING_ROUND);

    private final Player player = new Player(LIVES, START_CAPITAL); //Change staring capital later. Just used for testing right now
    private final Upgrades upgrades = new Upgrades();


    private final Map map = new Map(player);
    private final SpawnViruses virusSpawner = new SpawnViruses(map.getViruses());

    @Override
    public void updateModel() {
        map.updateMap();
        checkRoundCompleted();
        virusSpawner.decrementSpawnTimer();
    }

    @Override
    public void resetModel() {
        round = new Rounds(WINNING_ROUND);
        player.resetPlayer(LIVES, START_CAPITAL);
        map.resetMap();
        virusSpawner.resetSpawnViruses();
    }

    private void checkRoundCompleted() {
        if (map.isVirusCleared() && !virusSpawner.isSpawning()) {

            player.increaseMoney((100 * (round.getCurrentRound()/2)));

            stopGameUpdate();
            map.roundClear();
        }
    }



    private void startGameUpdate() {
        timer.startUpdateTimer();
    }

    private void stopGameUpdate() {
        timer.stopUpdateTimer();
    }



    @Override
    public void startRoundPressed() {
        if (!virusSpawner.isSpawning() && map.isVirusCleared()) {
            startGameUpdate();
            round.incrementToNextRound();
            virusSpawner.spawnRound(round.getCurrentRound());
        } else {

            timer.changeUpdateSpeed();

        }
    }



    @Override
    public void dragStart(String towerName, float x, float y) {
       map.dragStart(towerName, x, y);
    }

    @Override
    public void onDrag(float buttonWidth, float buttonHeight, float x, float y, int windowHeight, int windowWidth) {
        map.onDrag(buttonWidth, buttonHeight, x, y, windowHeight, windowWidth);
    }

    @Override
    public void dragEnd(float buttonWidth, float buttonHeight, float x, float y) {
        map.dragEnd(buttonWidth, buttonHeight, x, y);
    }


    // Maybe temporary because it sets the object to null. (Remove comment?)
    @Override
    public void checkIfTowerClicked(float x, float y) {
        map.checkIfTowerClicked(x,y);
    }

    public GetRangeCircle getRangeCircle() {
        return map.getRangeCircle();
    }

    public IMapObject getClickedTower() {
        return map.getClickedTower();
    }

    /**
     * A delegation for getting title of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get title of
     * @return a String with towers upgrade title depending on upgrade level.
     */
    public String getTowerUpgradeTitle(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradeTitle(towerName, upgradeLevel);
    }


    /**
     * A delegation for getting description of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get description of
     * @return a String with towers upgrade description depending on upgrade level.
     */
    public String getTowerUpgradeDesc(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradeDesc(towerName, upgradeLevel);
    }

    /**
     * A delegation for getting price of a tower upgrade.
     * @param towerName The towers name
     * @param upgradeLevel what upgrade to get price of
     * @return a String with towers upgrade price depending on upgrade level.
     */
    public Long getTowerUpgradePrice(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradePrice(towerName, upgradeLevel);
    }

    @Override
    public void upgradeClickedTower() {
        if (upgrades.upgradeTower(map.getClickedTower())) {
            player.decreaseMoney(upgrades.getTowerUpgradePrice(map.getClickedTower().getName(), map.getClickedTower().getUpgradeLevel() - 1).intValue());
        }
    }

    public boolean getIsGameLost() {
        return map.getIsGameLost();
    }

    /**
     * Return the current money value
     * @return the money value
     */
    public int getMoney() { return player.getMoney(); }

    /**
     * Returns the lives left of player
     * @return lives left
     */
    public int getLivesLeft() { return player.getLives(); }

    /**
     * Returns the current round
     * @return current round
     */
    public int getCurrentRound() { return round.getCurrentRound(); }



    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    //TODO Remove THIS when not needed
    public List<IVirus> getViruses() {
        return map.getViruses();
    }

    public List<IMapObject> getAllMapObjects() {
        return map.getAllMapObjects();
    }
}
