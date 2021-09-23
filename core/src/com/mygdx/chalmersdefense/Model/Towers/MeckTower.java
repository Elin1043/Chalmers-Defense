package com.mygdx.chalmersdefense.Model.Towers;

import com.mygdx.chalmersdefense.Model.TargetMode.Calculate;
import com.mygdx.chalmersdefense.Model.TargetMode.TargetMode;
import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;
import java.util.List;

public class MeckTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    private TargetMode targetMode;
    private Virus currentTarget;

    public MeckTower(float x, float y, TargetMode targetMode) {
        super(x, y, "Towers/Meck.png", "MeckTower", attackDamage, attackSpeed, cost, range);
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





    @Override
    public void update(List<Virus> viruses) {
        target(viruses);
    }
}
