public class Driver {
    public static void main(String[] args) {
        new TickerController(new IntervallicTicker(new DefaultIntervalBeep()));
    }

}
