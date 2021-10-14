package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.views.overlays.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
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
 * <p>
 * Class handeling all the models in the game.
 * <p>
 * 2021-09-20 Modified by Elin Forsberg: Added methods to handle towers + collisions <br>
 * 2021-09-20 Modified by Joel Båtsman Hilmersson: Made updateVirus loop syncronized <br>
 * 2021-09-22 Modified by Daniel Persson: Added support for storing a clicked tower and added algorithm for finding what tower is being clicked. <br>
 * 2021-09-24 Modified by Elin Forsberg: Added methods to handle projectiles <br>
 * 2021-09-25 Modified by Joel Båtsman Hilmersson: Added support for round system <br>
 * 2021-09-27 Modified by Elin Forsberg: Added methods to handle different attacks from towers <br>
 * 2021-09-27 Modified by Daniel Persson: Added delegation getters for upgrade title, description and price. <br>
 * 2021-09-28 Modified by Everyone: Moved methods to Map class <br>
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Added a specifc timer object <br>
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

    private ScreenOverlayEnum showOverlay = ScreenOverlayEnum.NONE;       // Boolean for views of they should show win panel

    @Override
    public synchronized void updateModel() {
        map.updateMap();
        checkRoundCompleted();
        virusSpawner.decrementSpawnTimer();
        if (map.getIsGameLost()) {
            showOverlay = ScreenOverlayEnum.LOSEPANEL;
        }
    }

    @Override
    public void resetModel() {
        round = new Rounds(WINNING_ROUND);
        player.resetPlayer(LIVES, START_CAPITAL);
        map.resetMap();
        virusSpawner.resetSpawnViruses();
        showOverlay = ScreenOverlayEnum.NONE;
    }

    private void checkRoundCompleted() {
        if (map.isVirusCleared() && !virusSpawner.isSpawning()) {

            player.increaseMoney((100 * (round.getCurrentRound() / 2)));

            stopGameUpdate();
            map.roundClear();

            if (round.gameWon()) {
                showOverlay = ScreenOverlayEnum.WINPANEL;
            }
        }
    }

    @Override
    public ITargetMode getClickedTowerTargetMode() {
        return map.getClickedTowerTargetMode();
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
    public void changeTargetMode(boolean goRight){
        map.changeTargetMode(goRight);
    }

    @Override
    public int getClickedTowerSellPrice() {
        double cost = 0;
        if(map.getClickedTower().getUpgradeLevel() == 1){
            cost = (map.getClickedTower().getCost() * 0.6);
        }
        else{
            cost += map.getClickedTower().getCost();
            for (int i = 2; i < map.getClickedTower().getUpgradeLevel() + 1; i++) {
                cost += upgrades.getTowerUpgradePrice(map.getClickedTower().getName(), i-1).intValue();
            }
            cost *= 0.6;
        }
        return (int)cost;
    }



    @Override
    public void upgradeClickedTower() {
        if (upgrades.upgradeTower(map.getClickedTower())) {
            player.decreaseMoney(upgrades.getTowerUpgradePrice(map.getClickedTower().getName(), map.getClickedTower().getUpgradeLevel() - 1).intValue());

            GetRangeCircle circle = map.getRangeCircle();
            circle.updatePos(map.getClickedTower().getX() + getClickedTower().getWidth()/2, map.getClickedTower().getY() + getClickedTower().getHeight()/2, map.getClickedTower().getRange());
        }
    }

    @Override
    public ScreenOverlayEnum showOverlay() {
        return showOverlay;
    }

    @Override
    public void setShowOverlay(ScreenOverlayEnum overlay) {
        showOverlay = overlay;
    }

    @Override
    public void sellClickedTower() {
        map.sellClickedTower(getClickedTowerSellPrice());
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

    //TODO Remove THIS when not needed
    @Override
    public List<IVirus> getViruses() {
        return map.getViruses();
    }

    @Override
    public List<IMapObject> getAllMapObjects() {
        return map.getAllMapObjects();
    }
}
