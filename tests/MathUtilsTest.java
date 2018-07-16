import main.MathUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathUtilsTest {

    @Test
    public void percentOfTest1() {
        assertEquals(50.0 , MathUtils.getPercentOf(5, 10), 1);
    }

}