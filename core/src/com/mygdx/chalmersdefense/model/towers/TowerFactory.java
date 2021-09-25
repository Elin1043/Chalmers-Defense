package com.mygdx.chalmersdefense.model.towers;



import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;

/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {

    public static Tower CreateSmurf(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "SmurfTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    public static Tower CreateChemist(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ChemistTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    public static Tower CreateHacker(int startPosX, int startPosY){

        return new Tower(startPosX,startPosY, "HackerTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    public static Tower CreateElectro(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ElectroTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }


    public static Tower CreateMeck(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "MeckTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    public static Tower CreateEco(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "EcoTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }




}
