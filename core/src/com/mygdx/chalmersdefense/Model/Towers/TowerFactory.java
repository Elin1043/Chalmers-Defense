package com.mygdx.chalmersdefense.Model.Towers;


/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {


    public static Tower CreateSmurf(int startPosX, int startPosY){
        Tower smurf = new SmurfTower(startPosX,startPosY);

        return smurf;
    }

    public static Tower CreateChemist(int startPosX, int startPosY){
        Tower chemist = new ChemistTower(startPosX,startPosY);
        return chemist;
    }

    public static Tower CreateHacker(int startPosX, int startPosY){
        Tower hacker = new HackerTower(startPosX,startPosY);

        return hacker;
    }

    public static Tower CreateElectro(int startPosX, int startPosY){
        Tower electro = new ElectroTower(startPosX,startPosY);
        return electro;
    }


    public static Tower CreateMeck(int startPosX, int startPosY){
        Tower meck = new MeckTower(startPosX,startPosY);
        return meck;
    }

    public static Tower CreateEco(int startPosX, int startPosY){
        Tower eco = new EcoTower(startPosX,startPosY);
        return eco;
    }




}
