package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.Virus;

public class BulletProjectile extends Projectile{
    private static String name = "bullet";

    public BulletProjectile(int speed , float x, float y, double angle) {
        super(10, name, x, y, angle);
    }

}
