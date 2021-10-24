package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.Player;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.modelUtilities.PathRectangle;

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
        return new SmurfTower(startPosX, startPosY);
    }

    /**
     * Creates a chemistTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createChemist(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new ChemistTower(startPosX, startPosY, addProjectileToList);
    }

    /**
     * Creates a hackerTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createHacker(float startPosX, float startPosY, List<IProjectile> addProjectileToList) {
        return new HackerTower(startPosX, startPosY, addProjectileToList);
    }

    /**
     * Creates a electroTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createElectro(float startPosX, float startPosY) {
        return new ElectroTower(startPosX, startPosY);
    }


    /**
     * Creates a mechTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createMech(float startPosX, float startPosY, List<ITower> towerToAddList, List<ITower> allTowers, List<PathRectangle> pathRectangles) {
        return new MechTower(startPosX, startPosY, towerToAddList, allTowers, pathRectangles);
    }

    /**
     * Creates a ecoTower
     *
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static ITower createEco(float startPosX, float startPosY, Player player) {
        return new EcoTower(startPosX, startPosY, player);
    }


}
