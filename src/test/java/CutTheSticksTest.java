import org.junit.jupiter.api.Test;
import src.CutTheSticks;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutTheSticksTest {
    @Test
    public void exampleTest0() {
        assertEquals(Arrays.asList(6, 4, 2, 1), CutTheSticks.cutTheSticks(Arrays.asList(5, 4, 4, 2, 2, 8)));
    }
    @Test
    public void exampleTest1() {
        assertEquals(Arrays.asList(8, 6, 4, 1), CutTheSticks.cutTheSticks(Arrays.asList(1, 2, 3, 4, 3, 3, 2, 1)));
    }
}
