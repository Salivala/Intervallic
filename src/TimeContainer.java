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
        String strHours = Short.toString(hours), strMinutes = Short.toString(minutes), strSeconds = Short.toString(seconds);
        if (hours < 10 ) {
            if (hours == 0) {
                strHours = "00";
            }
            else {
                strHours = "0" + hours;
            }
        }

        if (minutes < 10 ) {
            if (minutes == 0) {
                strMinutes = "00";
            }
            else {
                strMinutes = "0" + minutes;
            }
        }

        if (seconds < 10 ) {
            if (seconds == 0) {
                strSeconds = "00";
            }
            else {
                strSeconds = "0" + seconds;
            }
        }
        return (strHours) + ":" + (strMinutes) + ":" + (strSeconds);
    }
}
