package interval;

import java.time.Duration;

public class Interval extends Thread {
    private final Duration startingDuration;
    private volatile Duration duration;
    public int index; // optional variable for allowing intervals to keep track of an external placement in a collection

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    private volatile boolean activated = true;
    private ResetListener resetListener;
    private TickListener tickListener;

    /**
     * @param duration : Amount of time the interval will run
     * @param tickListener : Action performed every tick
     * @param resetListener : Action performed on interval reset ( or end )
     */
    public Interval(Duration duration, TickListener tickListener, ResetListener resetListener) {
        duration = duration.minusSeconds(1);
        this.resetListener = resetListener;
        this.tickListener = tickListener;
        this.duration = duration;
        this.startingDuration = Duration.ofSeconds(duration.getSeconds());
    }

    /**
     * @param duration : Amount of time the interval will run for.
     */
    public Interval(Duration duration) {
        this(duration, () -> {/*Default tick action does nothing*/}, () -> {/* default reset action does nothing */});
    }

    @Override
    public void run() {
        while (activated) {
            try {
                sleep(1000);
                tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTickListener(TickListener tickListener) {
        this.tickListener = tickListener;
    }

    public void addResetListener(ResetListener resetListener) {
        this.resetListener = resetListener;
    }

     public Duration getStartingDuration() {
         return startingDuration;
     }

     public Duration getDuration() {
         return duration;
     }

     void setDuration(Duration duration) {
         this.duration = duration;
     }

     private void tick() {
        if (this.duration.toSeconds() == 0) {
            this.duration = this.duration.ofSeconds(this.startingDuration.getSeconds());
            this.tickListener.tickAction();
            this.resetListener.resetAction();
        }
        else {
            this.duration = this.duration.minusSeconds(1);
            this.tickListener.tickAction();
        }
     }
 }
