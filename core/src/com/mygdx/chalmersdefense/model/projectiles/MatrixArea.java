package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing an area effected by the matrix. Slows down viruses that comes in contact
 */
public class MatrixArea extends Projectile {


    MatrixArea(float x, float y, int upgradeLevel) {
        super(0, "hackerArea" + upgradeLevel, x, y, 0);
    }
}
