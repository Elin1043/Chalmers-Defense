package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechTower
 */
public class MechTower extends Tower {


    private List<MechMiniTower> miniTowers = new ArrayList<>();

    private float x;
    private float y;
    private int attackSpeed;
    private int range;
    private List<ITargetMode> targetModes;
    private Projectile projectile;

    public MechTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes, Projectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
        this.x = x;
        this.y = y;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.projectile = projectile;
        this.range = range;


    }

    public List<MechMiniTower> createMiniTowers(){
            MechMiniTower miniTower1 = new MechMiniTower(this.getPosX() + 100,this.getPosY() - 100,attackSpeed,range,targetModes,projectile);
            MechMiniTower miniTower2 = new MechMiniTower(this.getPosX() - 100,this.getPosY() - 100,attackSpeed,range,targetModes,projectile);

            miniTowers.add(miniTower1);
            miniTowers.add(miniTower2);
            return miniTowers;
    }

    @Override
    public Projectile shootProjectile(){
        this.setAngle(0);
        return null;
    }



    public List<MechMiniTower> getMiniTowers() {
        return miniTowers;
    }
}
