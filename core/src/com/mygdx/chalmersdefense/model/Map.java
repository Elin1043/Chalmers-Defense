package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.utilities.event.EventBus;
import com.mygdx.chalmersdefense.model.modelUtilities.events.ModelEvents;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.path.IPath;
import com.mygdx.chalmersdefense.model.path.PathFactory;
import com.mygdx.chalmersdefense.model.powerUps.*;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.modelUtilities.Calculate;
import com.mygdx.chalmersdefense.utilities.RangeCircle;
import com.mygdx.chalmersdefense.model.modelUtilities.PathRectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 * <p>
 * Class handeling all objects and methods on Map.
 *
 * 2021-10-15 Modified by Elin Forsberg and Joel Båtsman Hilmmersson: Added methods for powerUps
 * 2021-10-22 Modified by Joel Båtsman Hilmmersson: Split big methods into smaller ones
 */
final class Map {
    private ITower selectedTower;        // Helper when placing towers and the current selected tower
    private final List<ITower> towersList = new ArrayList<>();              // The main tower list
    private final List<IProjectile> projectilesList = new ArrayList<>();    // The main projectile list
    private final List<IVirus> virusesList = new ArrayList<>();             // The main virus list
    private final List<IGenericMapObject> genericObjectsList = new ArrayList<>();    // The main genericObjects list
    private final List<IPowerUp> powerUpList = PowerUpFactory.createPowerUps(virusesList); // List containing all power-ups

    private final List<ITower> towersToAddList = new ArrayList<>();             // Temporary list for object adding towers to the main list (To avoid concurrent modification issues)
    private final List<IProjectile> projectilesToAddList = new ArrayList<>();   // Temporary list for object adding projectiles to the main list (To avoid concurrent modification issues)
    private final List<IVirus> virusToAddList = new ArrayList<>();             // Temporary list for object adding virus to the main list (To avoid concurrent modification issues)

    private final EventBus eventBus;                                      // A reference to the EventBus in the game
    private final IPath path = PathFactory.createClassicPath();     // Current path

    private final RangeCircle rangeCircle = new RangeCircle(0,0,0);     // Helper class for showing gray range circle

    Map(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * Update all map components
     */
    void updateMap() {
        updateVirus();
        updateTowers();
        updateProjectiles();
        updateRangeCircle();
        updatePowerUps();
        updateGenericObjects();
        addTempListsToMainLists();
    }


    /**
     * Resets all of maps variables
     */
    void resetMap() {
        towersList.clear();
        projectilesList.clear();
        virusesList.clear();
        genericObjectsList.clear();

        resetAllPowerUps();

        selectedTower = null;

        // Removes range circle
        rangeCircle.setEnumColor(RangeCircle.Color.NONE);
    }

    //Resets all powerUps
    private void resetAllPowerUps() {
        for (IPowerUp powerUp : powerUpList) {
            powerUp.resetPowerUp();
        }
    }

    //Add all temporary list to the mainlist
    private void addTempListsToMainLists() {
        towersList.addAll(towersToAddList);
        projectilesList.addAll(projectilesToAddList);
        virusesList.addAll(virusToAddList);

        towersToAddList.clear();
        projectilesToAddList.clear();
        virusToAddList.clear();
    }

    //Update all the towers
    private void updateTowers() {
        List<ITower> removeTowers = new ArrayList<>();

        for (ITower tower : towersList) {

            List<IVirus> virusInRange = getVirusesInRange(tower.getX(), tower.getY(), getModifiedTowerRange(tower), virusesList);


            // Standard values for when virus is out of range
            float newAngle = -1;
            boolean towerHasTarget = false;

            // If there are virus in range, update the new values accordingly
            if (virusInRange.size() > 0) {
                IVirus targetVirus = tower.getCurrentTargetMode().getCorrectVirus(virusInRange, tower.getX(), tower.getY());
                newAngle = Calculate.angleDeg(targetVirus.getX(), targetVirus.getY(), tower.getX(), tower.getY());
                towerHasTarget = true;
            }

            tower.update(projectilesList, newAngle, towerHasTarget);

            if (tower.canRemove() && !tower.equals(selectedTower)) { removeTowers.add(tower); }
        }

        towersList.removeAll(removeTowers);
    }


    // If power-up modifier is active, apply the modifier
    private float getModifiedTowerRange(ITower tower){
        if (powerUpList.get(1).getIsActive()){
            return tower.getRange() * 1.5f;
        } else {
            return tower.getRange();
        }
    }

    //Update all the viruses
    private void updateVirus() {

        List<IVirus> virusToRemove = new ArrayList<>();

        for (IVirus virus : virusesList) {
            if (virus.getY() > 1130 || virus.isDead()) {
                virusToRemove.add(virus);
            }
            virus.update();
        }

        removeDeadVirusesHandler(virusToRemove);
    }

    // Removes viruses from game and decreases player life
    private void removeDeadVirusesHandler(List<IVirus> virusToRemove) {
        for (IVirus virus : virusToRemove) {
            this.eventBus.emit(new ModelEvents(ModelEvents.Type.DECREASELIFEOFPLAYER, virus.getLifeDecreaseAmount()));
            virusesList.remove(virus);
        }
    }

    //Updates all things concerning projectiles
    private void updateProjectiles() {
        List<IProjectile> removeProjectiles = new ArrayList<>();

        for (IProjectile projectile : projectilesList) {
            projectileUpdateHandler(projectile);
            if (projectile.canRemove() || Calculate.checkIfOutOfBounds(projectile, true)) {
                removeProjectiles.add(projectile);
            }
        }

        projectilesList.removeAll(removeProjectiles);
    }

    // Handels the actual projectile update
    private void projectileUpdateHandler(IProjectile projectile) {
        List<IVirus> virusThatWasHit = new ArrayList<>();

        if (checkCollisionOfProjectiles(projectile, virusThatWasHit)) {
            float angle = getAngleToVirus(projectile, virusThatWasHit);
            projectile.update(true, virusThatWasHit.get(0).hashCode(), angle);
        } else {
            projectile.update(false, -1, -1);
        }
    }

    //Checks if projectile collided with path, then virus
    private boolean checkCollisionOfProjectiles(IProjectile projectile, List<IVirus> virusThatWasHit) {
        for (PathRectangle rectangle : path.getCollisionRectangles()) {
            if (Calculate.objectsIntersects(projectile, rectangle)) {
                return checkVirusAndProjectileCollision(projectile, virusThatWasHit);
            }
        }
        return false;
    }

    //Helper method for collision between virus and projectile
    private boolean checkVirusAndProjectileCollision(IProjectile projectile, List<IVirus> virusThatWasHit) {

        for (IVirus virus : virusesList) {
            if (Calculate.objectsIntersects(projectile, virus) && !projectile.haveHitBefore(virus.hashCode())) {
                virusAndProjectileHitHandler(projectile, virusThatWasHit, virus);
                return true;
            }
        }

        return false;
    }

    // What actually happens when projectile and virus hit
    private void virusAndProjectileHitHandler(IProjectile projectile, List<IVirus> virusThatWasHit, IVirus virus) {
        int virusHealthBefore = virus.getLifeDecreaseAmount();

        virus.decreaseHealth(projectile.getDamageAmount());
        this.eventBus.emit(new ModelEvents(ModelEvents.Type.ADDMONEYTOPLAYER, virusHealthBefore - virus.getLifeDecreaseAmount())); // This will add the correct amount of money to the player relative to the amount of damage done
        virusThatWasHit.add(virus);
    }

    //Get angle to virus in range of projectile
    private float getAngleToVirus(IProjectile projectile, List<IVirus> removeList) {
        List<IVirus> virusInRange = getTargetableViruses(projectile,removeList);

        if (virusInRange.size() > 0) {
            IVirus virus = virusInRange.get(0);
            return Calculate.angleDeg(virus.getX(), virus.getY(), projectile.getX(), projectile.getY());
        }
        return -1;
    }

    //Get the targetable viruses in range on projectile
    private List<IVirus> getTargetableViruses(IProjectile projectile, List<IVirus> removeList){
        List<IVirus> virusInRange = getVirusesInRange(projectile.getX(), projectile.getY(), 250, virusesList);

        for (IVirus virus : virusInRange) {
            if (projectile.haveHitBefore(virus.hashCode())) {
                removeList.add(virus);
            }
        }

        virusInRange.removeAll(removeList);
        return virusInRange;
    }

    // Gets all viruses in a given range from coordinates
    private List<IVirus> getVirusesInRange(float towerX, float towerY, float towerRange, List<IVirus> allViruses) {
        List<IVirus> virusList = new ArrayList<>();

        for (IVirus virus : allViruses) {
            if (Calculate.disBetweenPoints(towerX, towerY, virus.getX(), virus.getY()) < towerRange) {
                virusList.add(virus);
            }
        }
        return virusList;
    }

    //Updates the rangeCircle
    private void updateRangeCircle() {
        if(selectedTower != null){
            rangeCircle.updatePos(selectedTower.getX() + selectedTower.getWidth()/2, selectedTower.getY() + selectedTower.getHeight()/2, getModifiedTowerRange(selectedTower));
        }
    }

    //Update all the genericObjects
    private void updateGenericObjects(){
        List<IGenericMapObject> removeList = new ArrayList<>();

        for (IGenericMapObject object : genericObjectsList) {
            object.update();
            if(object.canRemove()){ removeList.add(object); }
        }

        genericObjectsList.removeAll(removeList);
    }

    //Updates the powerUps
    private void updatePowerUps() {
        for (IPowerUp powerUp : powerUpList){
            powerUp.decreaseTimer();
        }

        if (powerUpList.get(0).getIsActive()){
            for (int i = 0; i < 3; i++) { updateTowers(); }
        }
    }


    /**
     * Returns the timers for all power-ups
     * @return Array with all timers in it
     */
    int[] getPowerUpTimers(){
        int[] timers = new int[powerUpList.size()];

        for (int i = 0; i < powerUpList.size(); i++){
            timers[i] = powerUpList.get(i).getTimer();
        }

        return timers;
    }

    /**
     * Returns a list with the active status of all power-ups
     * @return Array with current active status of power-ups
     */
    boolean[] getPowerUpActiveStatus(){
        boolean[] powerUpsActive = new boolean[powerUpList.size()];

        for (int i = 0; i < powerUpList.size(); i++){
            powerUpsActive[i] = powerUpList.get(i).getIsActive();
        }

        return powerUpsActive;
    }

    //Checks if a tower collides with path
    private boolean checkMapAndTowerCollision(ITower tower) {
        for (PathRectangle rect : path.getCollisionRectangles()) {
            if (Calculate.objectsIntersects(tower, rect)) {
                return true;
            }
        }
        return false;
    }

    //Checks if towers collide with anything
    private boolean checkCollisionOfTower(ITower tower) {
        for (ITower checkTower : towersList) {
            //Check if tower collides with a placed tower
            if (Calculate.objectsIntersects(tower, checkTower) && !(checkTower.hashCode() == tower.hashCode())) {
                return true;
            }
            else if (Calculate.checkIfOutOfBounds(tower, false)) {
                return true;
            }
            //check if tower collide with path
            else if (checkMapAndTowerCollision(tower)) {
                return true;
            }

        }
        return false;

    }

    /**
     * Creates a new tower when user starts dragging from a tower button.
     *
     * @param towerName the name of the tower
     * @param x         the X-position of the button
     * @param y         the Y-position of the button
     */
    void dragStart(String towerName, float x, float y) {
        switch (towerName) {
            case "smurf" -> selectedTower = TowerFactory.createSmurf(x, y);
            case "chemist" -> selectedTower = TowerFactory.createChemist(x, y, projectilesToAddList);
            case "electro" -> selectedTower = TowerFactory.createElectro(x, y);
            case "hacker" -> selectedTower = TowerFactory.createHacker(x, y, projectilesToAddList);
            case "mech" -> selectedTower = TowerFactory.createMech(x, y, towersToAddList, Collections.unmodifiableList(towersList), path.getCollisionRectangles());
            case "eco" -> selectedTower = TowerFactory.createEco(x, y, eventBus);
            default -> throw new IllegalArgumentException("The argument: '" + towerName + "' is not a valid tower");
        }

        towersList.add(selectedTower);
    }


    /**
     * Handles a tower being dragged.
     * Updates the towers position after mouse and check for collision
     *  @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     */
    void onDrag(float x, float y, int money) {

        selectedTower.setPos(x - selectedTower.getWidth() / 2f, y - selectedTower.getHeight() / 2f);

        if (!checkCollisionOfTower(selectedTower) && (money >= selectedTower.getCost())) {
            selectedTower.setIfCanRemove(false);
            rangeCircle.updatePos(selectedTower.getX() + selectedTower.getWidth() / 2, selectedTower.getY() + selectedTower.getHeight() / 2, selectedTower.getRange());
            rangeCircle.setEnumColor(RangeCircle.Color.GRAY);

        } else {
            selectedTower.setIfCanRemove(true);
            rangeCircle.updatePos(selectedTower.getX() + selectedTower.getWidth() / 2, selectedTower.getY() + selectedTower.getHeight() / 2, selectedTower.getRange());
            rangeCircle.setEnumColor(RangeCircle.Color.RED);
        }
    }


    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     *  @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     */
    void dragEnd(float x, float y) {
        if (!selectedTower.canRemove()) {
            selectedTower.placeTower();
            selectedTower.setPos(x - selectedTower.getWidth() / 2f, y - selectedTower.getHeight() / 2f);
            this.eventBus.emit(new ModelEvents(ModelEvents.Type.REMOVEMONEYFROMPLAYER,selectedTower.getCost()));
        } else {
            towersList.remove(selectedTower);
            rangeCircle.setEnumColor(RangeCircle.Color.NONE);
            selectedTower = null;
        }
    }


    /**
     * Handles when a placed tower is clicked
     */
    void checkIfTowerClicked(float x, float y) {
        // Algorithm for finding which tower is clicked
        ITower towerWasClicked = null;
        for (ITower tower : towersList) {
            float towerCenterX = tower.getX() + tower.getWidth() / 2;
            float towerCenterY = tower.getY() + tower.getHeight() / 2;


            if (Math.sqrt(Math.pow(towerCenterX - x, 2) + Math.pow(towerCenterY - y, 2)) <= tower.getWidth()) {
                towerWasClicked = tower;
                rangeCircle.updatePos(towerCenterX, towerCenterY, tower.getRange());
                rangeCircle.setEnumColor(RangeCircle.Color.GRAY);
            }
        }

        if (towerWasClicked == null) {
            rangeCircle.setEnumColor(RangeCircle.Color.NONE);
        }
        selectedTower = towerWasClicked;

    }

    /**
     * Upgrades clicked tower if player has enough money
     */
    void upgradeClickedTower() {
        // If upgrade is applied decrease player money
        if (Upgrades.upgradeTower(selectedTower)) {
            this.eventBus.emit(new ModelEvents(ModelEvents.Type.REMOVEMONEYFROMPLAYER,Upgrades.getTowerUpgradePrice(selectedTower.getName(), selectedTower.getUpgradeLevel() - 1)));

            rangeCircle.updatePos(selectedTower.getX() + getSelectedTower().getWidth()/2, selectedTower.getY() + getSelectedTower().getHeight()/2, selectedTower.getRange());
        }
    }

    /**
     * Sell the clicked tower
     * Also gives the player some money back
     *
     * @param cost cost of tower sold
     */
    void sellClickedTower(int cost) {
        towersList.remove(selectedTower);
        this.eventBus.emit(new ModelEvents(ModelEvents.Type.ADDMONEYTOPLAYER, cost));
        selectedTower = null;
        rangeCircle.setEnumColor(RangeCircle.Color.NONE);
    }

    /**
     * Returns sell price for an upgraded tower
     * @return price
     */
    private float upgradedTowerSellPrice() {
        float cost = selectedTower.getCost();

        for (int i = 2; i < selectedTower.getUpgradeLevel() + 1; i++) {
            cost += Upgrades.getTowerUpgradePrice(selectedTower.getName(), i - 1);
        }

        cost *= 0.6;
        return cost;
    }

    /**
     * Change the targetMode of the clicked tower
     */
    void changeTargetMode(boolean goRight){
        selectedTower.changeTargetMode(goRight);
    }


    /**
     * Return the circle used for rendering range
     * @return the circle
     */
    RangeCircle getRangeCircle() {
        return rangeCircle;
    }

    /**
     * Returns currently clicked tower
     *
     * @return tower object of clicked tower
     */
    ITower getSelectedTower() {
        return selectedTower;
    }

    /**
     * Get the sell price of the clicked tower
     * @return price
     */
    int getSelectedTowerSellPrice() {
        float cost;
        if(selectedTower.getUpgradeLevel() == 1){
            cost = (selectedTower.getCost() * 0.6F);
        }
        else{
            cost = upgradedTowerSellPrice();
        }
        return Math.round(cost);
    }

    /**
     * Returns currently clicked towers target mode
     *
     * @return target mode of clicked tower
     */
    String getSelectedTowerTargetMode() {
        String[] targetModeNameSplit = selectedTower.getCurrentTargetMode().getClass().getName().split("[.]");
        return targetModeNameSplit[targetModeNameSplit.length - 1];
    }

    /**
     * Returns the list to add viruses to
     * @return list of viruses
     */
    List<IVirus> getVirusesToAddList() {
        return virusToAddList;
    }

    //TODO Remove when not needed
    List<IVirus> getViruses() {
        return virusesList;
    }

    /**
     * Return the list of objects on map
     *
     * @return the list of objects
     */
    List<IMapObject> getAllMapObjects() {
        List<IMapObject> allMapObjects = new ArrayList<>();
        allMapObjects.addAll(towersList);
        allMapObjects.addAll(virusesList);
        allMapObjects.addAll(projectilesList);
        allMapObjects.addAll(genericObjectsList);
        return allMapObjects;
    }

    /**
     * Returns if virus list is empty
     *
     * @return true - if all viruses are cleared, false - if there are viruses left
     */
    boolean isVirusCleared() {
        return virusesList.isEmpty();
    }

    /**
     * Returns the background image path to the image
     */
    String getMapImagePath(){ return path.getImagePath(); }

    /**
     * Method to call when round is cleared, makes map ready for next round
     */
    void roundClear() {
        projectilesList.clear();
        genericObjectsList.clear();
        resetAllPowerUps();
        updateRangeCircle();
    }


    /**
     * Method to handle a powerUp button being clicked. Also checks if player have enough cost to buy powerup-
     * @param powerUpName name of the button that was clicked
     */
    void powerUpClicked(String powerUpName, int money) {
        IPowerUp powerUp = switch (powerUpName) {
            case "cleanHands" -> powerUpList.get(0);
            case "maskedUp"   -> powerUpList.get(1);
            case "vaccinated" -> powerUpList.get(2);
            default -> throw new IllegalArgumentException("The argument: '" + powerUpName + "' is not a valid power-up"); 
        };

        handlePowerUpClicked(powerUp,money);
    }

    private void handlePowerUpClicked(IPowerUp powerUp, int money) {
        if ((money >= powerUp.getCost()) && !powerUp.getIsActive() && powerUp.getTimer() == -1) {
            powerUp.powerUpClicked(genericObjectsList);
            this.eventBus.emit(new ModelEvents(ModelEvents.Type.REMOVEMONEYFROMPLAYER,powerUp.getCost()));
        }
    }
}
