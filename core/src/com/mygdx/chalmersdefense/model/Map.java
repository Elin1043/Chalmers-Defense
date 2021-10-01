package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.customExceptions.PlayerLostAllLifeException;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.projectiles.AcidProjectile;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.LightningProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.towers.*;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.Virus;
import com.mygdx.chalmersdefense.utilities.Calculate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 * Class handeling all objects and methods on Map.
 */
public class Map {
    private ITower newTower;
    private ITower clickedTower;
    private final List<ITower> towersList = new ArrayList<>();
    private List<IProjectile> projectilesList = new ArrayList<>();
    private final List<IVirus> allViruses = new ArrayList<>();

    //Should not have player here
    private final Player player;
    private final Path path = new ClassicPath();           // Make a path factory instead?;


    public Map(Player player){
        this.player = player;
    }

    public void updateMap() {
        updateVirus();
        updateTowers();
        updateProjectiles();
    }


    //Update the projectiles
    private void updateProjectiles() {
        List<IProjectile> removeProjectiles = new ArrayList<>();
        List<IProjectile> addProjectiles = new ArrayList<>();

        for (IProjectile projectile : projectilesList) {
            projectile.move();
            if (checkCollisonOfProjectiles(projectile, removeProjectiles) || checkIfOutOfBounds(projectile.getY(), projectile.getX())) {
                if(projectile.remove() == true){
                    removeProjectiles.add(projectile);
                }

            }
        }
        for (IProjectile projectile : removeProjectiles) {
            projectilesList.remove(projectile);
        }
    }


    //Update all the towers
    private void updateTowers() {
        List<ITower> towersToAdd = new ArrayList<>();
        for (ITower tower : towersList) {
            List<IVirus> virusInRange = Calculate.getVirusesInRange(tower.getX(), tower.getY(), tower.getRange(), allViruses);

            // Standard values for when virus is out of range
            float newAngle = -1;
            boolean towerHasTarget = false;

            // If there are virus in range, update the new values accordingly
            if (virusInRange.size() > 0) {
                IVirus targetVirus = tower.getCurrentTargetMode().getRightVirus(virusInRange, tower.getX(), tower.getY());
                newAngle = Calculate.angleDeg(targetVirus.getX(), targetVirus.getY(), tower.getX(), tower.getY());
                towerHasTarget = true;
            }

            tower.update(projectilesList, towersToAdd, newAngle, towerHasTarget, allViruses);
            //for (IVirus virus : allViruses) {virus.setGotHit(false);}


        }
        towersList.addAll(towersToAdd);

    }


    //Update all the viruses
    private void updateVirus(){

        List<IVirus> virusToRemove = new ArrayList<>();

        for (IVirus virus : allViruses) {
            if (virus.getY() > 1130 || virus.isDead()) {
                virusToRemove.add(virus);
                if(virus.isDead()){
                    player.increaseMoney(1); //Change amount later
                }
            }
            virus.update();
        }
        for (IVirus virus : virusToRemove){
            try {
                player.decreaseLivesBy(virus.getLifeDecreaseAmount());
            } catch (PlayerLostAllLifeException ignore){

                // Här ska man hantera ifall man förlorar spelet

            }
            allViruses.remove(virus);
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
        for (java.awt.Rectangle rect : path.getCollisionRectangles()) {
            if (tower.getRectangle().intersects(rect)) {
                return true;
            }

        }
        return false;
    }


    //Checks if projectile collided with path, then virus
    private boolean checkCollisonOfProjectiles(IProjectile projectile, List<IProjectile> list){
        for (Rectangle rectangle: path.getCollisionRectangles()) {
            if(Calculate.objectsIntersects(projectile,rectangle)){
                return checkVirusAndProjectileCollision(projectile, list);
            }
        }
        return false;
    }

    //Helper method for collision between virus and projectile
    private boolean checkVirusAndProjectileCollision(IProjectile projectile, List<IProjectile> list){
        boolean collided = false;


        for (IVirus virus : allViruses) {
            if (Calculate.objectsIntersects(projectile, virus)) {
                    if (!projectile.getIfDealtDamage()) {
                        projectile.virusIsHit(virus, list, allViruses);
                    }

                collided = true;
            }
        }


    return collided;
    }



    //Collision with acid projectile
    private void collidedWithAcid(IProjectile projectile){
        // projectile.virusIsHit(); // Change position of this later

        if(!projectile.getIfDealtDamage()){
            for (IVirus virus:getViruses()) {
                if (Calculate.disBetweenPoints(projectile.getX() + projectile.getWidth()/2F, projectile.getY() + projectile.getHeight()/2F, virus.getX() + virus.getWidth()/2F ,virus.getY() + virus.getHeight()/2F ) < ((AcidProjectile) projectile).getRange() * ((AcidProjectile) projectile).getRange()){
                    virus.decreaseHealth();
                }
            }
            projectile.setDealtDamage(true);
        }
    }


    //Checks if towers collide with anything
    private boolean checkCollisionOfTower(ITower tower, int windowHeight, int windowWidth) {
        for(ITower checkTower: towersList){
            //Check if tower collides with a placed tower
            if(tower.getRectangle().intersects(checkTower.getRectangle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            //Check if tower out of bound on X
            else if(!(0 <= (tower.getX())) || (windowWidth - 340 < (tower.getX() + tower.getWidth()/2))){
                return true;
            }
            //Check if tower out of bound on Y
            else if(!(windowHeight - 950 < (tower.getY() - tower.getHeight()/2)) || (windowHeight < (tower.getY()) + tower.getHeight())){
                return true;
            }
            //check if tower collide with path
            else if(checkMapAndTowerCollision(tower)){
                return true;
            }

        }
        return false;

    }

    /**
     * Creates a new tower when user starts dragging from a tower button.
     * @param towerName the name of the tower
     * @param x the X-position of the button
     * @param y the Y-position of the button
     */
    public void dragStart(String towerName, int x, int y) {
        switch(towerName){
            case "smurf"   -> newTower = TowerFactory.CreateSmurf(x, y);
            case "chemist" -> newTower = TowerFactory.CreateChemist(x, y);
            case "electro" -> newTower = TowerFactory.CreateElectro(x, y);
            case "hacker"  -> newTower = TowerFactory.CreateHacker(x, y);
            case "meck"    -> newTower = TowerFactory.CreateMeck(x, y);
            case "eco"     -> newTower = TowerFactory.CreateEco(x, y, player);
            default        -> { return; }
        }

        towersList.add(newTower);

    }


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
    public void onDrag(int buttonWidth, int buttonHeight, int x, int y, int windowHeight, int windowWidth) {

        newTower.setPos( x - buttonWidth,(windowHeight - y - buttonHeight ));
        newTower.setRectangle();



        for (ITower tower: towersList) {

            if(!tower.isPlaced() && !checkCollisionOfTower(tower, windowHeight, windowWidth)){
                tower.setCollision(false);


            }
            else if(!tower.isPlaced() && checkCollisionOfTower(tower, windowHeight, windowWidth)){
                tower.setCollision(true);
            }

        }
    }

    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     * @param buttonWidth The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x The X-position of the mouse
     * @param y The Y-position of the mouse
     * @param windowHeight The height of the window
     */

    public void dragEnd(int buttonWidth, int buttonHeight, int x, int y, int windowHeight) {

        if(!newTower.getCollision()){

            newTower.placeTower();
            newTower.setPos(x - buttonWidth,(windowHeight - y - buttonHeight ) );
            newTower.setRectangle();
            player.decreaseMoney(newTower.getCost());
        }
        else{
            towersList.remove(newTower);

        }
    }


    /**
     * Handles when a placed tower is clicked
     */
    public void towerClicked(float x, float y) {
        // Algorithm for finding which tower is clicked
        for (ITower tower : towersList) {
            float towerCenterX = tower.getX() + tower.getWidth()/2;
            float towerCenterY = tower.getY() + tower.getHeight()/2;
            if (Math.sqrt(Math.pow(towerCenterX - x, 2) + Math.pow(towerCenterY - y, 2)) <= tower.getWidth()) {
                clickedTower = tower;
            }
        }

    }

    public void towerNotClicked() {
        clickedTower = null;
    }

    public ITower getClickedTower() {
        return clickedTower;
    }

    /**
     * Return the list of towers on map
     * @return The list of towers
     */
    public List<ITower> getTowers() {
        return towersList;
    }


    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    public List<IVirus> getViruses() {
        return allViruses;
    }

    /**
     * Return the list of projectiles
     * @return list of projectiles
     */
    public List<IProjectile> getProjectilesList() {
        return projectilesList;
    }

    /**
     * Returns if virus list is empty
     * @return true - if all viruses are cleared, false - if there are viruses left
     */
    public boolean isVirusCleared() { return allViruses.isEmpty(); }

    /**
     * Method to call when round is cleared, makes map ready for next round
     */
    public void roundClear() { projectilesList.clear(); }
}
