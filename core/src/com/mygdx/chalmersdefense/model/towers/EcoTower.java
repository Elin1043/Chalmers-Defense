package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.modelUtilities.events.ModelEvents;
import com.mygdx.chalmersdefense.utilities.event.EventBus;
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
    private final EventBus eventbus;    // The eventbus to call when money should be added

    /**
     * Creates object of a EcoTower
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower
     ** @param eventbus current eventBus from Model
     */
    EcoTower(float x, float y, EventBus eventbus) {
        super(x, y, "Economist", 180, 600, 60);
        this.eventbus = eventbus;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        switch (getUpgradeLevel()) {
            case 1 -> eventbus.emit(new ModelEvents(ModelEvents.EventType.ADDMONEYTOPLAYER, 20));
            case 2 -> eventbus.emit(new ModelEvents(ModelEvents.EventType.ADDMONEYTOPLAYER, 40));
            case 3 -> eventbus.emit(new ModelEvents(ModelEvents.EventType.ADDMONEYTOPLAYER, 60));
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
