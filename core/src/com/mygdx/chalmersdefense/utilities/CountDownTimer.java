package com.mygdx.chalmersdefense.utilities;

/**
 * @author Joel Båtsman Hilmersson
 * Class for holding a simple countdown timer used by many classes
 */
public class CountDownTimer {

    private final int timerLength;  // The total length of the timer
    private int timerCountDown;     // The current timer cooldown/position

    /**
     * Construct the CountDownTimer object
     * @param totalLength The length uf timer
     */
    public CountDownTimer(int totalLength){
        this.timerLength = totalLength;
        timerCountDown = totalLength;
    }

    /**
     * Counts down timer and returns of it has reached zero
     * @return True - if the timer has reached zero, False - if the timer has not reached zero
     */
    public boolean haveReachedZero(){   // TODO Question, is this okay? I both modify timer and return a boolean if the timer is done or should they be separated
        if (timerCountDown > 0){
            timerCountDown--;
            return false;
        } else {
            timerCountDown = timerLength;   // Resets timer
            return true;
        }
    }
}
