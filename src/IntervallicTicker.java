import java.util.Objects;

/**
 * Purpose : Provides a simple to use ticker that can be incremented or decremented by seconds
 */
public class IntervallicTicker {
    private static final short MAX_MINUTE_AMOUNT = 59;
    private static final short MINIMUM_MINUTE_AMOUNT = 0;
    private static final short INVALID_MINUTE_AMOUNT = -1;
    private static final short MAX_SECOND_AMOUNT = 59;
    private static final short MINIMUM_SECOND_AMOUNT = 0;
    private static final short INVALID_SECOND_AMOUNT = -1;
    private IntervalAction intervalBehavior;

    /**
     * Fields for client-side readability
     */
    short hours;
    short minutes;
    short seconds;
    public short startingHours;
    public short startingMinutes;
    public short startingSeconds;


    IntervallicTicker(IntervalAction action) {
        this (0,0,0, action);
    }
    IntervallicTicker(int hours, int minutes, int seconds, IntervalAction action) {
        this.intervalBehavior = action;
        this.hours = (short) hours;
        this.minutes = (short) minutes;
        this.seconds = (short) seconds;
        this.startingHours = (short) hours;
        this.startingMinutes = (short) minutes;
        this.startingSeconds = (short) seconds;
    }

    /**
     *  Handle a single second incrementing
     */
    IntervallicTicker increment() {
        if (this.seconds == MAX_SECOND_AMOUNT) {
            if (this.minutes == MAX_MINUTE_AMOUNT) {
                this.hours++;
                this.minutes = 0;
                this.seconds = 0;
            }
            else {
                this.minutes++;
                this.seconds = 0;
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
                    this.hours = startingHours;
                    this.minutes = startingMinutes;
                    this.seconds = startingSeconds;
                    this.intervalBehavior.activate();
                }
                else {
                    this.hours--;
                    this.minutes = 59;
                    this.seconds = 59;
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
     * getter for hours. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the hours the ticker has left, after calling syncTicker
     */
    short hours() {
        return hours;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the minutes the ticker has left, after calling syncTicker
     */
    short minutes() {
        return minutes;
    }

    /**
     * getter for minutes. Because the synctimes method can potentially be expensive, only
     * run the calculation when you actually want a prettified time.
     * @return return the seconds the ticker has left, after calling syncTicker
     */
    short seconds() {
        return seconds;
    }

    void prepareTicker(int hours, int minutes, int seconds) {
        this.startingHours = (short )hours;
        this.hours = (short) hours;
        this.startingMinutes = (short) minutes;
        this.minutes = (short) minutes;
        this.startingSeconds = (short) seconds;
        this.seconds = (short) seconds;
    }

    IntervallicTicker convertIntToTime(int rawSeconds) {
        /*
        for (int i = 0; i < rawSeconds; i++) {
            this.increment();
        }
        */ // this block works but is not efficient
        double tmpHours = Math.floor((rawSeconds / 60) / 60);
        double tmpMinutes = Math.floor((rawSeconds / 60) % 60);
        double tmpSeconds = (rawSeconds % 60) % 60;
        hours = (short) tmpHours;
        minutes = (short) tmpMinutes;
        seconds = (short) tmpSeconds;
        return this;
    }

    public int TimeToInt() {
        return ((hours * 60) * 60) + (minutes * 60) + seconds;
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
