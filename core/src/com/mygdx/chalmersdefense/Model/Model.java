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
 * @Modified by Elin Forsberg
 *  Added methods to handle towers + collisions
 */

public class Model {
    private ChalmersDefense game;
    private final ArrayList<Tower> towersList = new ArrayList<>();


    private final ArrayList<Rectangle> collisionRectangles = new ArrayList<>();


    private Tower newTower;
    private TowerFactory factory;



    private final Path path;

    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);

    private int money = 300;





    public Model(ChalmersDefense game) {
        this.game = game;
        factory = new TowerFactory();       // Make factory abstract?
        path = new ClassicPath();           // Make a path factory instead?
        createCollisionOnPath();

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
                allViruses.remove(virus);
            }

        }

    }


    //Function for creating the rectangles on path used for collision   (TODO Detta borde kanske ligga i path?)
    private void createCollisionOnPath(){

//        for (int i = 0; i < path.getListSize() -1; i++) {
//            Rectangle rectangle = new Rectangle();
//            float posX = path.getWaypoint(i).getX();
//            float posY = path.getWaypoint(i).getY();
//            float nextX = path.getWaypoint(i+1).getX();
//            float nextY = path.getWaypoint(i+1).getY();
//            if(posX == nextX){
//                float distY = Math.abs((nextY - posY));
//                if(posY < nextY){
//
//                    rectangle.set(posX-40 , posY -40,80, distY + 80);
//                }
//                else{
//                    rectangle.set(posX-40 , posY -distY -40,80, distY + 80);
//
//                }
//            }
//            else{
//                float distX = Math.abs((nextX - posX));
//                if(posX < nextX){
//
//                    rectangle.set(posX-40 , posY-40, distX, 80);
//                }
//                else{
//                    rectangle.set(posX-40 - distX  , posY-40, distX, 80);
//                }
//
//
//            }
//            collisionRectangles.add(rectangle);
//        }

        try {
            int i = 0;
            while (true){
                Rectangle rectangle = new Rectangle();
                float posX = path.getWaypoint(i).getX();
                float posY = path.getWaypoint(i).getY();
                float nextX = path.getWaypoint(i+1).getX();
                float nextY = path.getWaypoint(i+1).getY();
                if(posX == nextX){
                    float distY = Math.abs((nextY - posY));
                    if(posY < nextY){

                        rectangle.set(posX-40 , posY -40,80, distY + 80);
                    }
                    else{
                        rectangle.set(posX-40 , posY -distY -40,80, distY + 80);

                    }
                }
                else{
                    float distX = Math.abs((nextX - posX));
                    if(posX < nextX){

                        rectangle.set(posX-40 , posY-40, distX, 80);
                    }
                    else{
                        rectangle.set(posX-40 - distX  , posY-40, distX, 80);
                    }


                }
                collisionRectangles.add(rectangle);
                i++;
            }
        } catch (NoFurtherWaypointException ignored) {}

    }

    //Checks if a tower collides with path
    private boolean checkMapAndTowerCollision(Tower tower)
    {
        for (Rectangle rect : collisionRectangles) {
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
