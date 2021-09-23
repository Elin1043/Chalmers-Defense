package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.Virus;

public class BulletProjectile extends Projectile{
    private static String imagePath = "projectiles/bullet.png";


    public BulletProjectile(int speed , float x, float y, double angle) {
        super(10, imagePath, x, y, angle);
    }

    @Override
    public void attack(Virus virus) {
        if (virus != null){
            //virus.devolve();
        }
    }
}
