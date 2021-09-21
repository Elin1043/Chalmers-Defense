package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.Model.Path.Path;

import java.util.*;

import static java.lang.Math.abs;

/**
 * @author
 *
 *
 * @Modified by Elin Forsberg
 *  Added methods to handle placing towers + cost of towers
 */

public class Model {
    ChalmersDefense game;
    ArrayList<Tower> towersList = new ArrayList<>();


    Tower newTower;
    TowerFactory factory;
    Path path;

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



    private boolean checkMapAndTowerCollision(Circle circ, Rectangle rect)
    {

        float distancex = 0;
        float distancey = 0;
        distancex = abs(circ.x - rect.x);
        distancey = abs(circ.y - rect.y);
        if (distancex > (rect.width/2 + circ.radius)) { return false; }
        if (distancey > (rect.height/2 + circ.radius)) { return false; }
        if (distancex <= (rect.width/2)) { return true; }
        if (distancey <= (rect.height/2)) { return true; }
        int cDist_sq = (int) (distancex - rect.width / 2) ^ 2 + (int) (distancey - rect.height / 2) ^ 2;

        return (cDist_sq <= ((int)circ.radius^2));
    }


    private boolean checkCollisionOfTowers(Tower tower) {
        for(Tower checkTower: towersList){
            if(tower.getCircle().overlaps(checkTower.getCircle()) && !(checkTower.hashCode() == tower.hashCode())){
                return true;
            }
            else if(!(-20 < (tower.getPosX() - tower.getRange())) || (1580 < (tower.getPosX() + tower.getRange()))){
                    return true;
            }
            else if(!(-20 < (tower.getPosY() - tower.getRange())) || (1000 < (tower.getPosY() + tower.getRange()))){
                return true;
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
        newTower.getSprite().setPosition( Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) - button.getImage().getHeight()/2 );
        newTower.setCircle();
    }

    public void dragEnd(InputEvent event) {
        ImageButton button = (ImageButton) event.getListenerActor();
        if(!newTower.getCollision()){
            newTower.setPlaced(true);
            newTower.getSprite().setPosition(Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight()  - Gdx.input.getY()) - button.getImage().getHeight()/2 );
            newTower.setPos(Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) - button.getImage().getHeight()/2);
            newTower.setCircle();


        }
        else{
            towersList.remove(newTower);
        }
    }

    public void towerClicked() {
        //TODO fill
        System.out.println("Tower clicked");

    }
}
