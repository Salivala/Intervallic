import java.util.ArrayList;

/**
 * Controller for the application
 */
public class TickerController {
    IntervallicTicker ticker = new IntervallicTicker.Builder().seconds((short) 34).build();
    ArrayList<Observer> displays = new ArrayList<>();
    Thread tickerThread = new Thread(ticker);
    TickerController() {
        tickerThread.start();
    }
}
