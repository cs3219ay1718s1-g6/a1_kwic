package component;

import listener.TaskCompleteListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircularShiftTest {

    private CircularShifter circularShifter;
    private TaskCompleteListener callback;

    @BeforeEach
    public void setUp() {
        this.circularShifter = new CircularShifter();
    }

    @Test
    public void testSimpleCircularShift() {

        String testString = "Fast and Furious";
        String[] expected = new String[] { "Fast and Furious", "and Furious Fast", "Furious Fast and" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.circularShifter.setTaskCompleteListener(callback);
        this.circularShifter.transform(Arrays.asList(testString));

    }

    @Test
    public void testSingleWord() {

        String testString = "Fast";
        String[] expected = new String[] { "Fast" };

        this.callback = output -> {
            String[] actual = output.toArray(new String[output.size()]);
            assertArrayEquals(expected, actual);
        };

        this.circularShifter.setTaskCompleteListener(callback);
        this.circularShifter.transform(Arrays.asList(testString));

    }

}
