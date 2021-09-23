package com.mygdx.chalmersdefense.Model.Towers;

public class EcoTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    public EcoTower(float x, float y) {
        super(x, y, "Towers/Eco.png", "EcoTower", attackDamage, attackSpeed, cost, range);
    }
}
