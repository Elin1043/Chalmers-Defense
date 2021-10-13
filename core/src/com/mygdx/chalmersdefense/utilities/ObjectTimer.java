package com.mygdx.chalmersdefense.utilities;

/**
 * @author Joel Båtsman Hilmersson
 * Class for holding a simple countdown timer used by many classes
 */
public class ObjectTimer {

    private final int timerLength;  // The total length of the timer
    private int timerCountDown;     // The current timer cooldown/position

    public ObjectTimer(int timerLength){
        this.timerLength = timerLength;
    }


    public boolean updateTimer(){   // TODO Question, is this okay? I both modify timer and return a boolean if the timer is done or should they be separated
        if (timerCountDown > 0){
            timerCountDown--;
            return false;
        } else {
            timerCountDown = timerLength;   // Resets timer
            return true;
        }
    }
}
