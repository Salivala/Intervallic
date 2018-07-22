package interval;

import java.time.Duration;

public class Interval {
    private final Duration startingDuration;
    private Duration duration;

    private volatile boolean activated = true;

    /**
     * Consider if lambda functionality is actually necessary after implementing callable and future.
     */
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


    public void addTickListener(TickListener tickListener) { // TODO: Consider if this method is needed
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

     public void tick() {
        if (this.duration.toSeconds() == 1) {
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
