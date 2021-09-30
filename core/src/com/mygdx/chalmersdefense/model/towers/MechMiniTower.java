package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechMiniTowers that MechTower creates
 */
public class MechMiniTower extends Tower{
    private static String name = "MechMiniTower";
    private static int cost = 0;



    public MechMiniTower(float x, float y ,int attackSpeed,int range, List<ITargetMode> targetModes, Projectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
    }


}
