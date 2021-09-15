package com.mygdx.chalmersdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.mygdx.chalmersdefense.Models.Tower;


public class TowerFactory {
    private Sprite smurfImage;
    private Sprite chemistImage;
    private Sprite electroImage;


    public Tower CreateSmurf(int startPosX, int startPosY){
        smurfImage = new Sprite(new Texture("Towers/Smurf1.png"));
        Tower smurf = new Tower(new Vectors(startPosX,startPosY), smurfImage, "SmurfTower", 10,10);
        return smurf;
    }

    public Tower CreateElectro(int startPosX, int startPosY){
        chemistImage = new Sprite(new Texture("Towers/Chemist1.png"));
        Tower electro = new Tower(new Vectors(startPosX,startPosY), smurfImage, "ElectroTower", 10,10);
        return electro;
    }

    public Tower CreateChemist(int startPosX, int startPosY){
        electroImage = new Sprite(new Texture("Towers/Electro1.png"));
        Tower chemist = new Tower(new Vectors(startPosX,startPosY), smurfImage, "ChemistTower", 10,10);
        return chemist;
    }


}
