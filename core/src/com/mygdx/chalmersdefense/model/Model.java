package com.mygdx.chalmersdefense.model;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.chalmersdefense.ChalmersDefense;

import java.util.*;

/**
 *
 *
 *
 *
 *  2021-09-20 Modified by Elin Forsberg: Added methods to handle placing towers + cost of towers
 *  2021-09-22 Modified by Daniel Persson: Added support for storing a clicked tower and added algorithm for finding what tower is being clicked.
 */

public class Model {
    ChalmersDefense game;
    ArrayList<Tower> towersList = new ArrayList<>();


    Tower newTower;
    private Tower clickedTower;
    TowerFactory factory;

    private List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);

    private int money = 300;




    public Model(ChalmersDefense game) {
        this.game = game;
        factory = new TowerFactory();
    }

    public void updateModel() {
        updateTowers();
        updateVirus();
    }

    private void updateTowers() {
        for (Tower tower: towersList) {
            tower.setPos(tower.getSprite().getX(), tower.getSprite().getY());

            if(!tower.isPlaced() && !checkCollisionOfTowers(tower)){
                tower.setCollision(false);

            }
            else if(!tower.isPlaced() && checkCollisionOfTowers(tower)){
                tower.setCollision(true);
            }

        }
    }

    // TODO Try to fix concurrent modification error in list. Then the try-catch block can be removed
    private void updateVirus() {
        try {
            for (Virus virus : allViruses){ // Om den lägger till ett virus exakt samtidigt blir det inte bra
                virus.update();
            }

        } catch (ConcurrentModificationException e) {
            System.out.println("FAIL when updating Virus");

            for (Virus virus : allViruses){ // Om den lägger till ett virus exakt samtidigt blir det inte bra
                virus.update();
            }
        }
    }




    private boolean checkCollisionOfTowers(Tower tower) {
        for(Tower checkTower: towersList){
            if(tower.getCircle().overlaps(checkTower.getCircle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            else{
                if(!(-20 < (tower.getPosX() - tower.getRange())) || (1580 < (tower.getPosX() + tower.getRange()))){
                    return true;
                }
                if(!(-20 < (tower.getPosY() - tower.getRange())) || (1000 < (tower.getPosY() + tower.getRange()))){
                    return true;
                }
            }
        }
        return false;

    }


    public int getMoney() {
        return money;
    }

    // Ska vi använda Arraylist eller bara List ?
    public ArrayList<Tower> getTowers() {
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


    public void dragStart(InputEvent event) {
        String towerName = event.getListenerActor().getName();
        ImageButton button = (ImageButton) event.getListenerActor();
        switch(towerName){
            case "smurf"   -> newTower = factory.CreateSmurf((int)button.getX(), (int)button.getY());
            case "chemist" -> newTower = factory.CreateChemist((int)button.getX(), (int)button.getY());
            case "electro" -> newTower = factory.CreateElectro((int)button.getX(), (int)button.getY());
            case "hacker"  -> newTower = factory.CreateHacker((int)button.getX(), (int)button.getY());
            case "meck"    -> newTower = factory.CreateMeck((int)button.getX(), (int)button.getY());
            case "eco"     -> newTower = factory.CreateEco((int)button.getX(), (int)button.getY());
            default        -> { return; }
        }

        towersList.add(newTower);
    }

    public void onDrag(InputEvent event) {
        ImageButton button = (ImageButton) event.getListenerActor();
        newTower.getSprite().setPosition(event.getStageX() - button.getImage().getWidth()/2,event.getStageY() - button.getImage().getHeight()/2 );
        newTower.setCircle();
    }

    public void dragEnd(InputEvent event) {
        ImageButton button = (ImageButton) event.getListenerActor();
        if(!newTower.getCollision()){
            newTower.setPlaced(true);
            newTower.getSprite().setPosition(event.getStageX() - button.getImage().getWidth()/2,event.getStageY() - button.getImage().getHeight()/2 );
            newTower.setPos(event.getStageX() - button.getImage().getWidth()/2,event.getStageY() - button.getImage().getHeight()/2);
            newTower.setCircle();


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
