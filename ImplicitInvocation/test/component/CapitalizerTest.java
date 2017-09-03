package component;

import listener.TaskCompleteListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CapitalizerTest {

    private Capitalizer capitalizer;
    private TaskCompleteListener callback;

    @BeforeEach
    public void setUp() {
        this.capitalizer = new Capitalizer();
    }

    @Test
    public void testSingleWord() {

        String testString = "fast";
        String[] expected = new String[] { "Fast" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.capitalizer.setTaskCompleteListener(callback);
        this.capitalizer.transform(Arrays.asList(testString));

    }

    @Test
    public void testLongString() {

        String testString = "fast and furious";
        String[] expected = new String[] { "Fast and furious" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.capitalizer.setTaskCompleteListener(callback);
        this.capitalizer.transform(Arrays.asList(testString));

    }

    @Test
    public void testFrontSpacing() {

        String testString = "   fast and furious";
        String[] expected = new String[] { "Fast and furious" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.capitalizer.setTaskCompleteListener(callback);
        this.capitalizer.transform(Arrays.asList(testString));

    }

}
