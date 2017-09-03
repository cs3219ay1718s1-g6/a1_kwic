package component;

import listener.TaskCompleteListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AlphabetizerTest {

    private Alphabetizer alphabetizer;
    private TaskCompleteListener callback;

    @BeforeEach
    public void setUp() {
        this.alphabetizer = new Alphabetizer();
    }

    @Test
    public void testUnsortedStream() {

        String[] unsortedStrings = new String[] { "bob", "alice", "trudy", "cindy" };
        String[] expected = new String[] { "alice", "bob", "cindy", "trudy" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.alphabetizer.setTaskCompleteListener(callback);
        this.alphabetizer.transform(Arrays.asList(unsortedStrings));

    }

    @Test
    public void testEmptyStream() {

        String[] emptyStrings = new String[] { };
        String[] expected = new String[] { };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.alphabetizer.setTaskCompleteListener(callback);
        this.alphabetizer.transform(Arrays.asList(emptyStrings));

    }
}
