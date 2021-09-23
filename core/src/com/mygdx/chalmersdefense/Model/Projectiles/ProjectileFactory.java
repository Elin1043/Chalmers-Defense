package com.mygdx.chalmersdefense.Model.Projectiles;


public class ProjectileFactory {
    private String bulletPath = "Projectiles/bullet.png";

    public Projectile CreateBullet(int startPosX, int startPosY, double angle){
        Projectile bullet = new Projectile(startPosX, startPosY, angle, bulletPath, 10);

        return bullet;

    }
}
