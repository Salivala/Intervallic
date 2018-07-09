/**
 * Purpose : Provides a simple to use ticker that can be incremented or decremented by seconds
 */
public class IntervallicTicker {
    private static final double MAX_MINUTE_AMOUNT = 59.59;
    private static final double MINIMUM_MINUTE_AMOUNT = 0.0;
    private static final double INVALID_MINUTE_AMOUNT = -1.0;
    private static final double MAX_SECOND_AMOUNT = 59.0;
    private static final double MINIMUM_SECOND_AMOUNT = 0.0;
    private static final double INVALID_SECOND_AMOUNT = -1.0;

    /**
     * Fields for client-side readability
     */
    private double hours;
    private double minutes;
    private double seconds;

    /**
     * Internal ticker clock
     * Atom: The smallest interval of time the clock class will manage ( currently seconds )
     * TODO: Possibly extend atom functionality to work with different time units
     */
    private double atom;

    /**
     *  Static builder class for Intervallic ticker, Initialize the containing class using inner classes
     *  constructor, then chain option methods together, ending with .build to get an Intervallic ticker.
     */
    static class Builder {
        private double hours = 0;
        private double minutes = 0;
        private double seconds = 0;

        Builder () {
        }

        Builder hours(double hour) {
            hours = hour;
            return this;
        }

        Builder minutes(double minute) {
            minutes = minute;
            return this;
        }

        Builder seconds(double second) {
            seconds = second;
            return this;
        }

        IntervallicTicker build() {
            return new IntervallicTicker(this);
        }
    }

    private IntervallicTicker(Builder build) { // TODO:
        hours = build.hours; //TODO: ADD CONSTRAINTS TO HOURS
        minutes = (build.minutes <= MAX_MINUTE_AMOUNT && build.minutes >= MINIMUM_MINUTE_AMOUNT) ? build.minutes : INVALID_MINUTE_AMOUNT;
        seconds = (build.seconds <= MAX_SECOND_AMOUNT && build.seconds >= MINIMUM_SECOND_AMOUNT) ? build.seconds : INVALID_SECOND_AMOUNT;
        if ((minutes == INVALID_MINUTE_AMOUNT) || (seconds == INVALID_SECOND_AMOUNT))
        {throw new IllegalArgumentException("All builder calls must be positive!"); }
        else {
            atom = seconds + (minutes * 60) + ((hours * 60)*60); // atom represents all seconds, ignoring time constants
            System.out.println("ATOM BEFORE TICK : " + atom);
            syncTicker();
        }
    }

    /**
     * Increment the atomic counter by one ( equivalent to 1 second )
     */
    void tick() {
        this.atom++;
    }

    /**
     * Increment the atomic counter ticks amount of times
     * @param ticks amount of times to tick atomic counter
     */
    public void tick(double ticks)  {
        this.atom += ticks;
    }

    /**
     * getter for hours. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the hours the ticker has left, after calling syncTicker
     */
    double hours() {
        syncTicker();
        return hours;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the minutes the ticker has left, after calling syncTicker
     */
    double minutes() {
        syncTicker();
        return minutes;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the seconds the ticker has left, after calling syncTicker
     */
    double seconds() {
        syncTicker();
        return seconds;
    }

    /**
     * Align the class fields with what the hours minutes and seconds should be
     */
    private void syncTicker()
    {
        hours = Math.floor((atom / 60) / 60);
        minutes = Math.floor((atom / 60) % 60);
        seconds = .6 * ((atom / 60 % 60) % minutes * 100);
    }
}
