package com.mygdx.chalmersdefense.Model.Towers;

public class ChemistTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    public ChemistTower(float x, float y) {
        super(x, y, "Towers/Chemist.png", "ChemistTower", attackDamage, attackSpeed, cost, range);
    }
}
