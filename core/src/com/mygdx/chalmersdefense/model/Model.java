package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.customExceptions.PlayerLostAllLifeException;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.model.towers.Tower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 *
 * 2021-09-20 Modified by Elin Forsberg: Added methods to handle towers + collisions
 * 2021-09-20 Modified by Joel Båtsman Hilmersson: Made updateVirus loop syncronized
 * 2021-09-22 Modified by Daniel Persson: Added support for storing a clicked tower and added algorithm for finding what tower is being clicked.
 * 2021-09-24 Modified by Elin Forsberg: Added methods to handle projectiles
 *
 */

public class Model {
    private ChalmersDefense game;
    private final List<Tower> towersList = new ArrayList<>();
    private List<Projectile> projectilesList = new ArrayList<>();

    private Tower newTower;
    private Tower clickedTower;

    private final Path path;

    private final Player player = new Player(100, 300); //Change staring capital later. Just used for testing right now

    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);



    /**
     * Constructor of the model class
     * @param game current game session
     */
    public Model(ChalmersDefense game) {
        this.game = game;
        path = new ClassicPath();           // Make a path factory instead?
    }

    /**
     * Update all the model components
     */
    public void updateModel() {
        updateVirus();
        updateTowers();
        updateProjectiles();
    }

    //Update the projectiles
    private void updateProjectiles(){
        List<Projectile> removeProjectiles = new ArrayList<>();
        for (Projectile projectile: projectilesList) {
            projectile.move();
            if(checkCollisonOfProjectiles(projectile) ||  checkIfOutOfBounds(projectile.getY(), projectile.getX()) ){
                removeProjectiles.add(projectile);
            }
        }

        for (Projectile projectile: removeProjectiles) {
            projectilesList.remove(projectile);
        }


    }

    //Check if coordinates are outside the screen
    private boolean checkIfOutOfBounds(float y, float x){
        if(y > 1130 || -50 > y){
            return true;
        }
        if(x > 1970 || -50 > x){
            return true;
        }
        return false;
    }

    //Update all the towers
    private void updateTowers(){
        for (Tower tower: towersList) {
            tower.update();

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
            if(projectile != null){
                projectilesList.add(projectile);

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

                }
                virus.update();
            }

            for (Virus virus : virusToRemove){
                try {
                    player.decreaseLivesBy(virus.getLifeDecreaseAmount());
                } catch (PlayerLostAllLifeException ignore){

                    // Här ska man hantera ifall man förlorar spelet

                }
                allViruses.remove(virus);
            }

        }

    }


    //Checks if a tower collides with path
    private boolean checkMapAndTowerCollision(Tower tower)
    {
        for (java.awt.Rectangle rect : path.getCollisionRectangles()) {
            if(tower.getRectangle().intersects(rect)){
                return true;
            }

        }
        return false;
    }

    //Checks if projectile collided with virus
    private boolean checkCollisonOfProjectiles(Projectile projectile){
        for (Rectangle rectangle: path.getCollisionRectangles()) {
            if(Calculate.objectsIntersects(projectile,rectangle)){
                return checkVirusAndProjectileCollision(projectile);
            }
        }
        return false;
    }

    //Helper method for collison between virus and projectile
    private boolean checkVirusAndProjectileCollision(Projectile projectile){
        for (Virus virus: getViruses()) {
            if(Calculate.objectsIntersects(projectile,virus)){
                virus.decreaseHealth();
                return true;
            }
        }
        return false;
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
     * Return the current money value
     * @return the money value
     */
    public int getMoney() { return player.getMoney(); }

    /**
     * Returns the lives left of player
     * @return lives left
     */
    public int getLivesLeft() {return player.getLives(); }

    /**
     * Return the list of towers on map
     * @return The list of towers
     */
    public List<Tower> getTowers() {
        return towersList;
    }

    public Tower getClickedTower() {
        return clickedTower;
    }

    public List<Virus> getViruses() {
        return allViruses;
    }

    // TODO This should be gone later!!
    public SpawnViruses getVirusSpawner() {
        return virusSpawner;
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
    }

    /**
     * Return the list of projectiles
     * @return list of projectiles
     */
    public List<Projectile> getProjectilesList() {
        return projectilesList;
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

        for (Tower tower: towersList) {

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
        }
        else{
            towersList.remove(newTower);
        }
    }

    public void towerClicked(float x, float y) {

        // Algorithm for finding which tower is clicked
        for (Tower tower : towersList) {
            float towerCenterX = tower.getPosX() + tower.getWidth()/2;
            float towerCenterY = tower.getPosY() + tower.getHeight()/2;
            if (Math.sqrt(Math.pow(towerCenterX - x, 2) + Math.pow(towerCenterY - y, 2)) <= tower.getWidth()) {
                clickedTower = tower;
            }
        }
    }

    // Maybe temporary because it sets the object to null.
    public void towerNotClicked() {
        clickedTower = null;
    }
}
