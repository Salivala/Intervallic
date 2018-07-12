/**
 * Defines behavior for a timer that does not do any action upon reset
 */
public class DoNothing implements IntervalAction{
    @Override
    public void activate() {
        ; // don't do anything
    }
}
