import java.util.ArrayList;

/**
 * Controller for the application
 */
public class TickerController implements Subject {
    IntervallicTicker ticker = new IntervallicTicker.Builder().seconds((short) 34).build();
    ArrayList<Observer> displays = new ArrayList<>();
    Thread tickerThread = new Thread(ticker);
    TickerController() {
        //registerObserver(new TextView());
        registerObserver(new TickerDisplay());
        tickerThread.start();
    }

    @Override
    public void registerObserver(Observer o) {
        displays.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        displays.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer n : displays) {
            n.update(ticker.hours(), ticker.minutes(), ticker.seconds());
        }
    }
}
