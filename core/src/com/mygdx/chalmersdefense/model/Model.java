package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.towers.Upgrades;
import com.mygdx.chalmersdefense.model.viruses.SpawnViruses;
import com.mygdx.chalmersdefense.utilities.GameTimer;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;

import java.util.List;


/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 * <p>
 * Class handeling all the models in the game.
 * <p>
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

public class Model implements IUpdateModel, IControllModel, IViewModel {
    private final int WINNING_ROUND = 10;       // Current vinning round
    private final int LIVES = 100;              // Current amount of starting lives
    private final int START_CAPITAL = 30000;    // Current amount of start capital

    private final GameTimer timer = new GameTimer(this);    // Timer object
    private Rounds round = new Rounds(WINNING_ROUND);              // Round helper

    private final Player player = new Player(LIVES, START_CAPITAL); // Player object
    private final Upgrades upgrades = new Upgrades();   // Class for controlling upgrades


    private final Map map = new Map(player);        // Current map object
    private final SpawnViruses virusSpawner = new SpawnViruses(map.getViruses());   // The class for spawning viruses

    private boolean showWinPanel = false;       // Boolean for views of they should show win panel

    @Override
    public synchronized void updateModel() {
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

            player.increaseMoney((100 * (round.getCurrentRound() / 2)));

            stopGameUpdate();
            map.roundClear();

            if (round.gameWon()) {
                showWinPanel = true;
            }
        }
    }

    @Override
    public void startGameUpdate() {
        timer.startUpdateTimer();
    }

    @Override
    public void stopGameUpdate() {
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


    @Override
    public void checkIfTowerClicked(float x, float y) {
        map.checkIfTowerClicked(x, y);
    }

    @Override
    public GetRangeCircle getRangeCircle() {
        return map.getRangeCircle();
    }

    @Override
    public IMapObject getClickedTower() {
        return map.getClickedTower();
    }

    @Override
    public String getTowerUpgradeTitle(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradeTitle(towerName, upgradeLevel);
    }


    @Override
    public String getTowerUpgradeDesc(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradeDesc(towerName, upgradeLevel);
    }

    @Override
    public Long getTowerUpgradePrice(String towerName, int upgradeLevel) {
        return upgrades.getTowerUpgradePrice(towerName, upgradeLevel);
    }

    @Override
    public void upgradeClickedTower() {
        if (upgrades.upgradeTower(map.getClickedTower())) {
            player.decreaseMoney(upgrades.getTowerUpgradePrice(map.getClickedTower().getName(), map.getClickedTower().getUpgradeLevel() - 1).intValue());
        }
    }

    @Override
    public boolean getIsGameLost() {
        return map.getIsGameLost();
    }

    @Override
    public boolean showWinPanel() {
        return showWinPanel;
    }

    @Override
    public void continueToFreePlay() {
        showWinPanel = false;
    }

    @Override
    public int getMoney() {
        return player.getMoney();
    }

    @Override
    public int getLivesLeft() {
        return player.getLives();
    }

    @Override
    public int getCurrentRound() {
        return round.getCurrentRound();
    }

    @Override
    public int getWinningRound() {
        return round.getWinningRound();
    }

    @Override
    public List<IMapObject> getAllMapObjects() {
        return map.getAllMapObjects();
    }
}
