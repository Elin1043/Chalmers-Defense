package com.mygdx.chalmersdefense.model.projectiles;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing an acid projectile
 */
final class AcidProjectile extends Projectile {
    private final List<IProjectile> projectileList; // The list to add acidPool to

    /**
     * Creates a AcidProjectile object
     * @param x The x start position
     * @param y The y start position
     * @param angle The angle of the projectile
     * @param upgradeLevel The upgrade level of the projectile
     * @param projectileList The list to add new projectiles to
     */
    AcidProjectile(float x, float y, float angle, int upgradeLevel, List<IProjectile> projectileList) {
        super(5, "chemistProjectile" + upgradeLevel, x, y, angle, 1);
        this.projectileList = projectileList;
    }

    @Override
    void virusIsHit(int hitVirusHashCode, float angle) {
        // -60 because the acid pool image is 120x120 in size, and it needs to be placed centered
        int upgradeLevel = Character.getNumericValue(getSpriteKey().charAt(getSpriteKey().length() - 1));
        projectileList.add(new AcidPool(getX() - 60, getY() - 60, upgradeLevel));
        super.virusIsHit(hitVirusHashCode, angle);
    }


}
