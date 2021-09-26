package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

public class MechMiniTower extends MechTower{
    private static String name = "MechMiniTower";
    private static int cost = 0;
    private static int range = 100;

    private int currentReload;
    private int reloadTime = 60*5; //how many updates from model

    private float x;
    private float y;
    private int attackSpeed;
    private List<ITargetMode> targetModes;
    private Projectile projectile;

    public MechMiniTower(float x, float y ,int attackSpeed, List<ITargetMode> targetModes, Projectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
        this.x = x;
        this.y = y;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.projectile = projectile;
    }

    @Override
    public Projectile shootProjectile(){
        if(currentReload < 1 && this.GotTarget() && this.isPlaced()){
            projectile = projectile.createProjectile(attackSpeed, x + this.getWidth()/2, y + this.getHeight()/2, this.getAngle());
            currentReload = reloadTime;
            return projectile;
        }
        else{
            currentReload --;
        }
        return null;
    }


}
