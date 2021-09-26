package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.ArrayList;
import java.util.List;

public class MechTower extends Tower {

    private int maxTowers = 2;
    private int currentTowers = 0;

    private List<MechMiniTower> miniTowers = new ArrayList<>();

    private float x;
    private float y;
    private int attackSpeed;
    private List<ITargetMode> targetModes;
    private Projectile projectile;

    public MechTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes, Projectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
        this.x = x;
        this.y = y;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.projectile = projectile;



    }

    public void createMiniTowers(){
        for (int i = 0; i < maxTowers; i++) {
            int tempX = getRandomNumber(x - getRange(), x + getRange());
            int tempY = getRandomNumber(y - getRange(), y + getRange());
            miniTowers.add(new MechMiniTower(tempX,tempY,attackSpeed,targetModes,projectile));
        }
    }

    @Override
    public Projectile shootProjectile(){
        this.setAngle(0);
        return null;
    }

    private int getRandomNumber(float min, float max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public List<MechMiniTower> getMiniTowers() {
        return miniTowers;
    }
}
