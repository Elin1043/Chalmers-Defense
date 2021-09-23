package com.mygdx.chalmersdefense.Model.Towers;

public class MeckTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    public MeckTower(float x, float y) {
        super(x, y, "Towers/Meck.png", "MeckTower", attackDamage, attackSpeed, cost, range);
    }
}
