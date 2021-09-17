package com.mygdx.chalmersdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.chalmersdefense.Models.Tower;


public class TowerFactory {
    private Sprite smurfImage;
    private Sprite chemistImage;
    private Sprite electroImage;


    public Tower CreateSmurf(int startPosX, int startPosY){
        smurfImage = new Sprite(new Texture("Towers/Smurf.png"));
        Tower smurf = new Tower(startPosX,startPosY, smurfImage, "SmurfTower", 10,10);

        return smurf;
    }

    public Tower CreateElectro(int startPosX, int startPosY){
        electroImage = new Sprite(new Texture("Towers/Electro.png"));
        Tower electro = new Tower(startPosX,startPosY, electroImage, "ElectroTower", 20,10);
        return electro;
    }

    public Tower CreateChemist(int startPosX, int startPosY){
        chemistImage = new Sprite(new Texture("Towers/Chemist.png"));
        Tower chemist = new Tower(startPosX,startPosY, chemistImage, "ChemistTower", 30,10);
        return chemist;
    }


}
