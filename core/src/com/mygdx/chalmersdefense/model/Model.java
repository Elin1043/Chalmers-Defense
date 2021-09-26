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
 * 2021-09-24 Modified by Elin Forsberg: Added methods to handle projectiles
 */

public class Model {
    private final ChalmersDefense game;
    private final List<Tower> towersList = new ArrayList<>();
    private final List<Projectile> projectilesList = new ArrayList<>();
    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);
    private final Rounds round = new Rounds(10);    // 10 is temporary

    private Tower newTower;

    private final Path path;

    private final Player player = new Player(100, 600); //Change staring capital later. Just used for testing right now





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
                if(!(projectile instanceof LightningProjectile)){
                    removeProjectiles.add(projectile);
                }

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
        return (x > 1970) || (-50 > x);
    }

    //Update all the towers
    private void updateTowers(){
        for (Tower tower: towersList) {
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
            else{
                if(tower instanceof EcoTower){
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

                }
                allViruses.remove(virus);
            }

            if (allViruses.isEmpty() && !virusSpawner.isSpawning()) {
                stopGameUpdate();
                System.out.println("Stop TIMER");
            }

        }

    }

    private void stopGameUpdate() {
        game.stopModelUpdate();
    }

    private void startGameUpdate() {
        game.startModelUpdate();
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
        boolean collided = false;
        for (Virus virus: getViruses()) {
            if(Calculate.objectsIntersects(projectile,virus)){
                    if(projectile instanceof AcidProjectile){
                            collidedWithAcid(projectile);
                    }
                    else if(projectile instanceof LightningProjectile){
                        collidedWithLightning(projectile);
                    }
                    else{
                        if(!projectile.getIfDealtDamage()){
                            virus.decreaseHealth();
                            projectile.setDealtDamage(true);
                        }

                    }

                collided = true;
            }
        }
        return collided;
    }

    //Collison with lightning projectile
    //Does not work properly
    private void collidedWithLightning(Projectile projectile){
        int count = 5;
        if(!projectile.getIfDealtDamage()){
            List<Virus> virusInRange = Calculate.getVirusesInRange(projectile.getX(),projectile.getY(),((LightningProjectile) projectile).getRange(), allViruses);
            if(!virusInRange.isEmpty()){
                for (int i = 0; i < virusInRange.size() ; i++) {
                        if((i < virusInRange.size() - 1)){
                            virusInRange.get(i).decreaseHealth();
                            double angle = Calculate.angleDeg(virusInRange.get(i+1).getX(),virusInRange.get(i+1).getY(), virusInRange.get(i).getX(),virusInRange.get(i).getY());
                            projectile.setAngle(angle);
                            projectile.move();
                            count--;
                        }
                        else {
                            virusInRange.get(i).decreaseHealth();
                        }


                    if(count <= 0){
                        projectilesList.remove(projectile);
                        break;
                    }
                }
            }
            projectile.setDealtDamage(true);
        }

    }

    //Collison with acid projectile
    private void collidedWithAcid(Projectile projectile){
        if(!projectile.getIfDealtDamage()){
        for (Virus virus:getViruses()) {
                if (Calculate.disBetweenPoints(projectile.getX(), projectile.getY(), virus.getX() ,virus.getY() ) < ((AcidProjectile) projectile).getRange() * ((AcidProjectile) projectile).getRange()){
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

    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    public List<Virus> getViruses() {
        return allViruses;
    }

    // TODO This should be gone later!!
    public SpawnViruses getVirusSpawner() {
        return virusSpawner;
    }

    /**
     * Starts spawning viruses based on which is the current round
     */
    public void startRoundPressed() {
        if (!virusSpawner.isSpawning()) {
            startGameUpdate();
            round.incrementToNextRound();
            System.out.println("START TIMER");
            virusSpawner.spawnRound(round.getCurrentRound());
        } else {

            // Here we can speed up or slow down round later

        }
    }

    /**
     * Return the list of projectiles
     * @return list of projectiles
     */
    public List<Projectile> getProjectilesList() {
        return projectilesList;
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


    /**
     * Handles when a placed tower is clicked
     */
    public void towerClicked() {
        //TODO fill
        System.out.println("Tower clicked");

    }
}
