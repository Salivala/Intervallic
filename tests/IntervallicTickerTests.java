import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntervallicTickerTests {
    private IntervallicTicker oneHour = new IntervallicTicker.Builder().hours(1).minutes(0).seconds(0).build();

    @Test
    public void sameObjectEqualityTest() {
        assertEquals(oneHour, oneHour);
    }

    @Test
    public void differentObjectsAreNotEqual() {
        assertNotEquals("ldawkjd", oneHour, new IntervallicTicker.Builder().minutes(3).build());
    }

    @Test
    public void equalsTest3() {
        assertEquals("Objects with the same fields do not match", oneHour, new IntervallicTicker.Builder().hours(1).build());
    }

    @Test
    public void equalsTest4() {
        assertNotEquals("Objects with different fields are equal", oneHour, new IntervallicTicker.Builder().hours(2));
    }

    @Test
    public void incrementTest1() {
        assertEquals(
                "3 seconds did not increment to 4",
                new IntervallicTicker.Builder().seconds(3).build().increment().seconds(),
                4.0,
                0);
    }

    @Test
    public void incrementTest2() {
        assertEquals(
                "0 minutes 59 seconds did not increment to 1 minute",
                new IntervallicTicker.Builder().seconds(59).build().increment(),
                new IntervallicTicker.Builder().minutes(1).build()
                );
    }

    @Test
    public void incrementTest3() {
        assertEquals(
                "0 hours 59 minutes 59 seconds did not increment to 1 hour",
                new IntervallicTicker.Builder().minutes(59).seconds(59).build().increment(),
                new IntervallicTicker.Builder().hours(1).build()
        );
    }

    @Test
    public void decrementTest1() {
        assertEquals(
                "1 hour did not decrement to 0 hours 59 minutes 59 seconds",
                new IntervallicTicker.Builder().hours(1).build().decrement(),
                new IntervallicTicker.Builder().minutes(59).seconds(59).build()
        );
    }

    @Test
    public void decrementTest2() {
        assertEquals(
                "Did not reset properly for decrementing",
                new IntervallicTicker.Builder().seconds(1).build().decrement().decrement(),
                new IntervallicTicker.Builder().seconds(1).build()
        );
    }

    @Test
    public void decrementTest3() {
    }
}
