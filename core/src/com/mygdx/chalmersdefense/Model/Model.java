package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.TowerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    ChalmersDefense game;
    ArrayList<Tower> towersList = new ArrayList<>();



    HashMap<Tower, ImageButton> towerButtons = new HashMap<>();
    Tower newTower;
    TowerFactory factory;



    private int money = 300;

    public Model(ChalmersDefense game){
        this.game = game;
        factory = new TowerFactory();
    }

    public void updateModel(){
        updateTowers();
    }

    private void updateTowers(){
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




    private boolean checkCollisionOfTowers(Tower tower){
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
    public ArrayList<Tower> getTowers(){
        return towersList;
    }


    public void dragStart(InputEvent event){
        String towerName = event.getListenerActor().getName();
        ImageButton button = (ImageButton) event.getListenerActor();
        switch(towerName){
            case "smurf":
                newTower = factory.CreateSmurf((int)button.getX(), (int)button.getY());
                break;

            case "chemist":
                newTower = factory.CreateChemist((int)button.getX(), (int)button.getY());
                break;

            case "electro":
                newTower = factory.CreateElectro((int)button.getX(), (int)button.getY());
                break;

            case "hacker":
                newTower = factory.CreateHacker((int)button.getX(), (int)button.getY());
                break;

            case "meck":
                newTower = factory.CreateMeck((int)button.getX(), (int)button.getY());
                break;

            case "eco":
                newTower = factory.CreateEco((int)button.getX(), (int)button.getY());
                break;
            default:
                return;
        }

        towersList.add(newTower);
    }

    public void onDrag(InputEvent event){
        ImageButton button = (ImageButton) event.getListenerActor();
        newTower.getSprite().setPosition( Gdx.input.getX() - button.getImage().getWidth()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) - button.getImage().getHeight()/2 );
        newTower.setCircle();
    }

    public void dragEnd(InputEvent event){
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
