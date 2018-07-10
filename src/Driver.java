import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class Driver {
    public static void main(String[] args) {
        Thread ticker1 = new Thread(new IntervallicTicker.Builder().minutes((short) 1).build());
        ticker1.start();
    }

}
