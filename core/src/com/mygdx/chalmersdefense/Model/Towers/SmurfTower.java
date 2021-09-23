package com.mygdx.chalmersdefense.Model.Towers;

import com.badlogic.gdx.Gdx;
import com.mygdx.chalmersdefense.Model.TargetMode.Calculate;
import com.mygdx.chalmersdefense.Model.TargetMode.TargetMode;
import com.mygdx.chalmersdefense.Model.Virus;

import java.util.List;

public class SmurfTower extends Tower{
    private static final int cost = 100;
    private static int range = 200;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    private TargetMode targetMode;
    private Virus currentTarget;



    public SmurfTower(float x, float y, TargetMode targetMode){
        super(x, y, "Towers/Smurf.png", "SmurfTower", attackDamage, attackSpeed, cost, range);
        this.targetMode = targetMode;
    }

    public void target(List<Virus> viruses) {
        if (viruses != null && this.isPlaced()) {
            currentTarget = targetMode.getTarget(viruses, this.getPosX(), this.getPosY(), range);
            if (currentTarget != null) {
                this.setAngle((int) Calculate.getAngle(this.getPosX(), this.getPosY(), Gdx.input.getX(), Gdx.input.getY()));
            }
        }
    }





    @Override
    public void update(List<Virus> viruses) {
        target(viruses);
    }
}
