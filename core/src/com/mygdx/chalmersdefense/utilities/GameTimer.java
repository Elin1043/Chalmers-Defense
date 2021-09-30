package com.mygdx.chalmersdefense.utilities;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.model.IUpdateModel;

/**
 * @author Joel Båtsman Hilmersson
 *
 * A class containing metods related to the game timer. Wraps a timer object in it
 */
public class GameTimer {
    private final Timer timer = new Timer();
    private Timer.Task task;
    private float delay = 0.005F;
    private final IUpdateModel model;

    public GameTimer(IUpdateModel model) {
        this.model = model;
    }

    /**
     * Starts the timer that updates model (Effectively un-pauses the game)
     */
    public void startUpdateTimer() {
        setupTask();
        timer.start();

        System.out.println("START TIMER");
    }

    /**
     * Stops the timer that updates model (Effectively pauses the game state)
     */
    public void stopUpdateTimer() {
        task.cancel();  // Cancels current task so the update method won't be called
        timer.stop();   // Stops timer
        timer.clear();  // Clears timer from old tasks

        System.out.println("STOP TIMER");
    }

    /**
     * Change model update speed to run simulation faster or slower based on current speed
     */
    public void changeUpdateSpeed() {
        if (delay < 0.004F){
            delay = 0.005F;
        } else {
            delay = 0.0028F;
        }
        timer.clear();  // Resets timer task list
        setupTask();
    }

    private void createTask() {
        task = new Timer.Task() {
            @Override
            public void run() { if (task.isScheduled()) { model.updateModel(); }
            }
        };
    }

    // Creates new task with current delay
    private void setupTask() {
        createTask();
        timer.scheduleTask(task, 0, delay);

    }
}
