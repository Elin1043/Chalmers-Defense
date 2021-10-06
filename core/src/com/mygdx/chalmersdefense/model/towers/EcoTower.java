package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.Player;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the EcoTower
 */

class EcoTower extends Tower {

    private int currentReload;
    private final int reloadTime = 60*10; //how many updates from model
    private final Player player;

    EcoTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<ITargetMode> targetModes, Player player) {
        super(x, y, name, reloadSpeed, cost, range, targetModes);
        this.player = player;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        switch (getUpgradeLevel()){
            case 1 -> player.increaseMoney(20);
            case 2 -> player.increaseMoney(40);
            case 3 -> player.increaseMoney(80);

        }
        projectileList.add(ProjectileFactory.createMoneyProjectile(getX(), getY(), getUpgradeLevel()));
    }

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget){
        if(currentReload < 1 && this.isPlaced()){
            currentReload = reloadTime;
            createProjectile(projectilesList);
        }
        else{
            currentReload --;
        }
        this.setAngle(0);
    }
}
