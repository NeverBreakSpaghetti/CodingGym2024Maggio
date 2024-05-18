import org.junit.jupiter.api.Test;
import src.EvenForest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvenForestTest {
    @Test
    public void exampleTest0() {
        assertEquals(2, EvenForest.evenForest(10, 9, Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),Arrays.asList(1, 1, 3, 2, 1, 2, 6, 8, 8)));
    }
}
