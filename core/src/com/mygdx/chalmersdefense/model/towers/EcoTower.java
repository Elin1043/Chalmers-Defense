package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.event.ModelEvents;
import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the EcoTower
 */

final class EcoTower extends Tower {

    private final CountDownTimer currentReload = new CountDownTimer(600, 0); // Reload time of this tower
    private final EventBus eventbus;

    /**
     * Creates object of a EcoTower
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower
     * @param name of the tower
     * @param reloadSpeed of the tower
     * @param cost of the tower
     * @param range of the tower
     * @param eventbus current eventBus from Model
     */
    EcoTower(float x, float y, String name, int reloadSpeed, int cost, int range,EventBus eventbus) {
        super(x, y, name, reloadSpeed, cost, range);
        this.eventbus = eventbus;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        switch (getUpgradeLevel()) {
            case 1 -> eventbus.emit(new ModelEvents(ModelEvents.Type.ADDTOPLAYER, 20));
            case 2 -> eventbus.emit(new ModelEvents(ModelEvents.Type.ADDTOPLAYER, 40));
            case 3 -> eventbus.emit(new ModelEvents(ModelEvents.Type.ADDTOPLAYER, 60));
//            case 1 -> player.increaseMoney(20);
//            case 2 -> player.increaseMoney(40);
//            case 3 -> player.increaseMoney(80);
        }

        projectileList.add(ProjectileFactory.createMoneyPile(getX(), getY(), getUpgradeLevel()));
    }

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        if (currentReload.haveReachedZero() && this.isPlaced()) {
            createProjectile(projectilesList);
        }
        this.setAngle(0);
    }
}
