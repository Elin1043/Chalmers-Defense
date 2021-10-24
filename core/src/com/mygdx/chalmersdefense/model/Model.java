package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.model.event.IEventListener;
import com.mygdx.chalmersdefense.model.event.ModelEvents;
import com.mygdx.chalmersdefense.utilities.*;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.SpawnViruses;
import com.mygdx.chalmersdefense.model.modelUtilities.GameTimer;
import com.mygdx.chalmersdefense.model.modelUtilities.IGameTimer;

import java.util.Collections;
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
 * 2021-10-15 Modified by Elin Forsberg and Joel Båtsman Hilmmersson: Added methods for powerUps
 * 2021-10-22 Modified by Joel Båtsman Hilmmersson: Split big methods into smaller ones
 * 2021-10-22 Modified by Daniel Persson: Changed Upgrade object to use updated upgrades class. Also moved upgrade logic to map
 */

public class Model implements IUpdateModel, IControllModel, IViewModel, IEventListener<ModelEvents> {
    private final int WINNING_ROUND = 30;       // Current winning round
    private final int LIVES = 100;              // Current amount of starting lives
    private final int START_CAPITAL = 40000;    // Current amount of start capital

    private final IGameTimer timer = new GameTimer(this);    // Timer object
    private Rounds round = new Rounds(WINNING_ROUND);              // Round helper

    private final Player player = new Player(LIVES, START_CAPITAL); // Player object

    private ScreenOverlayEnum showOverlay = ScreenOverlayEnum.NONE;       // Boolean for views of they should show win panel

    private final Preferences preferences;
    private final EventBus eventBus = new EventBus();  // A reference to the EventBus in the game
    private final Map map = new Map(eventBus);        // Current map object
    private final SpawnViruses virusSpawner = new SpawnViruses(map.getVirusesToAddList());   // The class for spawning viruses




    public Model(Preferences preferences) {
        this.preferences = preferences;
        eventBus.listenFor(ModelEvents.class, this);

    }


    @Override
    public void handle(ModelEvents event) {
        switch(event.getEventType()){
            case ADDMONEYTOPLAYER -> player.increaseMoney(event.getAmount());
            case REMOVEMONEYFROMPLAYER -> player.decreaseMoney(event.getAmount());
            case DECREASELIFEOFPLAYER -> {
                try {
                    player.decreaseLivesBy(event.getAmount());
                } catch (PlayerLostAllLifeException e) {
                    showOverlay = ScreenOverlayEnum.LOSEPANEL;
                }
            }
        }
    }


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
        showOverlay = ScreenOverlayEnum.NONE;
    }

    //Checks if round is completed
    private void checkRoundCompleted() {
        if (isGameStopped()) {
            player.increaseMoney((int) (100 * (round.getCurrentRound() / 2f)));

            timer.stopUpdateTimer();
            map.roundClear();

            if (round.gameWon()) {
                showOverlay = ScreenOverlayEnum.WINPANEL;
            }
            if (preferences.getBoolean("autoplay") && getCurrentRound() != 1 && !round.gameWon()) startRoundPressed();
        }


    }

    @Override
    public String getClickedTowerTargetMode() {
        return map.getSelectedTowerTargetMode();
    }


    @Override
    public void startGameUpdate() {
        if (!isGameStopped()) { timer.startUpdateTimer(); }
    }


    @Override
    public void stopGameUpdate() {
        timer.stopUpdateTimer();
    }


    @Override
    public void startRoundPressed() {
        if (isGameStopped()) {
            timer.startUpdateTimer();
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
    public void onDrag(float x, float y) {
        map.onDrag(x, y, player.getMoney());
    }

    @Override
    public void dragEnd(float x, float y) {
        map.dragEnd(x, y);
    }


    @Override
    public void checkIfTowerClicked(float x, float y) {
        map.checkIfTowerClicked(x, y);
    }

    @Override
    public void powerUpClicked(String powerUpName){
        if (!isGameStopped()) {
            map.powerUpClicked(powerUpName, player.getMoney());
        }
    }

    @Override
    public RangeCircle getRangeCircle() {
        return new RangeCircle(map.getRangeCircle());
    }

    @Override
    public IMapObject getClickedTower() {
        return map.getSelectedTower();
    }

    @Override
    public String getTowerUpgradeTitle(String towerName, int upgradeLevel) {
        return Upgrades.getTowerUpgradeTitle(towerName, upgradeLevel);
    }

    @Override
    public String getTowerUpgradeDesc(String towerName, int upgradeLevel) {
        return Upgrades.getTowerUpgradeDesc(towerName, upgradeLevel);
    }

    @Override
    public Integer getTowerUpgradePrice(String towerName, int upgradeLevel) {
        return Upgrades.getTowerUpgradePrice(towerName, upgradeLevel);
    }

    @Override
    public void changeTargetMode(boolean goRight){
        map.changeTargetMode(goRight);
    }

    @Override
    public int getClickedTowerSellPrice() {
        return map.getSelectedTowerSellPrice();
    }

    @Override
    public int[] getPowerUpTimer(){
        return map.getPowerUpTimers();
    }

    @Override
    public boolean[] getPowerUpActive(){
        return map.getPowerUpActiveStatus();
    }

    @Override
    public void upgradeClickedTower() {
        map.upgradeClickedTower();
    }

    @Override
    public ScreenOverlayEnum getCurrentOverlay() {
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
    public List<IVirus> getVirusesToAddList() {
        return map.getVirusesToAddList();
    }

    //TODO Remove when not needed
    @Override
    public List<IVirus> getViruses() {
        return map.getViruses();
    }

    @Override
    public List<IMapObject> getAllMapObjects() {
        return Collections.unmodifiableList(map.getAllMapObjects());
    }

    @Override
    public boolean isGameStopped() {
        return !virusSpawner.isSpawning() && map.isVirusCleared();
    }

    @Override
    public String getMapImagePath() { return map.getMapImagePath(); }

    @Override
    public boolean isGameSpedUp() { return timer.isGameSpedUp(); }
}
