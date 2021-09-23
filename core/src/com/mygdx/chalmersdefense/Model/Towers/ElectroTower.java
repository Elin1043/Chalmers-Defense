package com.mygdx.chalmersdefense.Model.Towers;

public class ElectroTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;
    public ElectroTower(float x, float y) {
        super(x, y, "Towers/Electro.png", "ElectroTower", attackDamage, attackSpeed, cost, range);
    }

}
