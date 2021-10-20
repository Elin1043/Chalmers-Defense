package com.mygdx.chalmersdefense.model;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing the player and their resorces
 *
 * 2021-10-03 Modified by Daniel Persson: Added reset method for resetting all values in player.
 */
final public class Player {

    private int lives;  // How many lives the player has currently
    private int money;  // How much money the player has currently

    /**
     * Creates player object
     *
     * @param lives        Amount of lives the player has from the start
     * @param startCapital Amount of money the player has from the start
     */
    Player(int lives, int startCapital) {
        this.lives = lives;
        money = startCapital;
    }

    /**
     * Method for resetting all values in Player object
     *
     * @param lives        Amount of lives the player has after resetting
     * @param startCapital Amount of money the player after resetting
     */
    void resetPlayer(int lives, int startCapital) {
        this.lives = lives;
        money = startCapital;
    }

    /**
     * Get amount of lives from player
     *
     * @return Current amount of lives
     */
    int getLives() {
        return lives;
    }

    /**
     * Get amount of money
     *
     * @return Current amount of money
     */
    int getMoney() {
        return money;
    }

    /**
     * Decrease the amount of money
     *
     * @param amount amount to decrease by
     */
    void decreaseMoney(int amount) {
        money -= amount;
    }

    /**
     * Increase the amount of money
     *
     * @param amount amount to increase by
     */
    public void increaseMoney(int amount) {
        money += amount;
    }

    /**
     * Method to decrease player life
     *
     * @param livesToDecrease How much to decrease lives by
     * @throws PlayerLostAllLifeException When the life counter reaches zero or below
     */
    void decreaseLivesBy(int livesToDecrease) throws PlayerLostAllLifeException {
        if (lives <= 0) {
            lives = 0;
            throw new PlayerLostAllLifeException();
        } else {
            lives = Math.max(lives - livesToDecrease, 0);
        }

    }


}
