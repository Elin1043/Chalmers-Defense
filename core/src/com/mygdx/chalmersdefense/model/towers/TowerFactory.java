package com.mygdx.chalmersdefense.model.towers;



import com.mygdx.chalmersdefense.model.targetMode.TargetMode;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;

/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {



    private static final TargetMode targetModeClosest = TargetModeFactory.getClosestTarget();
    private static final TargetMode targetModeFirst = TargetModeFactory.getFirstTarget();

    public static Tower CreateSmurf(int startPosX, int startPosY){
        Tower smurf = new Tower(startPosX,startPosY, "SmurfTower", 10, 100, 200, targetModeFirst);

        return smurf;
    }

    public static Tower CreateChemist(int startPosX, int startPosY){
        Tower chemist = new Tower(startPosX,startPosY, "ChemistTower", 10, 100, 200, targetModeFirst);
        return chemist;
    }

    public static Tower CreateHacker(int startPosX, int startPosY){
        Tower hacker = new Tower(startPosX,startPosY, "HackerTower", 10, 100, 200, targetModeFirst);

        return hacker;
    }

    public static Tower CreateElectro(int startPosX, int startPosY){
        Tower electro = new Tower(startPosX,startPosY, "ElectroTower", 10, 100, 200, targetModeFirst);
        return electro;
    }


    public static Tower CreateMeck(int startPosX, int startPosY){
        Tower meck = new Tower(startPosX,startPosY, "MeckTower", 10, 100, 200, targetModeFirst);
        return meck;
    }

    public static Tower CreateEco(int startPosX, int startPosY){
        Tower eco = new Tower(startPosX,startPosY, "EcoTower", 10, 100, 200, targetModeFirst);
        return eco;
    }




}
