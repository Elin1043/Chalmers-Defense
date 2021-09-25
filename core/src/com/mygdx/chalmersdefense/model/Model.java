package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.path.GamePaths.ClassicPath;
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
 */

public class Model {
    private ChalmersDefense game;
    private final List<Tower> towersList = new ArrayList<>();
    private List<Projectile> projectilesList = new ArrayList<>();


    private Tower newTower;



    private final Path path;

    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);

    private int money = 300;





    public Model(ChalmersDefense game) {
        this.game = game;
        path = new ClassicPath();           // Make a path factory instead?
    }

    //Update all the components in model
    public void updateModel() {
        updateVirus();
        updateTowers();
        updateProjectiles();


    }

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

    private boolean checkIfOutOfBounds(float y, float x){
        if(y > 1130 || -50 > y){
            return true;
        }
        if(x > 1970 || -50 > x){
            return true;
        }
        return false;
    }

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

    // TODO Should be divided into smaller methods???
    private boolean checkCollisonOfProjectiles(Projectile projectile){
        for (Rectangle rectangle: path.getCollisionRectangles()) {
            if(Calculate.objectsIntersects(projectile,rectangle)){
                for (Virus virus: getViruses()) {
                    if(Calculate.objectsIntersects(projectile,virus)){
                        virus.decreaseHealth();
                        return true;
                    }
                }
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


    //Return money
    public int getMoney() {
        return money;
    }


    //Return list of towers on map
    public List<Tower> getTowers() {
        return towersList;
    }

    public List<Virus> getViruses() {
        return allViruses;
    }
    // TODO This should be gone later!!
    public SpawnViruses getVirusSpawner() {
        return virusSpawner;
    }

    //Create a tower when user draged from TowerButton
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

    public List<Projectile> getProjectilesList() {
        return projectilesList;
    }



    //While dragging the tower, follow the mouse
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

    //When let go of tower, check if valid spot to let go.
    //If not valid: remove tower
    //If valid: place tower
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

    //Function for when a placed tower is clicked
    public void towerClicked() {
        //TODO fill
        System.out.println("Tower clicked");

    }
}
