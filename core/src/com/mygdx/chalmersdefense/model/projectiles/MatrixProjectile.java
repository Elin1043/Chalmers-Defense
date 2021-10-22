package com.mygdx.chalmersdefense.model.projectiles;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Class representing Hackerman / HackerTowers shooting projectile
 */
final class MatrixProjectile extends Projectile{

    private final List<IProjectile> projectileList; // The list to add the MatrixArea to

    MatrixProjectile(float x, float y, float angle, int upgradeLevel, List<IProjectile> projectileList) {
        super(7, "hackerProjectile" + upgradeLevel, x, y, angle, Math.min(upgradeLevel - 1, 1));
        this.projectileList = projectileList;
    }

    @Override
    public void virusIsHit(int haveHit, float angle) {
        int upgradeLevel = Character.getNumericValue(getSpriteKey().charAt(getSpriteKey().length() - 1));
        projectileList.add(new MatrixArea(getX(), getY(), upgradeLevel));
        super.virusIsHit(haveHit, angle);
    }
}
