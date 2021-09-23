package com.mygdx.chalmersdefense.Model.Towers;

public class HackerTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;

    public HackerTower(float x, float y) {
        super(x, y, "Towers/Hacker.png", "HackerTower", attackDamage, attackSpeed, cost, range);
    }
}
