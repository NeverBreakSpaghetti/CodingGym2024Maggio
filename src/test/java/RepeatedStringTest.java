import org.junit.jupiter.api.Test;
import src.RepeatedString;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedStringTest {

    @Test
    public void exampleTest0() {
        assertEquals(7L, RepeatedString.repeatedString("aba", 10L));
    }
    @Test
    public void exampleTest1() {
        assertEquals(1000000000000L, RepeatedString.repeatedString("a", 1000000000000L));
    }

    @Test
    public void simpleTest2() {
        assertEquals(3L, RepeatedString.repeatedString("abbabb", 8L));
    }

}
