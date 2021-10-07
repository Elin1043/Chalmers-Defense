package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.path.PathFactory;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.utilities.PathRectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 * <p>
 * Class handeling all objects and methods on Map.
 */
class Map {
    private ITower newTower;            // Temp helper for when new tower is added
    private ITower clickedTower;        // The current clicked tower
    private final List<ITower> towersList = new ArrayList<>();              // The main tower list
    private final List<IProjectile> projectilesList = new ArrayList<>();    // The main projectile list
    private final List<IVirus> virusesList = new ArrayList<>();             // The main virus list

    private final List<ITower> towersToAddList = new ArrayList<>();             // Temporary list for object adding towers to the main list (To avoid concurrent modification issues)
    private final List<IProjectile> projectilesToAddList = new ArrayList<>();   // Temporary list for object adding projectiles to the main list (To avoid concurrent modification issues)

    private final Player player;                                   // A reference to the Player object in the game
    private final Path path = PathFactory.createClassicPath();     // Current path

    private boolean isGameLost;     // Boolean if game is lost

    private final GetRangeCircle rangeCircle = new GetRangeCircle();     // Helper class for showing gray range circle


    Map(Player player) {
        this.player = player;
        isGameLost = false;
    }

    /**
     * Update all map components
     */
    void updateMap() {
        updateVirus();
        updateTowers();
        updateProjectiles();
        addTempListsToMainLists();
    }

    /**
     * Resets all of maps variables
     */
    void resetMap() {
        towersList.clear();
        projectilesList.clear();
        virusesList.clear();
        clickedTower = null;
        isGameLost = false;
        // Removes range circle
        rangeCircle.setEnumColor(GetRangeCircle.Color.NONE);
    }

    //Add all temporary list to the mainlist
    private void addTempListsToMainLists() {
        towersList.addAll(towersToAddList);
        projectilesList.addAll(projectilesToAddList);
        towersToAddList.clear();
        projectilesToAddList.clear();
    }

    //Update the projectiles
    private void updateProjectiles() {
        List<IProjectile> removeProjectiles = new ArrayList<>();

        for (IProjectile projectile : projectilesList) {
            List<IVirus> virusThatWasHit = new ArrayList<>();

            if (checkCollisionOfProjectiles(projectile, virusThatWasHit)) {
                float angle = getAngle(projectile, virusThatWasHit);
                projectile.update(true, virusThatWasHit.get(0).hashCode(), angle);
            } else {
                projectile.update(false, -1, -1);
            }

            if (projectile.canRemove() || checkIfOutOfBounds(projectile.getY(), projectile.getX())) {
                removeProjectiles.add(projectile);
            }
        }

        projectilesList.removeAll(removeProjectiles);
    }


    //Checks if projectile collided with path, then virus
    private boolean checkCollisionOfProjectiles(IProjectile projectile, List<IVirus> removeList) {
        for (PathRectangle rectangle : path.getCollisionRectangles()) {
            if (Calculate.objectsIntersects(projectile, rectangle)) {
                return checkVirusAndProjectileCollision(projectile, removeList);
            }
        }
        return false;
    }

    //Helper method for collision between virus and projectile
    private boolean checkVirusAndProjectileCollision(IProjectile projectile, List<IVirus> removeList) {

        for (IVirus virus : virusesList) {
            if (Calculate.objectsIntersects(projectile, virus) && !projectile.haveHitBefore(virus.hashCode())) {
                virus.decreaseHealth();
                removeList.add(virus);
                return true;
            }
        }
        return false;
    }

    private float getAngle(IProjectile projectile, List<IVirus> removeList) {
        List<IVirus> virusInRange = Calculate.getVirusesInRange(projectile.getX(), projectile.getY(), 150, virusesList);

        for (IVirus virus : virusInRange) {
            if (projectile.haveHitBefore(virus.hashCode())) {
                removeList.add(virus);
            }
        }

        virusInRange.removeAll(removeList);

        if (virusInRange.size() > 0) {
            IVirus v = virusInRange.get(0);
            return Calculate.angleDeg(v.getX(), v.getY(), projectile.getX(), projectile.getY());
        }
        return -1;
    }

    //Update all the towers
    private void updateTowers() {
        for (ITower tower : towersList) {

            List<IVirus> virusInRange = Calculate.getVirusesInRange(tower.getX(), tower.getY(), tower.getRange(), virusesList);

            // Standard values for when virus is out of range
            float newAngle = -1;
            boolean towerHasTarget = false;

            // If there are virus in range, update the new values accordingly
            if (virusInRange.size() > 0) {
                IVirus targetVirus = tower.getCurrentTargetMode().getRightVirus(virusInRange, tower.getX(), tower.getY());
                newAngle = Calculate.angleDeg(targetVirus.getX(), targetVirus.getY(), tower.getX(), tower.getY());
                towerHasTarget = true;
            }

            tower.update(projectilesList, newAngle, towerHasTarget);
        }
    }


    //Update all the viruses
    private void updateVirus() {

        List<IVirus> virusToRemove = new ArrayList<>();

        for (IVirus virus : virusesList) {
            if (virus.getY() > 1130 || virus.isDead()) {
                virusToRemove.add(virus);
                if (virus.isDead()) {
                    player.increaseMoney(1); //Change amount later
                }
            }
            virus.update();
        }
        for (IVirus virus : virusToRemove) {
            try {
                player.decreaseLivesBy(virus.getLifeDecreaseAmount());
            } catch (PlayerLostAllLifeException ignore) {
                isGameLost = true;
            }
            virusesList.remove(virus);
        }

    }


    //Check if coordinates are outside the screen
    private boolean checkIfOutOfBounds(float y, float x) {
        if (y > 1130 || -50 > y) {
            return true;
        }
        return x > 1970 || -50 > x;
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
    private boolean checkCollisionOfTower(ITower tower, int windowHeight, int windowWidth) {
        for (ITower checkTower : towersList) {
            //Check if tower collides with a placed tower
            if (Calculate.objectsIntersects(tower, checkTower) && !(checkTower.hashCode() == tower.hashCode())) {
                return true;
            }
            //Check if tower out of bound on X
            else if (!(0 <= (tower.getX())) || (windowWidth - 340 < (tower.getX() + tower.getWidth() / 2))) {
                return true;
            }
            //Check if tower out of bound on Y
            else if (!(windowHeight - 950 < (tower.getY() - tower.getHeight() / 2)) || (windowHeight < (tower.getY()) + tower.getHeight())) {
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
            case "smurf" -> newTower = TowerFactory.CreateSmurf(x, y);
            case "chemist" -> newTower = TowerFactory.CreateChemist(x, y, projectilesToAddList);
            case "electro" -> newTower = TowerFactory.CreateElectro(x, y);
            case "hacker" -> newTower = TowerFactory.CreateHacker(x, y);
            case "meck" -> newTower = TowerFactory.CreateMeck(x, y, towersToAddList);
            case "eco" -> newTower = TowerFactory.CreateEco(x, y, player);
            default -> {
                return;
            }
        }

        towersList.add(newTower);
        clickedTower = newTower;

    }


    /**
     * Handles a tower being dragged.
     * Updates the towers position after mouse and check for collision
     *
     * @param buttonWidth  The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     * @param windowHeight The height of the window
     * @param windowWidth  The width of the window
     */
    void onDrag(float buttonWidth, float buttonHeight, float x, float y, int windowHeight, int windowWidth) {

        newTower.setPos(x - buttonWidth / 2f, y - buttonHeight / 2f);

        for (ITower tower : towersList) {

            if (!tower.isPlaced() && !checkCollisionOfTower(tower, windowHeight, windowWidth)) {
                tower.setCollision(false);
                rangeCircle.updatePos(tower.getX() + tower.getWidth() / 2, tower.getY() + tower.getHeight() / 2, tower.getRange());
                rangeCircle.setEnumColor(GetRangeCircle.Color.GRAY);


            } else if (!tower.isPlaced() && checkCollisionOfTower(tower, windowHeight, windowWidth)) {
                tower.setCollision(true);
                rangeCircle.updatePos(tower.getX() + tower.getWidth() / 2, tower.getY() + tower.getHeight() / 2, tower.getRange());
                rangeCircle.setEnumColor(GetRangeCircle.Color.RED);
            } else {
                rangeCircle.setEnumColor(GetRangeCircle.Color.NONE);
            }

        }
    }

    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     *
     * @param buttonWidth  The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x            The X-position of the mouse
     * @param y            The Y-position of the mouse
     */
    void dragEnd(float buttonWidth, float buttonHeight, float x, float y) {
        if (!newTower.getCollision()) {
            newTower.placeTower();
            newTower.setPos(x - buttonWidth / 2f, y - buttonHeight / 2f);
            player.decreaseMoney(newTower.getCost());
        } else {
            towersList.remove(newTower);
            rangeCircle.setEnumColor(GetRangeCircle.Color.NONE);
            clickedTower = null;
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
                rangeCircle.setEnumColor(GetRangeCircle.Color.GRAY);

            }
        }
        if (towerWasClicked == null) {
            rangeCircle.setEnumColor(GetRangeCircle.Color.NONE);
        }
        clickedTower = towerWasClicked;

    }


    /**
     * Return the circle used for rendering range
     * @return the circle
     */
    GetRangeCircle getRangeCircle() {
        return rangeCircle;
    }

    /**
     * Returns currently clicked tower
     *
     * @return tower object of clicked tower
     */
    ITower getClickedTower() {
        return clickedTower;
    }

    /**
     * Returns if game has been lost
     *
     * @return a boolean for game lost status
     */
    boolean getIsGameLost() {
        return isGameLost;
    }


    /**
     * Returns the list of viruses
     * @return list of viruses
     */
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
     * Method to call when round is cleared, makes map ready for next round
     */
    void roundClear() {
        projectilesList.clear();
    }


    /**
     * Remove the clicked tower
     * @param
     */
    void sellClickedTower(int cost) {
        towersList.remove(clickedTower);
        player.increaseMoney((int) (cost * 0.6));
        clickedTower = null;
        rangeCircle.setEnumColor(GetRangeCircle.Color.NONE);

    }
}
