import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class Driver {
    public static void main(String[] args) {
        Thread ticker1 = new Thread(new IntervallicTicker.Builder().minutes(1).build());
        Thread ticker2 = new Thread(new IntervallicTicker.Builder().seconds(3).build());
        Thread ticker3 = new Thread(new IntervallicTicker.Builder().hours(1).build());
        ticker1.start();
        ticker2.start();
        ticker3.start();
    }

}
