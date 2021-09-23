package com.mygdx.chalmersdefense.Model;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing the player and thier resorces
 */
class Player {

    private int lives;
    private int money;

    Player(int lives, int startCapital){
        this.lives = lives;
        money = startCapital;
    }


    int getLives() { return lives; }
    int getMoney() { return money; }

    void decreaseLivesBy(int livesToDecrease){
        lives -= livesToDecrease;
    }


}
