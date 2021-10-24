package com.mygdx.chalmersdefense.model.modelUtilities;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.model.IUpdateModel;
import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.model.event.ModelEvents;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * A class containing metods related to the game timer. Wraps a timer object in it
 * The timer then calls update method in the given model
 */
final public class GameTimer implements IGameTimer {

    private final Timer timer = new Timer();    // The timer object
    private Timer.Task task = new Timer.Task() {
        @Override
        public void run() {}
    }; // The runnable task the timer uses to schedule method calls with
    private float delay = 0.005F;               // Delay in seconds between task calls
    private EventBus eventBus;                  // A reference to the EventBus in the game

    /**
     * Constructor for the GameTimer class
     *
     * @param eventBus The active EventBus
     */
    public GameTimer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void startUpdateTimer() {
        setupTask();
        timer.start();
    }

    @Override
    public void stopUpdateTimer() {
        task.cancel();  // Cancels current task so the update method won't be called
        timer.stop();   // Stops timer
        timer.clear();  // Clears timer from old tasks
    }

    @Override
    public void changeUpdateSpeed() {
        if (delay < 0.004F) {
            delay = 0.005F;
        } else {
            delay = 0.0028F;
        }
        timer.clear();  // Resets timer task list
        setupTask();
    }

    @Override
    public boolean isGameSpedUp() {
        return delay < 0.004F;
    }

    // Creates new task with current delay
    private void setupTask() {
        task = new Timer.Task() {
            @Override
            public void run() {
                if (task.isScheduled()) {
                    eventBus.emit(new ModelEvents(ModelEvents.Type.UPDATEMODEL));
                }
            }
        };
        timer.scheduleTask(task, 0, delay);
    }
}
