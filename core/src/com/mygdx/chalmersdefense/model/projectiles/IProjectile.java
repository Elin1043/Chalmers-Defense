package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;

/**
 * @author Elin Forsberg
 * Interface for projectiles
 */
public interface IProjectile extends IMapObject {


    /**
     * Moves the projectile in calculated direction
     *
     * @param hasVirusBeenHit  if virus has been hit
     * @param hitVirusHashCode hashcode of the virus hit
     * @param angle            to move towards
     */
    void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle);

    /**
     * Return if current projectile can be removed
     *
     * @return if can be removed
     */
    boolean canRemove();

    /**
     * Return if the virus with given hashcode has been hit before
     *
     * @param hashCode of the virus
     * @return if virus have been hit before
     */
    boolean haveHitBefore(int hashCode);

    /**
     * Returns how much damage the projectile does
     *
     * @return amount of damage
     */
    float getDamageAmount();
}
