import static java.lang.Thread.sleep;

public class TickerThread implements Runnable{
    View display;
    IntervallicTicker ticker;
    public TickerThread(View v, IntervallicTicker t) {
        display = v;
        ticker = t;
    }

    @Override
    public void run() {
        while (true) {
            display.setTime(new TimeContainer(ticker.hours(), ticker.minutes(), ticker.seconds()));
            try {
                sleep(1000);
                ticker.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
