public class DefaultIntervalBeep implements IntervalAction{
    @Override
    public void activate() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
}
