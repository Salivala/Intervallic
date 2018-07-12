import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntervallicTickerTests {
    private IntervallicTicker oneHour = new IntervallicTicker(1,0,0, new DoNothing());

    @Test
    public void sameObjectEqualityTest() {
        assertEquals(oneHour, oneHour);
    }

    @Test
    public void differentObjectsAreNotEqual() {
        assertNotEquals("ldawkjd", oneHour, new IntervallicTicker(0,3,0, new DoNothing()));
    }

    @Test
    public void equalsTest3() {
        assertEquals("Objects with the same fields do not match", oneHour, new IntervallicTicker(1,0,0, new DoNothing()));
    }

    @Test
    public void equalsTest4() {
        assertNotEquals("Objects with different fields are equal", oneHour, new IntervallicTicker(2,0,0, new DoNothing()));
    }

    @Test
    public void incrementTest1() {
        assertEquals(
                "3 seconds did not increment to 4",
                new IntervallicTicker(0,0,3, new DoNothing()).increment().seconds(),
                4.0,
                0);
    }

    @Test
    public void incrementTest2() {
        assertEquals(
                "0 minutes 59 seconds did not increment to 1 minute",
                new IntervallicTicker(0,0,59, new DoNothing()).increment(),
                new IntervallicTicker(0,1,0, new DoNothing())
                );
    }

    @Test
    public void incrementTest3() {
        assertEquals(
                "0 hours 59 minutes 59 seconds did not increment to 1 hour",
                new IntervallicTicker(0,59,59, new DoNothing()).increment(),
                new IntervallicTicker(1,0,0, new DoNothing())
        );
    }

    @Test
    public void decrementTest1() {
        assertEquals(
                "1 hour did not decrement to 0 hours 59 minutes 59 seconds",
                new IntervallicTicker(1,0,0, new DoNothing()).decrement(),
                new IntervallicTicker(0,59,59, new DoNothing())
        );
    }

    @Test
    public void decrementTest2() {
        assertEquals(
                "Did not reset properly for decrementing",
                new IntervallicTicker(0,0,1, new DoNothing()).decrement().decrement(),
                new IntervallicTicker(0,0,1, new DoNothing())
        );
    }

    @Test
    public void convertIntToTimeTest1() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(120),
                new IntervallicTicker(0,2,0, new DoNothing()));
    }

    @Test
    public void convertIntToTimeTest2() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(3600),
                new IntervallicTicker(1,0,0, new DoNothing()));
    }

    @Test
    public void convertIntToTimeTest3() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(3601),
                new IntervallicTicker(1,0,1, new DoNothing()));
    }

    @Test
    public void convertIntToTimeTest4() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(3602),
                new IntervallicTicker(1,0,2, new DoNothing()));
    }

    @Test
    public void convertIntToTimeTest5() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(3660),
                new IntervallicTicker(1,1,0, new DoNothing()));
    }

    @Test
    public void convertIntToTimeTest6() {
        assertEquals(
                new IntervallicTicker(0,0,0, new DoNothing()).convertIntToTime(3665),
                new IntervallicTicker(1,1,5, new DoNothing()));
    }

    @Test
    public void convertTimeToIntTest1() {
        assertEquals(
                new IntervallicTicker(1,1,5, new DoNothing()).TimeToInt(),
                3665);
    }
}
