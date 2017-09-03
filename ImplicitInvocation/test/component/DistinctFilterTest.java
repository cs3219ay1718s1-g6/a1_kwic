package component;

import listener.TaskCompleteListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DistinctFilterTest {

    private DistinctFilter distinctFilter;
    private TaskCompleteListener callback;

    @BeforeEach
    public void setUp() {
        this.distinctFilter = new DistinctFilter();
    }

    @Test
    public void testSimpleDistinct() {

        String[] duplicates = new String[] { "hello", "hello" };
        String[] expected = new String[] { "hello" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.distinctFilter.setTaskCompleteListener(callback);
        this.distinctFilter.transform(Arrays.asList(duplicates));

    }

    @Test
    public void testMaintainingOrder() {

        String[] duplicates = new String[] { "bob", "alice", "bob", "cindy", "cindy", "alice", "trudy" };
        String[] expected = new String[] { "bob", "alice",  "cindy", "trudy" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.distinctFilter.setTaskCompleteListener(callback);
        this.distinctFilter.transform(Arrays.asList(duplicates));

    }
}
