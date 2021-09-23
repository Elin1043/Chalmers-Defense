package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.projectiles.BulletProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.Calculate;
import com.mygdx.chalmersdefense.model.targetMode.TargetMode;
import com.mygdx.chalmersdefense.model.Virus;

import java.util.List;

public class SmurfTower extends Tower{
    private static final int cost = 100;
    private static int range = 200;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    private TargetMode targetMode;
    private Virus currentTarget;

    private int reloadTime = 60; //how many frames
    private int currentReload = 0;

    Model model;





    public SmurfTower(float x, float y, TargetMode targetMode){
        super(x, y, "Towers/Smurf.png", "SmurfTower", attackDamage, attackSpeed, cost, range);
        this.targetMode = targetMode;
    }

    public void target(List<Virus> viruses) {
        if (viruses != null && this.isPlaced()) {
            currentTarget = targetMode.getTarget(viruses, this.getPosX(), this.getPosY(), range);
            if (currentTarget != null) {
                this.setAngle( Calculate.angleDeg(currentTarget.getX(), currentTarget.getY(), this.getPosX(), this.getPosY()));
            }
        }
    }

    public Projectile createProjectile() {
        if (currentTarget != null){
            return new BulletProjectile(attackSpeed,this.getPosX(), this.getPosY(), Calculate.angleDeg(currentTarget.getX(), currentTarget.getY(), this.getPosX(), this.getPosY()));
        }
        return null;
    }

    public void shoot(){
        if(currentReload < 1 && currentTarget != null){
            model.getProjectilesList().add(createProjectile());
            currentReload = reloadTime;
        }
        else{
            currentReload --;
        }
    }


    @Override
    public void update(List<Virus> viruses) {
        target(viruses);
        shoot();
    }
}
