import java.awt.desktop.UserSessionEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

/**
 * Purpose : Provides a simple to use ticker that can be incremented or decremented by seconds
 * t
 */
public class IntervallicTicker implements Runnable, Subject {
    private static final short MAX_MINUTE_AMOUNT = 59;
    private static final short MINIMUM_MINUTE_AMOUNT = 0;
    private static final short INVALID_MINUTE_AMOUNT = -1;
    private static final short MAX_SECOND_AMOUNT = 59;
    private static final short MINIMUM_SECOND_AMOUNT = 0;
    private static final short INVALID_SECOND_AMOUNT = -1;
    private final short startingHours;
    private final short startingMinutes;
    private final short startingSeconds;
    private ArrayList<Observer> observers;

    /**
     * Fields for client-side readability
     */
    private short hours;
    private short minutes;
    private short seconds;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this.hours, this.minutes, this.seconds);
        }
    }

    /**
     *  Static builder class for Intervallic ticker, Initialize the containing class using inner classes
     *  constructor, then chain option methods together, ending with .build to get an Intervallic ticker.
     **/
    static class Builder {
        private short hours = 0;
        private short minutes = 0;
        private short seconds = 0;

        Builder () {
        }

        Builder hours(short hour) {
            hours = hour;
            return this;
        }

        Builder minutes(short minute) {
            minutes = minute;
            return this;
        }

        Builder seconds(short second) {
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
        observers = new ArrayList<>();
        registerObserver(new TickerDisplay());
        registerObserver(new TextView());
        if ((minutes == INVALID_MINUTE_AMOUNT) || (seconds == INVALID_SECOND_AMOUNT))
        {throw new IllegalArgumentException("All builder calls must be positive!"); }
        else {
            startingHours = hours;
            startingMinutes = minutes;
            startingSeconds = seconds;
        }
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
        this.notifyObservers();
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

    @Override
    public void run() {
        try {
            while(true) { //TODO: Consider a more elegant way to handle a repeating interval
                sleep(1000);
                decrement();
            }
        }
        catch(InterruptedException ex)
        {
        }
    }
}
