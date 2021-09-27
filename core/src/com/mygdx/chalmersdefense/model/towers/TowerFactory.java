package com.mygdx.chalmersdefense.model.towers;



import com.mygdx.chalmersdefense.model.projectiles.AcidProjectile;
import com.mygdx.chalmersdefense.model.projectiles.BulletProjectile;
import com.mygdx.chalmersdefense.model.projectiles.LightningProjectile;
import com.mygdx.chalmersdefense.model.projectiles.RobotProjectile;
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
        return new Tower(startPosX,startPosY, "SmurfTower", 5, 100, 200, TargetModeFactory.getTargetModes(), new BulletProjectile(0,0,0,0));
    }

    /**
     * Creates a chemistTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateChemist(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ChemistTower", 5, 200, 200, TargetModeFactory.getTargetModes(), new AcidProjectile(0,0,0,0));
    }

    /**
     * Creates a hackerTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateHacker(int startPosX, int startPosY){

        return new Tower(startPosX,startPosY, "HackerTower", 5, 300, 200, TargetModeFactory.getTargetModes(), new BulletProjectile(0,0,0,0));
    }

    /**
     * Creates a electroTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateElectro(int startPosX, int startPosY){
        return new Tower(startPosX,startPosY, "ElectroTower", 5, 400, 200, TargetModeFactory.getTargetModes(), new LightningProjectile(0,0,0,0));
    }


    /**
     * Creates a meckTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateMeck(int startPosX, int startPosY){
        return new MechTower(startPosX,startPosY, "MechTower", 3, 500, 200, TargetModeFactory.getTargetModes(), new RobotProjectile(0,0,0,0));
    }

    /**
     * Creates a ecoTower
     * @param startPosX x-coordinate to create tower
     * @param startPosY y-coordinate to create tower
     * @return tower that was created
     */
    public static Tower CreateEco(int startPosX, int startPosY){
        return new EcoTower(startPosX,startPosY, "EcoTower", 5, 600, 200, TargetModeFactory.getTargetModes());
    }




}
