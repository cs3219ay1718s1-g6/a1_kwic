package filters;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DedupeFilterTest {
    @Test
    public void testSimpleDedupe() {
        String[] duplicates = new String[] { "hello", "hello" };
        String[] expected = new String[] { "hello" };

        String[] actual = new DedupeFilter().transform(Arrays.stream(duplicates)).toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMaintainingOrder() {
        String[] duplicates = new String[] { "bob", "alice", "bob", "cindy", "cindy", "alice", "trudy" };
        String[] expected = new String[] { "bob", "alice",  "cindy", "trudy" };

        String[] actual = new DedupeFilter().transform(Arrays.stream(duplicates)).toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testCaseInsensitiveDedupe() {
        String[] duplicates = new String[] { "Alice", "alice", "Bob", "bob", "boB", "bOb", "aLiCe" };
        String[] expected = new String[] { "Alice", "Bob" };

        String[] actual = new DedupeFilter().transform(Arrays.stream(duplicates)).toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}
