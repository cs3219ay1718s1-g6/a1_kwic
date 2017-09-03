package filters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AlphabetizeFilterTest {

    @Test
    public void testUnsortedStream() {
        String[] unsortedStrings = new String[] { "bob", "alice", "trudy", "cindy" };
        String[] expected = new String[] { "alice", "bob", "cindy", "trudy" };

        String[] actual = new AlphabetizeFilter().transform(Arrays.stream(unsortedStrings)).toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEmptyStream() {
        assertArrayEquals(
                new String[] {}, // Expected an empty array
                new AlphabetizeFilter().transform(Arrays.stream(new String[] {})).toArray(String[]::new)
        );
    }
}
