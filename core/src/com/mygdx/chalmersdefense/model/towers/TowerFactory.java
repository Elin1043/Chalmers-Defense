package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.Player;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.utilities.PathRectangle;

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
    public static ITower createSmurf(float startPosX, float startPosY) {
        return new SmurfTower(startPosX, startPosY, "IT-Smurf", 60 * 2, 100, 200);
    }

    /**
     * Creates a chemistTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createChemist(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new ChemistTower(startPosX, startPosY, "Chemist", 60 * 7, 200, 200, addProjectileToList);
    }

    /**
     * Creates a hackerTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createHacker(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new HackerTower(startPosX, startPosY, "Hackerman", 60 * 4, 300, 700, addProjectileToList);
    }

    /**
     * Creates a electroTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createElectro(float startPosX, float startPosY) {
        return new ElectroTower(startPosX, startPosY, "Electroman", 60 * 5, 400, 200);
    }


    /**
     * Creates a mechTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createMech(float startPosX, float startPosY, List<ITower> towerToAddList, List<ITower> allTowers, List<PathRectangle> pathRectangles) {
        return new MechTower(startPosX, startPosY, "Mechoman", 60 * 3, 500, 200, towerToAddList, allTowers, pathRectangles);
    }

    /**
     * Creates a ecoTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createEco(float startPosX, float startPosY, Player player) {
        return new EcoTower(startPosX, startPosY, "Economist", 60 * 3, 600, 200, player);
    }


}
