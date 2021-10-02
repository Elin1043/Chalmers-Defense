package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.customExceptions.PlayerLostAllLifeException;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.projectiles.AcidProjectile;
import com.mygdx.chalmersdefense.model.projectiles.LightningProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.towers.EcoTower;
import com.mygdx.chalmersdefense.model.towers.MechTower;
import com.mygdx.chalmersdefense.model.towers.Tower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
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
    private Tower newTower;
    private Tower clickedTower;
    private final List<Tower> towersList = new ArrayList<>();
    private final List<Projectile> projectilesList = new ArrayList<>();
    private final List<Virus> allViruses = new ArrayList<>();

    //Should not have player here
    private final Player player;
    private final Path path = new ClassicPath();           // Make a path factory instead?;

    private boolean isGameLost;


    public Map(Player player){
        this.player = player;
        isGameLost = false;
    }

    public void updateMap() {
        updateVirus();
        updateTowers();
        updateProjectiles();
    }


    //Update the projectiles
    private void updateProjectiles() {
        List<Projectile> removeProjectiles = new ArrayList<>();

        for (Projectile projectile : projectilesList) {
            projectile.move();
            if (checkCollisonOfProjectiles(projectile, removeProjectiles) || checkIfOutOfBounds(projectile.getY(), projectile.getX())) {
                if (!(projectile instanceof LightningProjectile)) {
                    removeProjectiles.add(projectile);
                }
            }
        }
        for (Projectile projectile : removeProjectiles) {
            projectilesList.remove(projectile);
        }
    }


    //Update all the towers
    private void updateTowers() {

        for (Tower tower : towersList) {
            List<Virus> virusInRange;

            synchronized (allViruses) {
                virusInRange = Calculate.getVirusesInRange(tower.getPosX(), tower.getPosY(), tower.getRange(), allViruses);
            }

            if (virusInRange.size() > 0) {
                Virus targetVirus = tower.getCurrentTargetMode().getRightVirus(virusInRange, tower.getPosX(), tower.getPosY());
                tower.setAngle(Calculate.angleDeg(targetVirus.getX(), targetVirus.getY(), tower.getPosX(), tower.getPosY()));
                tower.haveTarget();
            } else {
                tower.notHaveTarget();
            }

            Projectile projectile = tower.shootProjectile();

            if (projectile != null) {
                projectilesList.add(projectile);
                for (Virus virus : allViruses) {virus.setGotHit(false);}
            } else {
                if (tower instanceof EcoTower) {
                    player.increaseMoney(((EcoTower) tower).getMoneyEarned());
                }
            }
        }
    }


    //Update all the viruses
    private void updateVirus(){
        synchronized (allViruses) {
            List<Virus> virusToRemove = new ArrayList<>();

            for (Virus virus : allViruses) {
                if (virus.getY() > 1130 || virus.isDead()) {
                    virusToRemove.add(virus);
                    if(virus.isDead()){
                        player.increaseMoney(1); //Change amount later
                    }
                }
                virus.update();
            }
            for (Virus virus : virusToRemove){
                try {
                    player.decreaseLivesBy(virus.getLifeDecreaseAmount());
                } catch (PlayerLostAllLifeException ignore){

                    // Här ska man hantera ifall man förlorar spelet
                    isGameLost = true;
                }
                allViruses.remove(virus);
            }



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
    private boolean checkMapAndTowerCollision(Tower tower) {
        for (java.awt.Rectangle rect : path.getCollisionRectangles()) {
            if (tower.getRectangle().intersects(rect)) {
                return true;
            }

        }
        return false;
    }


    //Checks if projectile collided with path, then virus
    private boolean checkCollisonOfProjectiles(Projectile projectile, List<Projectile> list){
        for (Rectangle rectangle: path.getCollisionRectangles()) {
            if(Calculate.objectsIntersects(projectile,rectangle)){
                return checkVirusAndProjectileCollision(projectile, list);
            }
        }
        return false;
    }

    //Helper method for collision between virus and projectile
    private boolean checkVirusAndProjectileCollision(Projectile projectile, List<Projectile> list){
        boolean collided = false;

        synchronized (allViruses) {
            for (Virus virus : allViruses) {
                if (Calculate.objectsIntersects(projectile, virus)) {
                    if (projectile instanceof AcidProjectile) {
                        collidedWithAcid(projectile);
                    } else if (projectile instanceof LightningProjectile) {
                        collidedWithLightning(projectile, virus, list);

                    } else {
                        if (!projectile.getIfDealtDamage()) {
                            virus.decreaseHealth();
                            projectile.setDealtDamage(true);
                        }

                    }

                    collided = true;
                }
            }
        }
        return collided;
    }

    //Collision with lightning projectile
    private void collidedWithLightning(Projectile projectile, Virus virus, List<Projectile> list){
        List<Virus> virusToRemove = new ArrayList<>();
        if(!projectile.getIfDealtDamage()){
            if(!virus.getIfGotHit()){
                virus.decreaseHealth();
                projectile.virusHit();
                virus.setGotHit(true);

                List<Virus> virusInRange = Calculate.getVirusesInRange(virus.getX() + virus.getWidth()/2F, virus.getY() + virus.getHeight()/2F, ((LightningProjectile) projectile).getRange(), allViruses);

                for (Virus virusInList: virusInRange) {
                    if(virusInList.getIfGotHit()){
                        virusToRemove.add(virusInList);
                    }
                }
                virusInRange.removeAll(virusToRemove);


                if(!virusInRange.isEmpty()){
                    Virus tempVirus = virusInRange.get(0);
                    projectile.setAngle(Calculate.angleDeg(tempVirus.getX() + tempVirus.getWidth()/2F, tempVirus.getY() + tempVirus.getHeight()/2F,projectile.getX() + projectile.getWidth()/2F, projectile.getY() + projectile.getHeight()/2F));

                }
                else{
                    list.add(projectile);
                }
            }

        }
        else{
            list.add(projectile);
        }
    }


    //Collision with acid projectile
    private void collidedWithAcid(Projectile projectile){
        if(!projectile.getIfDealtDamage()){
            for (Virus virus:getViruses()) {
                if (Calculate.disBetweenPoints(projectile.getX() + projectile.getWidth()/2F, projectile.getY() + projectile.getHeight()/2F, virus.getX() + virus.getWidth()/2F ,virus.getY() + virus.getHeight()/2F ) < ((AcidProjectile) projectile).getRange() * ((AcidProjectile) projectile).getRange()){
                    virus.decreaseHealth();
                }
            }
            projectile.setDealtDamage(true);
        }
    }


    //Checks if towers collide with anything
    private boolean checkCollisionOfTower(Tower tower, int windowHeight, int windowWidth) {
        for(Tower checkTower: towersList){
            //Check if tower collides with a placed tower
            if(tower.getRectangle().intersects(checkTower.getRectangle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            //Check if tower out of bound on X
            else if(!(0 <= (tower.getPosX())) || (windowWidth - 340 < (tower.getPosX() + tower.getWidth()/2))){
                return true;
            }
            //Check if tower out of bound on Y
            else if(!(windowHeight - 950 < (tower.getPosY() - tower.getHeight()/2)) || (windowHeight < (tower.getPosY()) + tower.getHeight())){
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
            case "eco"     -> newTower = TowerFactory.CreateEco(x, y);
            default        -> { return; }
        }

        towersList.add(newTower);
        if(newTower instanceof MechTower){
            towersList.addAll(((MechTower) newTower).createMiniTowers());
        }
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
        if(newTower instanceof MechTower){
            ((MechTower) newTower).getMiniTowers().get(0).setPos(newTower.getPosX() - 50 , newTower.getPosY() - 50);
            ((MechTower) newTower).getMiniTowers().get(1).setPos(newTower.getPosX() + 70 , newTower.getPosY() - 50);
            ((MechTower) newTower).getMiniTowers().get(0).setRectangle();
            ((MechTower) newTower).getMiniTowers().get(1).setRectangle();
        }


        for (Tower tower: towersList) {

            if(!tower.isPlaced() && !checkCollisionOfTower(tower, windowHeight, windowWidth)){
                tower.setCollision(false);
                if(newTower instanceof  MechTower){
                    if(checkCollisionOfTower(((MechTower) newTower).getMiniTowers().get(0), windowHeight, windowWidth) || checkCollisionOfTower(((MechTower) newTower).getMiniTowers().get(1), windowHeight, windowWidth)){
                        tower.setCollision(true);
                    }
                }

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
            if(newTower instanceof MechTower && !((MechTower) newTower).getMiniTowers().get(0).getCollision() && !((MechTower) newTower).getMiniTowers().get(1).getCollision()){
                ((MechTower) newTower).getMiniTowers().get(0).placeTower();
                ((MechTower) newTower).getMiniTowers().get(1).placeTower();
                ((MechTower) newTower).getMiniTowers().get(0).setRectangle();
                ((MechTower) newTower).getMiniTowers().get(1).setRectangle();
            }
            newTower.placeTower();
            newTower.setPos(x - buttonWidth,(windowHeight - y - buttonHeight ) );
            newTower.setRectangle();
            player.decreaseMoney(newTower.getCost());
        }
        else{
            towersList.remove(newTower);
            if(newTower instanceof MechTower){
                towersList.remove(((MechTower) newTower).getMiniTowers().get(0));
                towersList.remove(((MechTower) newTower).getMiniTowers().get(1));
            }
        }
    }


    /**
     * Handles when a placed tower is clicked
     */
    public void towerClicked(float x, float y) {
        // Algorithm for finding which tower is clicked
        for (Tower tower : towersList) {
            float towerCenterX = tower.getPosX() + tower.getWidth()/2;
            float towerCenterY = tower.getPosY() + tower.getHeight()/2;
            if (Math.sqrt(Math.pow(towerCenterX - x, 2) + Math.pow(towerCenterY - y, 2)) <= tower.getWidth()) {
                clickedTower = tower;
            }
        }
        isGameLost = true;

    }

    /**
     * Clears clickedTower variable.
     */
    public void towerNotClicked() {
        clickedTower = null;
        isGameLost = false;
    }

    /**
     * Returns currently clicked tower
     * @return tower object of clicked tower
     */
    public Tower getClickedTower() {
        return clickedTower;
    }

    /**
     * Returns if game has been lost
     * @return a boolean for game lost status
     */
    public boolean getIsGameLost() {
        return isGameLost;
    }

    /**
     * Return the list of towers on map
     * @return The list of towers
     */
    public List<Tower> getTowers() {
        return towersList;
    }


    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    public List<Virus> getViruses() {
        return allViruses;
    }

    /**
     * Return the list of projectiles
     * @return list of projectiles
     */
    public List<Projectile> getProjectilesList() {
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
