package com.mygdx.chalmersdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.mygdx.chalmersdefense.Model.Tower;


public class TowerFactory {
    private Sprite smurfImage;
    private Sprite chemistImage;
    private Sprite electroImage;
    private Sprite hackerImage;
    private Sprite meckImage;
    private Sprite ecoImage;


    public Tower CreateSmurf(int startPosX, int startPosY){
        smurfImage = new Sprite(new Texture("Towers/Smurf.png"));
        Tower smurf = new Tower(startPosX,startPosY, smurfImage, "SmurfTower", 10,10, 100);

        return smurf;
    }

    public Tower CreateChemist(int startPosX, int startPosY){
        chemistImage = new Sprite(new Texture("Towers/Chemist.png"));
        Tower chemist = new Tower(startPosX,startPosY, chemistImage, "ChemistTower", 30,10, 200);
        return chemist;
    }

    public Tower CreateHacker(int startPosX, int startPosY){
        hackerImage = new Sprite(new Texture("Towers/Hacker.png"));
        Tower hacker = new Tower(startPosX,startPosY, hackerImage, "HackerTower", 10,10, 300);

        return hacker;
    }

    public Tower CreateElectro(int startPosX, int startPosY){
        electroImage = new Sprite(new Texture("Towers/Electro.png"));
        Tower electro = new Tower(startPosX,startPosY, electroImage, "ElectroTower", 20,10, 400);
        return electro;
    }


    public Tower CreateMeck(int startPosX, int startPosY){
        meckImage = new Sprite(new Texture("Towers/Meck.png"));
        Tower meck = new Tower(startPosX,startPosY, meckImage, "MeckTower", 30,10, 500);
        return meck;
    }

    public Tower CreateEco(int startPosX, int startPosY){
        ecoImage = new Sprite(new Texture("Towers/Eco.png"));
        Tower eco = new Tower(startPosX,startPosY, ecoImage, "EcoTower", 20,10, 600);
        return eco;
    }




}
