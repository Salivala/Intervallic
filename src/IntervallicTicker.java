import java.awt.desktop.UserSessionEvent;
import java.util.Objects;

/**
 * Purpose : Provides a simple to use ticker that can be incremented or decremented by seconds
 */
public class IntervallicTicker {
    private static final double MAX_MINUTE_AMOUNT = 59;
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

    /*
     * Internal ticker clock
     * Atom: The smallest interval of time the clock class will manage ( currently seconds )
     * TODO: Possibly extend atom functionality to work with different time units
     **/

    /**
     *  Static builder class for Intervallic ticker, Initialize the containing class using inner classes
     *  constructor, then chain option methods together, ending with .build to get an Intervallic ticker.
     **/
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
    }

    /**
     * Increment the atomic counter by one ( equivalent to 1 second )
     */
    IntervallicTicker increment() {
        //this.atom++;
        if (this.seconds == MAX_SECOND_AMOUNT) {
            if (this.minutes == MAX_MINUTE_AMOUNT) {
                this.hours++;
                this.minutes = 0;
                this.seconds = 0;
            }
            else {
                this.minutes++;
                this.seconds = 0.0;
            }
        }
        else {
            this.seconds ++;
        }
        return this;
    }

    IntervallicTicker decrement() {
        if (this.seconds == MINIMUM_MINUTE_AMOUNT) {
            if (this.minutes == MINIMUM_MINUTE_AMOUNT) {
                if (this.hours == 0.0) {
                    //TODO: complete this stub
                }
                else {
                    //TODO: complete this stub
                }
            }
            else {
                this.seconds = MAX_SECOND_AMOUNT;
                this.minutes--;
            }
        }
        else {
            this.seconds--;
        }
        return this;
    }

    /**
     * Increment the atomic counter ticks amount of times
     * @param ticks amount of times to tick atomic counter
     **/
    public void tick(double ticks)  {

    }

    /**
     * getter for hours. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the hours the ticker has left, after calling syncTicker
     */
    double hours() {
        return hours;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the minutes the ticker has left, after calling syncTicker
     */
    double minutes() {
        return minutes;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the seconds the ticker has left, after calling syncTicker
     */
    double seconds() {
        return seconds;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if(!(o instanceof IntervallicTicker )) {
            return false;
        }
        IntervallicTicker t = (IntervallicTicker) o;
        return this.seconds == t.seconds &&
                this.minutes == t.minutes &&
                this.hours == t.hours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seconds, minutes, hours);
    }
}
