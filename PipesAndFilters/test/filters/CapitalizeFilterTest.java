package filters;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CapitalizeFilterTest {

    @Test
    public void testSingleWord() {
        String testString = "fast";
        String[] expected = new String[] { "Fast" };
        String[] actual = new CapitalizeFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testLongString() {
        String testString = "fast and furious";
        String[] expected = new String[] { "Fast and furious" };
        String[] actual = new CapitalizeFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFrontSpacing() {
        String testString = "   fast and furious";
        String[] expected = new String[] { "Fast and furious" };
        String[] actual = new CapitalizeFilter().transform(Stream.of(testString))
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}
