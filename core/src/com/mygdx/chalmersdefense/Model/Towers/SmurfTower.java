package com.mygdx.chalmersdefense.Model.Towers;

public class SmurfTower extends Tower{
    private static final int cost = 100;
    private static int range = 100;
    private static int attackDamage = 10;
    private static int attackSpeed = 10;



    public SmurfTower(float x, float y){
        super(x, y, "Towers/Smurf.png", "SmurfTower", attackDamage, attackSpeed, cost, range);
    }
}
