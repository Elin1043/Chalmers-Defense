package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.BulletProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

public class EcoTower extends Tower{


    int moneyEarned;
    int currentReload;
    private int reloadTime = 60*5; //how many updates from model

    public EcoTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes) {
        super(x, y, name, attackSpeed, cost, range, targetModes);
        moneyEarned = 0;
        currentReload = 0;

    }

    @Override
    public Projectile shootProjectile(){
        if(currentReload < 1 && this.isPlaced()){
            currentReload = reloadTime;
            moneyEarned = 20;
        }
        else{
            currentReload --;
            moneyEarned = 0;
        }
        this.setAngle(0);
        return null;
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }
}
