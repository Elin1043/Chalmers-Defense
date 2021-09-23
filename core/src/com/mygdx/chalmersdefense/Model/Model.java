package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Model.CustomExceptions.NoFurtherWaypointException;
import com.mygdx.chalmersdefense.Model.Path.GamePaths.ClassicPath;
import com.mygdx.chalmersdefense.Model.Path.Path;

import java.util.*;
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

    private final ArrayList<Tower> towersList = new ArrayList<>();
    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());

    private Tower newTower;
    private TowerFactory factory;

    private final Player player = new Player(100, 300); //Change staring capital later. Just used for testing right now

    private final Path path;


    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);




    public Model(ChalmersDefense game) {
        this.game = game;
        factory = new TowerFactory();       // Make factory abstract?
        path = new ClassicPath();           // Make a path factory instead?
    }

    //Update all the components in model
    public void updateModel() {
        updateVirus();
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
                player.decreaseLivesBy(virus.getLifeDecreaseAmount());
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
    private boolean checkCollisionOfTower(Tower tower) {
        for(Tower checkTower: towersList){
            //Check if tower collides with a placed tower
            if(tower.getRectangle().overlaps(checkTower.getRectangle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            //Check if tower out of bound on X
            else if(!(0 <= (tower.getPosX())) || (Gdx.graphics.getWidth() - 340 < (tower.getPosX() + tower.getRectangle().width/2))){
                    return true;
            }
            //Check if tower out of bound on Y
            else if(!(Gdx.graphics.getHeight() - 950 < (tower.getPosY() - tower.getHeight()/2)) || (Gdx.graphics.getHeight() < (tower.getPosY()) + tower.getHeight())){
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
        return player.getMoney();
    }


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
            case "smurf"   -> newTower = factory.CreateSmurf(x, y);
            case "chemist" -> newTower = factory.CreateChemist(x, y);
            case "electro" -> newTower = factory.CreateElectro(x, y);
            case "hacker"  -> newTower = factory.CreateHacker(x, y);
            case "meck"    -> newTower = factory.CreateMeck(x, y);
            case "eco"     -> newTower = factory.CreateEco(x, y);
            default        -> { return; }
        }

        towersList.add(newTower);
    }



    //While dragging the tower, follow the mouse
    public void onDrag(int buttonWidth, int buttonHeight, int x, int y, int windowHeight) {

        newTower.setPos( x - buttonWidth,(windowHeight - y - buttonHeight ));
        newTower.setRectangle();

        for (Tower tower: towersList) {

            if(!tower.isPlaced() && !checkCollisionOfTower(tower)){
                tower.setCollision(false);

            }
            else if(!tower.isPlaced() && checkCollisionOfTower(tower)){
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
