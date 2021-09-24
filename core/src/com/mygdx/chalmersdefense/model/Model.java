package com.mygdx.chalmersdefense.model;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.path.GamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.towers.Tower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;

import java.util.*;
import java.util.List;


/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 *
 * @Modified by Elin Forsberg
 *  Added methods to handle towers + collisions
 */

public class Model {
    private ChalmersDefense game;
    private final ArrayList<Tower> towersList = new ArrayList<>();
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
        for (Projectile projectile: projectilesList) {
            projectile.move();
        }
    }

    private void updateTowers(){
        for (Tower tower: towersList) {
            tower.update((allViruses));
            Projectile projectile = tower.shoot();
            if(projectile != null){
                projectilesList.add(projectile);
            }

        }
    }


    private void updateVirus(){
        synchronized (allViruses) {
            List<Virus> virusToRemove = new ArrayList<>();

            for (Virus virus : allViruses) {
                if (virus.getY() > 1130) {
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
        for (Rectangle rect : path.getCollisionRectangles()) {
            if(tower.getRectangle().overlaps(rect)){
                return true;
            }

        }
        return false;
    }


    //Checks if towers collide with anything
    private boolean checkCollisionOfTower(Tower tower, int windowHeight, int windowWidth) {
        for(Tower checkTower: towersList){
            //Check if tower collides with a placed tower
            if(tower.getRectangle().overlaps(checkTower.getRectangle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            //Check if tower out of bound on X
            else if(!(0 <= (tower.getPosX())) || (windowWidth - 340 < (tower.getPosX() + tower.getRectangle().width/2))){
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


    // Ska vi använda Arraylist eller bara List ?
    //Return list of towers on map
    public ArrayList<Tower> getTowers() {
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
            newTower.setPlaced(true);
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
