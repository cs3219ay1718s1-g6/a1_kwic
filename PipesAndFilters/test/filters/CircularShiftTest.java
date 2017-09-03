package filters;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircularShiftTest {

    @Test
    public void testSimpleCircularShift() {
        String testString = "Fast and Furious";
        String[] expected = new String[] { "Fast and Furious", "and Furious Fast", "Furious Fast and" };
        String[] actual = new CircularShiftFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSingleWord() {
        String testString = "Fast";
        String[] expected = new String[] { "Fast" };
        String[] actual = new CircularShiftFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}
