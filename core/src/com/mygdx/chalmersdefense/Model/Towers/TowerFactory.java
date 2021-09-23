package com.mygdx.chalmersdefense.Model.Towers;


import com.mygdx.chalmersdefense.Model.TargetMode.Closest;
import com.mygdx.chalmersdefense.Model.TargetMode.TargetMode;
import com.mygdx.chalmersdefense.Model.TargetMode.TargetModeFactory;

/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {


    private static TargetModeFactory targetModeFactory = new TargetModeFactory();
    private static TargetMode targetModeClosest = targetModeFactory.getClosestTarget();

    public static Tower CreateSmurf(int startPosX, int startPosY){
        Tower smurf = new SmurfTower(startPosX,startPosY, targetModeClosest);

        return smurf;
    }

    public static Tower CreateChemist(int startPosX, int startPosY){
        Tower chemist = new ChemistTower(startPosX,startPosY, targetModeClosest);
        return chemist;
    }

    public static Tower CreateHacker(int startPosX, int startPosY){
        Tower hacker = new HackerTower(startPosX,startPosY, targetModeClosest);

        return hacker;
    }

    public static Tower CreateElectro(int startPosX, int startPosY){
        Tower electro = new ElectroTower(startPosX,startPosY, targetModeClosest);
        return electro;
    }


    public static Tower CreateMeck(int startPosX, int startPosY){
        Tower meck = new MeckTower(startPosX,startPosY, targetModeClosest);
        return meck;
    }

    public static Tower CreateEco(int startPosX, int startPosY){
        Tower eco = new EcoTower(startPosX,startPosY, targetModeClosest);
        return eco;
    }




}
