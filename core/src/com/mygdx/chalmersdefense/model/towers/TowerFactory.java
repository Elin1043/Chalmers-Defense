package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.Player;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;

import java.util.List;


/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 */

public abstract class TowerFactory {


    /**
     * Creates a smurfTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateSmurf(float startPosX, float startPosY) {
        return new SmurfTower(startPosX, startPosY, "IT-Smurf", 60 * 3, 100, 200, TargetModeFactory.getTargetModes());
    }

    /**
     * Creates a chemistTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateChemist(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new ChemistTower(startPosX, startPosY, "Chemist", 60 * 3, 200, 200, TargetModeFactory.getTargetModes(), addProjectileToList);
    }

    /**
     * Creates a hackerTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateHacker(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new HackerTower(startPosX, startPosY, "Hackerman", 60 * 3, 300, 700, TargetModeFactory.getTargetModes(), addProjectileToList);
    }

    /**
     * Creates a electroTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateElectro(float startPosX, float startPosY) {
        return new ElectroTower(startPosX, startPosY, "Electroman", 60 * 3, 400, 200, TargetModeFactory.getTargetModes());
    }


    /**
     * Creates a mechTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateMech(float startPosX, float startPosY, List<ITower> towerToAddList) {
        return new MechTower(startPosX, startPosY, "Mechoman", 60 * 3, 500, 200, TargetModeFactory.getTargetModes(), towerToAddList);
    }

    /**
     * Creates a ecoTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower CreateEco(float startPosX, float startPosY, Player player) {
        return new EcoTower(startPosX, startPosY, "Economist", 60 * 3, 600, 200, TargetModeFactory.getTargetModes(), player);
    }


}
