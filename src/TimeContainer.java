public class TimeContainer {

    public short hours;
    public short minutes;
    public short seconds;


    TimeContainer() {}
    TimeContainer(short inHours, short inMinutes, short inSeconds) {
        this.hours = inHours;
        this.minutes = inMinutes;
        this.seconds = inSeconds;
    }

    @Override
    public String toString() {
        return (hours) + ":" + (minutes) + ":" + (seconds);
    }
}
