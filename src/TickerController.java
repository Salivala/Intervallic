import static java.lang.Thread.sleep;

/**
 * Controller for the application
 */
class TickerController {
    IntervallicTicker ticker;
    View display;
    TickerController(IntervallicTicker ticker) {
        this.ticker = ticker;
        display = new TickerDisplay(ticker, this);
    }

    void startTicker(int hours, int minutes, int seconds) {
        ticker.prepareTicker(hours, minutes, seconds);
        Thread tickerThread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (true) {
                    display.displayTime(ticker.hours(), ticker.minutes(), ticker.seconds());
                    display.updateElement(ticker.TimeToInt());
                    try {
                        sleep(1000);
                        ticker.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tickerThread.start();
    }
}
