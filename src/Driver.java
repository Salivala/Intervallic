import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class Driver {
    public static void main(String[] args) {
        IntervallicTicker ticker = new IntervallicTicker.Builder()
                .hours(60)
                .minutes(23)
                .seconds(1)
                .build();

        for (int i = 0; i < 3400; i++)
        {
            ticker.tick();
            System.out.println("Hours : " + ticker.hours());
            System.out.println("Minutes : " + ticker.minutes());
            System.out.println("Seconds : " + ticker.seconds());
        }
    }

}
