package com.mygdx.chalmersdefense.Model.Towers;

import com.mygdx.chalmersdefense.Model.TargetMode.TargetMode;
import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;
import java.util.List;

public class HackerTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;
    private TargetMode targetMode;

    public HackerTower(float x, float y, TargetMode targetMode) {
        super(x, y, "Towers/Hacker.png", "HackerTower", attackDamage, attackSpeed, cost, range);
        this.targetMode = targetMode;
    }

    private ArrayList<Virus> targetsInRange(ArrayList<Virus> viruses){
        ArrayList<Virus> list = new ArrayList<>();

        for (Virus virus:viruses){
            if (targetMode.isWithinRange(virus, this.getX(),this.getY() , range)){
                list.add(virus);
            }
        }
        return list;
    }



    @Override
    public void update(List<Virus> viruses) {

    }
}
