package com.mygdx.chalmersdefense.model.towers;



import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;

/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {

    /**
     * Creates a smurfTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateSmurf(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "SmurfTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    /**
     * Creates a chemistTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateChemist(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ChemistTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    /**
     * Creates a hackerTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateHacker(int startPosX, int startPosY){

        return new Tower(startPosX,startPosY, "HackerTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    /**
     * Creates a electroTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateElectro(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ElectroTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }


    /**
     * Creates a meckTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateMeck(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "MeckTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }

    /**
     * Creates a ecoTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateEco(int startPosX, int startPosY){
        return new EcoTower(startPosX,startPosY, "EcoTower", 10, 100, 200, TargetModeFactory.getTargetModes());
    }




}
